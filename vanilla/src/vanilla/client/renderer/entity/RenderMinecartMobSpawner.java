package vanilla.client.renderer.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderMinecart;
import vanilla.client.renderer.tileentity.TileEntityMobSpawnerRenderer;
import vanilla.entity.ai.EntityMinecartMobSpawner;
import net.minecraft.init.Blocks;

public class RenderMinecartMobSpawner extends RenderMinecart<EntityMinecartMobSpawner> {

	public RenderMinecartMobSpawner(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}

	protected void renderTileInside(EntityMinecartMobSpawner minecart, float partialTicks, IBlockState state) {
		super.renderTileInside(minecart, partialTicks, state);

		if (state.getBlock() == Blocks.mob_spawner) {
			TileEntityMobSpawnerRenderer.renderMob(minecart.func_98039_d(), minecart.posX, minecart.posY, minecart.posZ, partialTicks);
		}
	}

}
