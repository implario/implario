package vanilla.world;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.Player;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import vanilla.entity.EntitySpawnPlacementRegistry;
import vanilla.entity.EnumCreatureType;
import vanilla.entity.SpawnPlacementType;
import vanilla.entity.VanillaEntity;
import vanilla.world.biome.BiomeGenBase;

import java.util.List;
import java.util.Random;
import java.util.Set;

public final class SpawnerAnimals {

	private static final int MOB_COUNT_DIV = (int) Math.pow(17.0D, 2.0D);
	private final Set<ChunkCoordIntPair> eligibleChunksForSpawning = Sets.newHashSet();

	/**
	 * adds all chunks within the spawn radius of the players to eligibleChunksForSpawning. pars: the world,
	 * hostileCreatures, passiveCreatures. returns number of eligible chunks.
	 */
	public int findChunksForSpawning(WorldServer world, boolean spawnHostileMobs, boolean spawnPeacefulMobs, boolean p_77192_4_) {
		if (!(world instanceof VanillaWorldServer)) return 0;
		VanillaWorldServer w = (VanillaWorldServer) world;
		if (!spawnHostileMobs && !spawnPeacefulMobs) {
			return 0;
		}
		this.eligibleChunksForSpawning.clear();
		int i = 0;

		for (Player entityplayer : w.playerEntities) {
			if (!entityplayer.isSpectator()) {
				int j = MathHelper.floor_double(entityplayer.posX / 16.0D);
				int k = MathHelper.floor_double(entityplayer.posZ / 16.0D);
				int l = 8;

				for (int i1 = -l; i1 <= l; ++i1) {
					for (int j1 = -l; j1 <= l; ++j1) {
						boolean flag = i1 == -l || i1 == l || j1 == -l || j1 == l;
						ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i1 + j, j1 + k);

						if (!this.eligibleChunksForSpawning.contains(chunkcoordintpair)) {
							++i;

							if (!flag && w.getWorldBorder().contains(chunkcoordintpair)) {
								this.eligibleChunksForSpawning.add(chunkcoordintpair);
							}
						}
					}
				}
			}
		}

		int i4 = 0;
		BlockPos blockpos2 = w.getSpawnPoint();

		for (EnumCreatureType type : EnumCreatureType.values()) {
			if ((!type.getPeacefulCreature() || spawnPeacefulMobs) && (type.getPeacefulCreature() || spawnHostileMobs) && (!type.getAnimal() || p_77192_4_)) {
				int actualEntitiesAmount = w.countEntities(type.getCreatureClass());
				int maxEntitiesAmount = type.getMaxNumberOfCreature() * i / MOB_COUNT_DIV;

				if (actualEntitiesAmount <= maxEntitiesAmount) {
					label374:

					for (ChunkCoordIntPair chunkcoordintpair1 : this.eligibleChunksForSpawning) {
						BlockPos blockpos = getRandomChunkPosition(w, chunkcoordintpair1.chunkXPos, chunkcoordintpair1.chunkZPos);
						int k1 = blockpos.getX();
						int l1 = blockpos.getY();
						int i2 = blockpos.getZ();
						Block block = w.getBlockState(blockpos).getBlock();

						if (!block.isNormalCube()) {
							int j2 = 0;

							for (int k2 = 0; k2 < 3; ++k2) {
								int l2 = k1;
								int i3 = l1;
								int j3 = i2;
								int k3 = 6;
								SpawnListEntry entry = null;
								IEntityLivingData ientitylivingdata = null;

								for (int l3 = 0; l3 < 4; ++l3) {
									l2 += w.rand.nextInt(k3) - w.rand.nextInt(k3);
									i3 += w.rand.nextInt(1) - w.rand.nextInt(1);
									j3 += w.rand.nextInt(k3) - w.rand.nextInt(k3);
									BlockPos blockpos1 = new BlockPos(l2, i3, j3);
									float f = (float) l2 + 0.5F;
									float f1 = (float) j3 + 0.5F;

									if (!w.isAnyPlayerWithinRangeAt((double) f, (double) i3, (double) f1, 24.0D) && blockpos2.distanceSq((double) f, (double) i3, (double) f1) >= 576.0D) {
										if (entry == null) {
											entry = w.getSpawnListEntryForTypeAt(type, blockpos1);
											if (entry == null) {
												break;
											}
										}

										if (w.canCreatureTypeSpawnHere(type, entry, blockpos1) && canCreatureTypeSpawnAtLocation(
												EntitySpawnPlacementRegistry.getPlacementForEntity(entry.entityClass), w, blockpos1)) {
											VanillaEntity entityliving;

											try {
												entityliving = (VanillaEntity) entry.entityClass.getConstructor(new Class[] {World.class}).newInstance(new Object[] {w});
											} catch (Exception exception) {
												exception.printStackTrace();
												return i4;
											}

											entityliving.setLocationAndAngles((double) f, (double) i3, (double) f1, w.rand.nextFloat() * 360.0F, 0.0F);

											if (entityliving.getCanSpawnHere() && entityliving.isNotColliding()) {
												ientitylivingdata = entityliving.onInitialSpawn(w.getDifficultyForLocation(new BlockPos(entityliving)), ientitylivingdata);

												if (entityliving.isNotColliding()) {
													++j2;
													w.spawnEntityInWorld(entityliving);
												}

												if (j2 >= entityliving.getMaxSpawnedInChunk()) {
													continue label374;
												}
											}

											i4 += j2;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return i4;
	}

	protected static BlockPos getRandomChunkPosition(World worldIn, int x, int z) {
		Chunk chunk = worldIn.getChunkFromChunkCoords(x, z);
		int i = x * 16 + worldIn.rand.nextInt(16);
		int j = z * 16 + worldIn.rand.nextInt(16);
		int k = MathHelper.func_154354_b(chunk.getHeight(new BlockPos(i, 0, j)) + 1, 16);
		int l = worldIn.rand.nextInt(k > 0 ? k : chunk.getTopFilledSegment() + 16 - 1);
		return new BlockPos(i, l, j);
	}

	public static boolean canCreatureTypeSpawnAtLocation(SpawnPlacementType p_180267_0_, World worldIn, BlockPos pos) {
		if (!worldIn.getWorldBorder().contains(pos)) {
			return false;
		}
		Block block = worldIn.getBlockState(pos).getBlock();

		if (p_180267_0_ == SpawnPlacementType.IN_WATER) {
			return block.getMaterial().isLiquid() && worldIn.getBlockState(pos.down()).getBlock().getMaterial().isLiquid() && !worldIn.getBlockState(pos.up()).getBlock().isNormalCube();
		}
		BlockPos blockpos = pos.down();

		if (!World.doesBlockHaveSolidTopSurface(worldIn, blockpos)) {
			return false;
		}
		Block block1 = worldIn.getBlockState(blockpos).getBlock();
		boolean flag = block1 != Blocks.bedrock && block1 != Blocks.barrier;
		return flag && !block.isNormalCube() && !block.getMaterial().isLiquid() && !worldIn.getBlockState(pos.up()).getBlock().isNormalCube();
	}

	/**
	 * Called during chunk generation to spawn initial creatures.
	 */
	public static void performWorldGenSpawning(World worldIn, Biome b, int p_77191_2_, int p_77191_3_, int p_77191_4_, int p_77191_5_, Random p_77191_6_) {
		if (!(b instanceof BiomeGenBase)) return;
		BiomeGenBase biome = (BiomeGenBase) b;
		List<SpawnListEntry> list = biome.getSpawnableList(EnumCreatureType.CREATURE);

		if (!list.isEmpty()) {
			while (p_77191_6_.nextFloat() < biome.getSpawningChance()) {
				SpawnListEntry biomegenbase$spawnlistentry = WeightedRandom.getRandomItem(worldIn.rand, list);
				int i = biomegenbase$spawnlistentry.minGroupCount + p_77191_6_.nextInt(1 + biomegenbase$spawnlistentry.maxGroupCount - biomegenbase$spawnlistentry.minGroupCount);
				IEntityLivingData ientitylivingdata = null;
				int j = p_77191_2_ + p_77191_6_.nextInt(p_77191_4_);
				int k = p_77191_3_ + p_77191_6_.nextInt(p_77191_5_);
				int l = j;
				int i1 = k;

				for (int j1 = 0; j1 < i; ++j1) {
					boolean flag = false;

					for (int k1 = 0; !flag && k1 < 4; ++k1) {
						BlockPos blockpos = worldIn.getTopSolidOrLiquidBlock(new BlockPos(j, 0, k));

						if (canCreatureTypeSpawnAtLocation(SpawnPlacementType.ON_GROUND, worldIn, blockpos)) {
							VanillaEntity entityliving;

							try {
								entityliving = (VanillaEntity) biomegenbase$spawnlistentry.entityClass.getConstructor(new Class[] {World.class}).newInstance(new Object[] {worldIn});
							} catch (Exception exception) {
								exception.printStackTrace();
								continue;
							}

							entityliving.setLocationAndAngles((double) ((float) j + 0.5F), (double) blockpos.getY(), (double) ((float) k + 0.5F), p_77191_6_.nextFloat() * 360.0F, 0.0F);
							worldIn.spawnEntityInWorld(entityliving);
							ientitylivingdata = entityliving.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityliving)), ientitylivingdata);
							flag = true;
						}

						j += p_77191_6_.nextInt(5) - p_77191_6_.nextInt(5);

						for (k += p_77191_6_.nextInt(5) - p_77191_6_.nextInt(
								5); j < p_77191_2_ || j >= p_77191_2_ + p_77191_4_ || k < p_77191_3_ || k >= p_77191_3_ + p_77191_4_; k = i1 + p_77191_6_.nextInt(5) - p_77191_6_.nextInt(5)) {
							j = l + p_77191_6_.nextInt(5) - p_77191_6_.nextInt(5);
						}
					}
				}
			}
		}
	}

}
