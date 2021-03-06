package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.G;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.Player;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3d;

public class RenderFish extends Render<EntityFishHook> {

	private static final ResourceLocation FISH_PARTICLES = new ResourceLocation("textures/particle/particles.png");

	public RenderFish(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
	 * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
	 * (Render<T extends Entity>) and this method has signature public void doRender(T entity, double d, double d1,
	 * double d2, float f, float f1). But JAD is pre 1.5 so doe
	 */
	public void doRender(EntityFishHook entity, double x, double y, double z, float entityYaw, float partialTicks) {
		G.pushMatrix();
		G.translate((float) x, (float) y, (float) z);
		G.enableRescaleNormal();
		G.scale(0.5F, 0.5F, 0.5F);
		this.bindEntityTexture(entity);
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		int i = 1;
		int j = 2;
		float f = 0.0625F;
		float f1 = 0.125F;
		float f2 = 0.125F;
		float f3 = 0.1875F;
		float f4 = 1.0F;
		float f5 = 0.5F;
		float f6 = 0.5F;
		G.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		G.rotate(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
		worldrenderer.pos(-0.5D, -0.5D, 0.0D).tex(0.0625D, 0.1875D).normal(0.0F, 1.0F, 0.0F).endVertex();
		worldrenderer.pos(0.5D, -0.5D, 0.0D).tex(0.125D, 0.1875D).normal(0.0F, 1.0F, 0.0F).endVertex();
		worldrenderer.pos(0.5D, 0.5D, 0.0D).tex(0.125D, 0.125D).normal(0.0F, 1.0F, 0.0F).endVertex();
		worldrenderer.pos(-0.5D, 0.5D, 0.0D).tex(0.0625D, 0.125D).normal(0.0F, 1.0F, 0.0F).endVertex();
		tessellator.draw();
		G.disableRescaleNormal();
		G.popMatrix();

		Player player = entity.angler;
		if (player != null) {
			float f7 = player.getSwingProgress(partialTicks);
			float f8 = MathHelper.sin(MathHelper.sqrt_float(f7) * (float) Math.PI);
			Vec3d vec3D = new Vec3d(-0.36D, 0.03D, 0.35D);
			vec3D = vec3D.rotatePitch(-(player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * partialTicks) * (float) Math.PI / 180.0F);
			vec3D = vec3D.rotateYaw(-(player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * partialTicks) * (float) Math.PI / 180.0F);
			vec3D = vec3D.rotateYaw(f8 * 0.5F);
			vec3D = vec3D.rotatePitch(-f8 * 0.7F);
			double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) partialTicks + vec3D.xCoord;
			double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) partialTicks + vec3D.yCoord;
			double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) partialTicks + vec3D.zCoord;
			double d3 = (double) player.getEyeHeight();

			//            if (Settings.getPerspective() > 0 || entity.angler != Minecraft.getMinecraft().thePlayer)
			//            {
			//                float f9 = (entity.angler.prevRenderYawOffset + (entity.angler.renderYawOffset - entity.angler.prevRenderYawOffset) * partialTicks) * (float)Math.PI / 180.0F;
			//                double d4 = (double)MathHelper.sin(f9);
			//                double d6 = (double)MathHelper.cos(f9);
			//                double d8 = 0.35D;
			//                double d10 = 0.8D;
			//                d0 = entity.angler.prevPosX + (entity.angler.posX - entity.angler.prevPosX) * (double)partialTicks - d6 * 0.35D - d4 * 0.8D;
			//                d1 = entity.angler.prevPosY + d3 + (entity.angler.posY - entity.angler.prevPosY) * (double)partialTicks - 0.45D;
			//                d2 = entity.angler.prevPosZ + (entity.angler.posZ - entity.angler.prevPosZ) * (double)partialTicks - d4 * 0.35D + d6 * 0.8D;
			//                d3 = entity.angler.isSneaking() ? -0.1875D : 0.0D;
			//            }

			double d13 = entity.prevPosX + (entity.posX - entity.prevPosX) * (double) partialTicks;
			double d5 = entity.prevPosY + (entity.posY - entity.prevPosY) * (double) partialTicks + 0.25D;
			double d7 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double) partialTicks;
			double d9 = (double) (float) (d0 - d13);
			double d11 = (double) (float) (d1 - d5) + d3;
			double d12 = (double) (float) (d2 - d7);
			G.disableTexture2D();
			G.disableLighting();
			worldrenderer.begin(3, DefaultVertexFormats.POSITION_COLOR);
			int k = 2;

			for (int l = 0; l <= k; ++l) {
				float f10 = (float) l / (float) k;
				//				System.out.println((x + d9 * (double)f10) + "    " + (y + d11 * (double)(f10 * f10 + f10) * 0.5D + 0.25D) + "    " + (z + d12 * (double)f10));
				worldrenderer.pos(x + d9 * (double) f10, y + d11 * (double) (f10 * f10 + f10) * 0.5D + 0.25D, z + d12 * (double) f10).color(127, 0, 0, 255).endVertex();
			}

			tessellator.draw();
			G.enableLighting();
			G.enableTexture2D();
			super.doRender(entity, x, y, z, entityYaw, partialTicks);
		}
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntityFishHook entity) {
		return FISH_PARTICLES;
	}

}
