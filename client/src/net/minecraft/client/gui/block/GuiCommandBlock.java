package net.minecraft.client.gui.block;

import io.netty.buffer.Unpooled;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.element.GuiButton;
import net.minecraft.client.gui.element.GuiTextField;
import net.minecraft.client.resources.Lang;
import net.minecraft.command.legacy.CommandBlockLogic;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.protocol.minecraft_47.play.client.C17PacketCustomPayload;
import net.minecraft.util.IChatComponent;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class GuiCommandBlock extends GuiScreen {

	/**
	 * Text field containing the command block's command.
	 */
	private GuiTextField commandTextField;
	private GuiTextField previousOutputTextField;

	/**
	 * Command block being edited.
	 */
	private final CommandBlockLogic localCommandBlock;

	/**
	 * "Done" button for the GUI.
	 */
	private GuiButton doneBtn;
	private GuiButton cancelBtn;
	private GuiButton field_175390_s;
	private boolean field_175389_t;

	public GuiCommandBlock(CommandBlockLogic p_i45032_1_) {
		this.localCommandBlock = p_i45032_1_;
	}

	/**
	 * Called from the main game loop to update the screen.
	 */
	public void updateScreen() {
		this.commandTextField.updateCursorCounter();
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
	 * window resizes, the buttonList is cleared beforehand.
	 */
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(this.doneBtn = new GuiButton(0, this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, Lang.format("gui.done", new Object[0])));
		this.buttonList.add(this.cancelBtn = new GuiButton(1, this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, Lang.format("gui.cancel", new Object[0])));
		this.buttonList.add(this.field_175390_s = new GuiButton(4, this.width / 2 + 150 - 20, 150, 20, 20, "O"));
		this.commandTextField = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 150, 50, 300, 20);
		this.commandTextField.setMaxStringLength(32767);
		this.commandTextField.setFocused(true);
		this.commandTextField.setText(this.localCommandBlock.getCommand());
		this.previousOutputTextField = new GuiTextField(3, this.fontRendererObj, this.width / 2 - 150, 150, 276, 20);
		this.previousOutputTextField.setMaxStringLength(32767);
		this.previousOutputTextField.setEnabled(false);
		this.previousOutputTextField.setText("-");
		this.field_175389_t = this.localCommandBlock.shouldTrackOutput();
		this.func_175388_a();
		this.doneBtn.enabled = this.commandTextField.getText().trim().length() > 0;
	}

	/**
	 * Called when the screen is unloaded. Used to disable keyboard repeat events
	 */
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	/**
	 * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
	 */
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.enabled) {
			if (button.id == 1) {
				this.localCommandBlock.setTrackOutput(this.field_175389_t);
				this.mc.displayGuiScreen((GuiScreen) null);
			} else if (button.id == 0) {
				PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
				packetbuffer.writeByte(this.localCommandBlock.func_145751_f());
				this.localCommandBlock.func_145757_a(packetbuffer);
				packetbuffer.writeString(this.commandTextField.getText());
				packetbuffer.writeBoolean(this.localCommandBlock.shouldTrackOutput());
				this.mc.getNetHandler().addToSendQueue(new C17PacketCustomPayload("MC|AdvCdm", packetbuffer));

				if (!this.localCommandBlock.shouldTrackOutput()) {
					this.localCommandBlock.setLastOutput((IChatComponent) null);
				}

				this.mc.displayGuiScreen((GuiScreen) null);
			} else if (button.id == 4) {
				this.localCommandBlock.setTrackOutput(!this.localCommandBlock.shouldTrackOutput());
				this.func_175388_a();
			}
		}
	}

	/**
	 * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
	 * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
	 */
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		this.commandTextField.textboxKeyTyped(typedChar, keyCode);
		this.previousOutputTextField.textboxKeyTyped(typedChar, keyCode);
		this.doneBtn.enabled = this.commandTextField.getText().trim().length() > 0;

		if (keyCode != 28 && keyCode != 156) {
			if (keyCode == 1) {
				this.actionPerformed(this.cancelBtn);
			}
		} else {
			this.actionPerformed(this.doneBtn);
		}
	}

	/**
	 * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
	 */
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		this.commandTextField.mouseClicked(mouseX, mouseY, mouseButton);
		this.previousOutputTextField.mouseClicked(mouseX, mouseY, mouseButton);
	}

	/**
	 * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
	 */
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, Lang.format("advMode.setCommand", new Object[0]), this.width / 2, 20, 16777215);
		this.drawString(this.fontRendererObj, Lang.format("advMode.command", new Object[0]), this.width / 2 - 150, 37, 10526880);
		this.commandTextField.drawTextBox();
		int i = 75;
		int j = 0;
		this.drawString(this.fontRendererObj, Lang.format("advMode.nearestPlayer", new Object[0]), this.width / 2 - 150, i + j++ * this.fontRendererObj.getFontHeight(), 10526880);
		this.drawString(this.fontRendererObj, Lang.format("advMode.randomPlayer", new Object[0]), this.width / 2 - 150, i + j++ * this.fontRendererObj.getFontHeight(), 10526880);
		this.drawString(this.fontRendererObj, Lang.format("advMode.allPlayers", new Object[0]), this.width / 2 - 150, i + j++ * this.fontRendererObj.getFontHeight(), 10526880);
		this.drawString(this.fontRendererObj, Lang.format("advMode.allEntities", new Object[0]), this.width / 2 - 150, i + j++ * this.fontRendererObj.getFontHeight(), 10526880);
		this.drawString(this.fontRendererObj, "", this.width / 2 - 150, i + j++ * this.fontRendererObj.getFontHeight(), 10526880);

		if (this.previousOutputTextField.getText().length() > 0) {
			i = i + j * this.fontRendererObj.getFontHeight() + 16;
			this.drawString(this.fontRendererObj, Lang.format("advMode.previousOutput", new Object[0]), this.width / 2 - 150, i, 10526880);
			this.previousOutputTextField.drawTextBox();
		}

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	private void func_175388_a() {
		if (this.localCommandBlock.shouldTrackOutput()) {
			this.field_175390_s.displayString = "O";

			if (this.localCommandBlock.getLastOutput() != null) {
				this.previousOutputTextField.setText(this.localCommandBlock.getLastOutput().getUnformattedText());
			}
		} else {
			this.field_175390_s.displayString = "X";
			this.previousOutputTextField.setText("-");
		}
	}

}
