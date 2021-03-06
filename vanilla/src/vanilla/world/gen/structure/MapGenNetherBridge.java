package vanilla.world.gen.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vanilla.entity.monster.EntityBlaze;
import vanilla.entity.monster.EntityMagmaCube;
import vanilla.entity.monster.EntityPigZombie;
import vanilla.entity.monster.EntitySkeleton;
import net.minecraft.world.World;
import net.minecraft.world.biome.SpawnListEntry;

public class MapGenNetherBridge extends MapGenStructure {

	private List<SpawnListEntry> spawnList = new ArrayList<>();

	public MapGenNetherBridge() {
		this.spawnList.add(new SpawnListEntry(EntityBlaze.class, 10, 2, 3));
		this.spawnList.add(new SpawnListEntry(EntityPigZombie.class, 5, 4, 4));
		this.spawnList.add(new SpawnListEntry(EntitySkeleton.class, 10, 4, 4));
		this.spawnList.add(new SpawnListEntry(EntityMagmaCube.class, 3, 4, 4));
	}

	public String getStructureName() {
		return "Fortress";
	}

	public List<SpawnListEntry> getSpawnList() {
		return this.spawnList;
	}

	protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
		int i = chunkX >> 4;
		int j = chunkZ >> 4;
		this.rand.setSeed((long) (i ^ j << 4) ^ this.worldObj.getSeed());
		this.rand.nextInt();
		return this.rand.nextInt(3) != 0 ? false : chunkX != (i << 4) + 4 + this.rand.nextInt(8) ? false : chunkZ == (j << 4) + 4 + this.rand.nextInt(8);
	}

	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		return new MapGenNetherBridge.Start(this.worldObj, this.rand, chunkX, chunkZ);
	}

	public static class Start extends StructureStart {

		public Start() {
		}

		public Start(World worldIn, Random p_i2040_2_, int p_i2040_3_, int p_i2040_4_) {
			super(p_i2040_3_, p_i2040_4_);
			StructureNetherBridgePieces.Start structurenetherbridgepieces$start = new StructureNetherBridgePieces.Start(p_i2040_2_, (p_i2040_3_ << 4) + 2, (p_i2040_4_ << 4) + 2);
			this.components.add(structurenetherbridgepieces$start);
			structurenetherbridgepieces$start.buildComponent(structurenetherbridgepieces$start, this.components, p_i2040_2_);
			List<StructureComponent> list = structurenetherbridgepieces$start.field_74967_d;

			while (!list.isEmpty()) {
				int i = p_i2040_2_.nextInt(list.size());
				StructureComponent structurecomponent = (StructureComponent) list.remove(i);
				structurecomponent.buildComponent(structurenetherbridgepieces$start, this.components, p_i2040_2_);
			}

			this.updateBoundingBox();
			this.setRandomHeight(worldIn, p_i2040_2_, 48, 70);
		}

	}

}
