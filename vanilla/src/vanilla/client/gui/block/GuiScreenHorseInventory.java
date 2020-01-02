package vanilla.client.gui.block;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.G;
import net.minecraft.inventory.Inventory;
import net.minecraft.util.ResourceLocation;
import vanilla.entity.passive.EntityHorse;
import vanilla.inventory.ContainerHorseInventory;

public class GuiScreenHorseInventory extends GuiContainer {

	private static final ResourceLocation horseGuiTextures = new ResourceLocation("textures/gui/container/horse.png");

	/**
	 * The player inventory bound to this GUI.
	 */
	private Inventory playerInventory;

	/**
	 * The horse inventory bound to this GUI.
	 */
	private Inventory horseInventory;

	/**
	 * The EntityHorse whose inventory is currently being accessed.
	 */
	private EntityHorse horseEntity;

	/**
	 * The mouse x-position recorded during the last rendered frame.
	 */
	private float mousePosx;

	/**
	 * The mouse y-position recorded during the last renderered frame.
	 */
	private float mousePosY;

	public GuiScreenHorseInventory(Inventory playerInv, Inventory horseInv, EntityHorse horse) {
		super(new ContainerHorseInventory(playerInv, horseInv, horse, Minecraft.getMinecraft().thePlayer));
		this.playerInventory = playerInv;
		this.horseInventory = horseInv;
		this.horseEntity = horse;
		this.allowUserInput = false;
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items). Args : mouseX, mouseY
	 */
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRendererObj.drawString(this.horseInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
		this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}

	/**
	 * Args : renderPartialTicks, mouseX, mouseY
	 */
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		G.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(horseGuiTextures);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		if (this.horseEntity.isChested()) {
			this.drawTexturedModalRect(i + 79, j + 17, 0, this.ySize, 90, 54);
		}

		if (this.horseEntity.canWearArmor()) {
			this.drawTexturedModalRect(i + 7, j + 35, 0, this.ySize + 54, 18, 18);
		}

		GuiInventory.drawEntityOnScreen(i + 51, j + 60, 17, (float) (i + 51) - this.mousePosx, (float) (j + 75 - 50) - this.mousePosY, this.horseEntity);
	}

	/**
	 * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
	 */
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.mousePosx = (float) mouseX;
		this.mousePosY = (float) mouseY;
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

}
