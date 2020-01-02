package net.minecraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.chat.ChatComponentText;
import net.minecraft.util.chat.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.LockableContainer;
import net.minecraft.world.LockCode;

public abstract class TileEntityLockable extends TileEntity implements IInteractionObject, LockableContainer {

	private LockCode code = LockCode.EMPTY_CODE;

	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.code = LockCode.fromNBT(compound);
	}

	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		if (this.code != null) {
			this.code.toNBT(compound);
		}
	}

	public boolean isLocked() {
		return this.code != null && !this.code.isEmpty();
	}

	public LockCode getLockCode() {
		return this.code;
	}

	public void setLockCode(LockCode code) {
		this.code = code;
	}

	/**
	 * Get the formatted ChatComponent that will be used for the sender's username in chat
	 */
	public IChatComponent getDisplayName() {
		return (IChatComponent) (this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName(), new Object[0]));
	}

}
