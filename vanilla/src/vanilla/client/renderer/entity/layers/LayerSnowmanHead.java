package vanilla.client.renderer.entity.layers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.G;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import vanilla.client.renderer.entity.vanilla.RenderSnowMan;
import vanilla.entity.monster.EntitySnowman;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class LayerSnowmanHead implements LayerRenderer<EntitySnowman> {

	private final RenderSnowMan snowManRenderer;

	public LayerSnowmanHead(RenderSnowMan snowManRendererIn) {
		this.snowManRenderer = snowManRendererIn;
	}

	public void doRenderLayer(EntitySnowman entitylivingbaseIn, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
		if (!entitylivingbaseIn.isInvisible()) {
			G.pushMatrix();
			this.snowManRenderer.getMainModel().head.postRender(0.0625F);
			float f = 0.625F;
			G.translate(0.0F, -0.34375F, 0.0F);
			G.rotate(180.0F, 0.0F, 1.0F, 0.0F);
			G.scale(f, -f, -f);
			Minecraft.get().getItemRenderer().renderItem(entitylivingbaseIn, new ItemStack(Blocks.pumpkin, 1), ItemCameraTransforms.TransformType.HEAD);
			G.popMatrix();
		}
	}

	public boolean shouldCombineTextures() {
		return true;
	}

}
