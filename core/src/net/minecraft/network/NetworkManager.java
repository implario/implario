package net.minecraft.network;

import com.google.common.collect.Queues;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.local.LocalChannel;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.TimeoutException;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.logging.Log;
import net.minecraft.network.protocol.IProtocol;
import net.minecraft.network.protocol.IProtocols;
import net.minecraft.network.protocol.minecraft.ProtocolMinecraft;
import net.minecraft.network.protocol.minecraft_47.Protocol47;
import net.minecraft.util.*;
import net.minecraft.util.chat.ChatComponentText;
import net.minecraft.util.chat.ChatComponentTranslation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

import javax.crypto.SecretKey;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class NetworkManager extends SimpleChannelInboundHandler<Packet> {

	private static final Log logger = Log.MAIN;
	public static final AttributeKey<IProtocol> attrKeyConnectionState = AttributeKey.valueOf("protocol");
	public static final LazySupplier<NioEventLoopGroup> NIO_CLIENT = new LazySupplier<NioEventLoopGroup>() {
		protected NioEventLoopGroup load() {
			return new NioEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Client IO #%d").setDaemon(true).build());
		}
	};
	public static final LazySupplier<EpollEventLoopGroup> EPOLL_CLIENT = new LazySupplier<EpollEventLoopGroup>() {
		protected EpollEventLoopGroup load() {
			return new EpollEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Epoll Client IO #%d").setDaemon(true).build());
		}
	};
	public static final LazySupplier<LocalEventLoopGroup> LOCAL_CLIENT = new LazySupplier<LocalEventLoopGroup>() {
		protected LocalEventLoopGroup load() {
			return new LocalEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Local Client IO #%d").setDaemon(true).build());
		}
	};
	private final boolean isClientSide;
	private final Queue<NetworkManager.InboundHandlerTuplePacketListener> outboundPacketsQueue = Queues.newConcurrentLinkedQueue();
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	/**
	 * The active channel
	 */
	private Channel channel;

	/**
	 * Активный протокол для игрока
	 */
	@Getter
	@Setter
	private IProtocols protocol;

	/**
	 * The address of the remote party
	 */
	private SocketAddress socketAddress;

	/**
	 * The INetHandler instance responsible for processing received packets
	 */
	private INetHandler packetListener;

	/**
	 * A String indicating why the network has shutdown.
	 */
	private IChatComponent terminationReason;
	private boolean isEncrypted;
	private boolean disconnected;

	public NetworkManager(boolean isClientSide) {
		this.isClientSide = isClientSide;
	}

	public void channelActive(ChannelHandlerContext context) throws Exception {
		super.channelActive(context);
		this.channel = context.channel();
		this.socketAddress = this.channel.remoteAddress();

		try {
			this.setConnectionState(ProtocolMinecraft.HANDSHAKE);
		} catch (Throwable throwable) {
			logger.error("Error on change connection state", throwable);
		}
	}

	public void setAutoRead(boolean autoRead){
		channel.config().setAutoRead(autoRead);
	}

	/**
	 * Sets the new connection state and registers which packets this channel may send and receive
	 */
	public void setConnectionState(IProtocol newState) {
		this.channel.attr(attrKeyConnectionState).set(newState);
		this.channel.config().setAutoRead(true);
		Log.MAIN.debug("Enabled auto read");
	}

	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		this.closeChannel(new ChatComponentTranslation("disconnect.endOfStream"));
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable t) throws Exception {
		ChatComponentTranslation chatcomponenttranslation;

		if (t instanceof TimeoutException) {
			chatcomponenttranslation = new ChatComponentTranslation("disconnect.timeout");
		} else {
			chatcomponenttranslation = new ChatComponentTranslation("disconnect.genericReason", "Internal Exception: " + t);
		}

		this.closeChannel(chatcomponenttranslation);
	}

	protected void channelRead0(ChannelHandlerContext context, Packet packet) {
		if (!this.channel.isOpen()) return;
		try {
			packet.processPacket(this.packetListener);
		} catch (ThreadQuickExitException ignored) {}
	}

	/**
	 * Sets the NetHandler for this NetworkManager, no checks are made if this handler is suitable for the particular
	 * connection state (protocol)
	 */
	public void setNetHandler(INetHandler handler) {
		Validate.notNull(handler, "packetListener");
		Log.MAIN.debug("Set listener of " + this + " to " + handler);
		this.packetListener = handler;
	}

	public void sendPacket(Packet packetIn) {
		if (this.isChannelOpen()) {
			this.flushOutboundQueue();
			this.dispatchPacket(packetIn, null);
		} else {
			this.lock.writeLock().lock();

			try {
				this.outboundPacketsQueue.add(new NetworkManager.InboundHandlerTuplePacketListener(packetIn, null));
			} finally {
				this.lock.writeLock().unlock();
			}
		}
	}

	@SafeVarargs
	public final void sendPacket(Packet packetIn, GenericFutureListener<? extends Future<? super Void>> listener, GenericFutureListener<? extends Future<? super Void>>... listeners) {
		if (this.isChannelOpen()) {
			this.flushOutboundQueue();
			this.dispatchPacket(packetIn, ArrayUtils.add(listeners, 0, listener));
		} else {
			this.lock.writeLock().lock();

			try {
				this.outboundPacketsQueue.add(new NetworkManager.InboundHandlerTuplePacketListener(packetIn, ArrayUtils.add(listeners, 0, listener)));
			} finally {
				this.lock.writeLock().unlock();
			}
		}
	}

	/**
	 * Will commit the packet to the channel. If the current thread 'owns' the channel it will write and flush the
	 * packet, otherwise it will add a task for the channel eventloop thread to do that.
	 */
	private void dispatchPacket(final Packet inPacket, final GenericFutureListener<? extends Future<? super Void>>[] futureListeners) {
		if (this.channel.eventLoop().inEventLoop()) {
			ChannelFuture f = this.channel.writeAndFlush(inPacket);
			if (futureListeners != null) f.addListeners(futureListeners);
			f.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
			inPacket.endSend(this);
		} else {
			this.channel.eventLoop().execute(() -> {
				ChannelFuture f = this.channel.writeAndFlush(inPacket);
				if (futureListeners != null) f.addListeners(futureListeners);
				f.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
				inPacket.endSend(this);
			});
		}
	}

	/**
	 * Will iterate through the outboundPacketQueue and dispatch all Packets
	 */
	private void flushOutboundQueue() {
		if (this.channel != null && this.channel.isOpen()) {
			this.lock.readLock().lock();

			try {
				while (!this.outboundPacketsQueue.isEmpty()) {
					NetworkManager.InboundHandlerTuplePacketListener networkmanager$inboundhandlertuplepacketlistener = this.outboundPacketsQueue.poll();
					this.dispatchPacket(networkmanager$inboundhandlertuplepacketlistener.packet, networkmanager$inboundhandlertuplepacketlistener.futureListeners);
				}
			} finally {
				this.lock.readLock().unlock();
			}
		}
	}

	/**
	 * Checks timeouts and processes all packets received
	 */
	public void processReceivedPackets() {
		this.flushOutboundQueue();

		if (this.packetListener instanceof ITickable) {
			((ITickable) this.packetListener).update();
		}

		this.channel.flush();
	}

	/**
	 * Returns the socket address of the remote side. Server-only.
	 */
	public SocketAddress getRemoteAddress() {
		return this.socketAddress;
	}

	/**
	 * Closes the channel, the parameter can be used for an exit message (not certain how it gets sent)
	 */
	public void closeChannel(IChatComponent message) {
		if (this.channel.isOpen()) {
			this.channel.close().awaitUninterruptibly();
			this.terminationReason = message;
		}
	}

	/**
	 * True if this NetworkManager uses a memory connection (single player game). False may imply both an active TCP
	 * connection or simply no active connection at all
	 */
	public boolean isLocalChannel() {
		return this.channel instanceof LocalChannel || this.channel instanceof LocalServerChannel;
	}

	public static NetworkManager func_181124_a(InetAddress p_181124_0_, int p_181124_1_, boolean useEpoll) {
		final NetworkManager networkmanager = new NetworkManager(false);
		Class<? extends SocketChannel> oclass;
		LazySupplier<? extends EventLoopGroup> lazyloadbase;

		if (Epoll.isAvailable() && useEpoll) {
			oclass = EpollSocketChannel.class;
			lazyloadbase = EPOLL_CLIENT;
		} else {
			oclass = NioSocketChannel.class;
			lazyloadbase = NIO_CLIENT;
		}

		new Bootstrap().group(lazyloadbase.getValue()).handler(new ChannelInitializer<Channel>() {
			protected void initChannel(Channel channel) throws Exception {
				try {
					channel.config().setOption(ChannelOption.TCP_NODELAY, Boolean.TRUE);
				} catch (ChannelException ignored) {
				}

				channel.pipeline()
						.addLast("timeout", new ReadTimeoutHandler(30))
						.addLast("splitter", new NettyCommunication.Splitter())
						.addLast("decoder", new NettyCommunication.Decoder(false))
						.addLast("prepender", new NettyCommunication.Prepender())
						.addLast("encoder", new NettyCommunication.Encoder(true))
						.addLast("packet_handler", networkmanager);
			}
		}).channel(oclass).connect(p_181124_0_, p_181124_1_).syncUninterruptibly();
		return networkmanager;
	}

	/**
	 * Prepares a clientside NetworkManager: establishes a connection to the socket supplied and configures the channel
	 * pipeline. Returns the newly created instance.
	 */
	public static NetworkManager provideLocalClient(SocketAddress address) {
		final NetworkManager networkmanager = new NetworkManager(false);
		new Bootstrap().group(LOCAL_CLIENT.getValue()).handler(new ChannelInitializer<Channel>() {
			protected void initChannel(Channel p_initChannel_1_) throws Exception {
				p_initChannel_1_.pipeline().addLast("packet_handler", networkmanager);
			}
		}).channel(LocalChannel.class).connect(address).syncUninterruptibly();
		return networkmanager;
	}

	/**
	 * Adds an encoder+decoder to the channel pipeline. The parameter is the secret key used for encrypted communication
	 */
	public void enableEncryption(SecretKey key) {
		this.isEncrypted = true;
		this.channel.pipeline().addBefore("splitter", "decrypt", new NettyEncryptingDecoder(CryptManager.createNetCipherInstance(2, key)));
		this.channel.pipeline().addBefore("prepender", "encrypt", new NettyEncryptingEncoder(CryptManager.createNetCipherInstance(1, key)));
	}

	public boolean getIsencrypted() {
		return this.isEncrypted;
	}

	/**
	 * Returns true if this NetworkManager has an active channel, false otherwise
	 */
	public boolean isChannelOpen() {
		return this.channel != null && this.channel.isOpen();
	}

	public boolean hasNoChannel() {
		return this.channel == null;
	}

	/**
	 * Gets the current handler for processing packets
	 */
	public INetHandler getNetHandler() {
		return this.packetListener;
	}

	/**
	 * If this channel is closed, returns the exit message, null otherwise.
	 */
	public IChatComponent getExitMessage() {
		return this.terminationReason;
	}

	/**
	 * Switches the channel to manual reading modus
	 */
	public void disableAutoRead() {
		this.channel.config().setAutoRead(false);
	}

	public void setCompressionTreshold(int treshold) {
		if (treshold >= 0) {
			if (this.channel.pipeline().get("decompress") instanceof NettyCompressionDecoder) {
				((NettyCompressionDecoder) this.channel.pipeline().get("decompress")).setCompressionTreshold(treshold);
			} else {
				this.channel.pipeline().addBefore("decoder", "decompress", new NettyCompressionDecoder(treshold));
			}

			if (this.channel.pipeline().get("compress") instanceof NettyCompressionEncoder) {
				((NettyCompressionEncoder) this.channel.pipeline().get("decompress")).setCompressionTreshold(treshold);
			} else {
				this.channel.pipeline().addBefore("encoder", "compress", new NettyCompressionEncoder(treshold));
			}
		} else {
			if (this.channel.pipeline().get("decompress") instanceof NettyCompressionDecoder) {
				this.channel.pipeline().remove("decompress");
			}

			if (this.channel.pipeline().get("compress") instanceof NettyCompressionEncoder) {
				this.channel.pipeline().remove("compress");
			}
		}
	}

	public void checkDisconnected() {
		if (this.channel != null && !this.channel.isOpen()) {
			if (!this.disconnected) {
				this.disconnected = true;

				if (this.getExitMessage() != null) {
					this.getNetHandler().onDisconnect(this.getExitMessage());
				} else if (this.getNetHandler() != null) {
					this.getNetHandler().onDisconnect(new ChatComponentText("Disconnected"));
				}
			} else {
				logger.warn("handleDisconnection() called twice");
			}
		}
	}

	static class InboundHandlerTuplePacketListener {

		private final Packet packet;
		private final GenericFutureListener<? extends Future<? super Void>>[] futureListeners;

		public InboundHandlerTuplePacketListener(Packet inPacket, GenericFutureListener<? extends Future<? super Void>>[] inFutureListeners) {
			this.packet = inPacket;
			this.futureListeners = inFutureListeners;
		}

	}

}
