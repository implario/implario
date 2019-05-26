package vanilla.world.gen.provider;

import com.google.gson.*;
import net.minecraft.util.JsonUtils;
import vanilla.world.biome.BiomeGenBase;

import java.lang.reflect.Type;

public class ChunkProviderSettings {

	public final float coordinateScale;
	public final float heightScale;
	public final float upperLimitScale;
	public final float lowerLimitScale;
	public final float depthNoiseScaleX;
	public final float depthNoiseScaleZ;
	public final float depthNoiseScaleExponent;
	public final float mainNoiseScaleX;
	public final float mainNoiseScaleY;
	public final float mainNoiseScaleZ;
	public final float baseSize;
	public final float stretchY;
	public final float biomeDepthWeight;
	public final float biomeDepthOffSet;
	public final float biomeScaleWeight;
	public final float biomeScaleOffset;
	public final int seaLevel;
	public final boolean useCaves;
	public final boolean useDungeons;
	public final int dungeonChance;
	public final boolean useStrongholds;
	public final boolean useVillages;
	public final boolean useMineShafts;
	public final boolean useTemples;
	public final boolean useMonuments;
	public final boolean useRavines;
	public final boolean useWaterLakes;
	public final int waterLakeChance;
	public final boolean useLavaLakes;
	public final int lavaLakeChance;
	public final boolean useLavaOceans;
	public final int fixedBiome;
	public final int biomeSize;
	public final int riverSize;
	public final int dirtSize;
	public final int dirtCount;
	public final int dirtMinHeight;
	public final int dirtMaxHeight;
	public final int gravelSize;
	public final int gravelCount;
	public final int gravelMinHeight;
	public final int gravelMaxHeight;
	public final int graniteSize;
	public final int graniteCount;
	public final int graniteMinHeight;
	public final int graniteMaxHeight;
	public final int dioriteSize;
	public final int dioriteCount;
	public final int dioriteMinHeight;
	public final int dioriteMaxHeight;
	public final int andesiteSize;
	public final int andesiteCount;
	public final int andesiteMinHeight;
	public final int andesiteMaxHeight;
	public final int coalSize;
	public final int coalCount;
	public final int coalMinHeight;
	public final int coalMaxHeight;
	public final int ironSize;
	public final int ironCount;
	public final int ironMinHeight;
	public final int ironMaxHeight;
	public final int goldSize;
	public final int goldCount;
	public final int goldMinHeight;
	public final int goldMaxHeight;
	public final int redstoneSize;
	public final int redstoneCount;
	public final int redstoneMinHeight;
	public final int redstoneMaxHeight;
	public final int diamondSize;
	public final int diamondCount;
	public final int diamondMinHeight;
	public final int diamondMaxHeight;
	public final int lapisSize;
	public final int lapisCount;
	public final int lapisCenterHeight;
	public final int lapisSpread;

	private ChunkProviderSettings(ChunkProviderSettings.Factory settingsFactory) {
		this.coordinateScale = settingsFactory.coordinateScale;
		this.heightScale = settingsFactory.heightScale;
		this.upperLimitScale = settingsFactory.upperLimitScale;
		this.lowerLimitScale = settingsFactory.lowerLimitScale;
		this.depthNoiseScaleX = settingsFactory.depthNoiseScaleX;
		this.depthNoiseScaleZ = settingsFactory.depthNoiseScaleZ;
		this.depthNoiseScaleExponent = settingsFactory.depthNoiseScaleExponent;
		this.mainNoiseScaleX = settingsFactory.mainNoiseScaleX;
		this.mainNoiseScaleY = settingsFactory.mainNoiseScaleY;
		this.mainNoiseScaleZ = settingsFactory.mainNoiseScaleZ;
		this.baseSize = settingsFactory.baseSize;
		this.stretchY = settingsFactory.stretchY;
		this.biomeDepthWeight = settingsFactory.biomeDepthWeight;
		this.biomeDepthOffSet = settingsFactory.biomeDepthOffset;
		this.biomeScaleWeight = settingsFactory.biomeScaleWeight;
		this.biomeScaleOffset = settingsFactory.biomeScaleOffset;
		this.seaLevel = settingsFactory.seaLevel;
		this.useCaves = settingsFactory.useCaves;
		this.useDungeons = settingsFactory.useDungeons;
		this.dungeonChance = settingsFactory.dungeonChance;
		this.useStrongholds = settingsFactory.useStrongholds;
		this.useVillages = settingsFactory.useVillages;
		this.useMineShafts = settingsFactory.useMineShafts;
		this.useTemples = settingsFactory.useTemples;
		this.useMonuments = settingsFactory.useMonuments;
		this.useRavines = settingsFactory.useRavines;
		this.useWaterLakes = settingsFactory.useWaterLakes;
		this.waterLakeChance = settingsFactory.waterLakeChance;
		this.useLavaLakes = settingsFactory.useLavaLakes;
		this.lavaLakeChance = settingsFactory.lavaLakeChance;
		this.useLavaOceans = settingsFactory.useLavaOceans;
		this.fixedBiome = settingsFactory.fixedBiome;
		this.biomeSize = settingsFactory.biomeSize;
		this.riverSize = settingsFactory.riverSize;
		this.dirtSize = settingsFactory.dirtSize;
		this.dirtCount = settingsFactory.dirtCount;
		this.dirtMinHeight = settingsFactory.dirtMinHeight;
		this.dirtMaxHeight = settingsFactory.dirtMaxHeight;
		this.gravelSize = settingsFactory.gravelSize;
		this.gravelCount = settingsFactory.gravelCount;
		this.gravelMinHeight = settingsFactory.gravelMinHeight;
		this.gravelMaxHeight = settingsFactory.gravelMaxHeight;
		this.graniteSize = settingsFactory.graniteSize;
		this.graniteCount = settingsFactory.graniteCount;
		this.graniteMinHeight = settingsFactory.graniteMinHeight;
		this.graniteMaxHeight = settingsFactory.graniteMaxHeight;
		this.dioriteSize = settingsFactory.dioriteSize;
		this.dioriteCount = settingsFactory.dioriteCount;
		this.dioriteMinHeight = settingsFactory.dioriteMinHeight;
		this.dioriteMaxHeight = settingsFactory.dioriteMaxHeight;
		this.andesiteSize = settingsFactory.andesiteSize;
		this.andesiteCount = settingsFactory.andesiteCount;
		this.andesiteMinHeight = settingsFactory.andesiteMinHeight;
		this.andesiteMaxHeight = settingsFactory.andesiteMaxHeight;
		this.coalSize = settingsFactory.coalSize;
		this.coalCount = settingsFactory.coalCount;
		this.coalMinHeight = settingsFactory.coalMinHeight;
		this.coalMaxHeight = settingsFactory.coalMaxHeight;
		this.ironSize = settingsFactory.ironSize;
		this.ironCount = settingsFactory.ironCount;
		this.ironMinHeight = settingsFactory.ironMinHeight;
		this.ironMaxHeight = settingsFactory.ironMaxHeight;
		this.goldSize = settingsFactory.goldSize;
		this.goldCount = settingsFactory.goldCount;
		this.goldMinHeight = settingsFactory.goldMinHeight;
		this.goldMaxHeight = settingsFactory.goldMaxHeight;
		this.redstoneSize = settingsFactory.redstoneSize;
		this.redstoneCount = settingsFactory.redstoneCount;
		this.redstoneMinHeight = settingsFactory.redstoneMinHeight;
		this.redstoneMaxHeight = settingsFactory.redstoneMaxHeight;
		this.diamondSize = settingsFactory.diamondSize;
		this.diamondCount = settingsFactory.diamondCount;
		this.diamondMinHeight = settingsFactory.diamondMinHeight;
		this.diamondMaxHeight = settingsFactory.diamondMaxHeight;
		this.lapisSize = settingsFactory.lapisSize;
		this.lapisCount = settingsFactory.lapisCount;
		this.lapisCenterHeight = settingsFactory.lapisCenterHeight;
		this.lapisSpread = settingsFactory.lapisSpread;
	}

	public static class Factory {

		static final Gson JSON_ADAPTER = new GsonBuilder().registerTypeAdapter(ChunkProviderSettings.Factory.class, new ChunkProviderSettings.Serializer()).create();
		public float coordinateScale = 684.412F;
		public float heightScale = 684.412F;
		public float upperLimitScale = 512.0F;
		public float lowerLimitScale = 512.0F;
		public float depthNoiseScaleX = 200.0F;
		public float depthNoiseScaleZ = 200.0F;
		public float depthNoiseScaleExponent = 0.5F;
		public float mainNoiseScaleX = 80.0F;
		public float mainNoiseScaleY = 160.0F;
		public float mainNoiseScaleZ = 80.0F;
		public float baseSize = 8.5F;
		public float stretchY = 12.0F;
		public float biomeDepthWeight = 1.0F;
		public float biomeDepthOffset = 0.0F;
		public float biomeScaleWeight = 1.0F;
		public float biomeScaleOffset = 0.0F;
		public int seaLevel = 63;
		public boolean useCaves = true;
		public boolean useDungeons = true;
		public int dungeonChance = 8;
		public boolean useStrongholds = true;
		public boolean useVillages = true;
		public boolean useMineShafts = true;
		public boolean useTemples = true;
		public boolean useMonuments = true;
		public boolean useRavines = true;
		public boolean useWaterLakes = true;
		public int waterLakeChance = 4;
		public boolean useLavaLakes = true;
		public int lavaLakeChance = 80;
		public boolean useLavaOceans = false;
		public int fixedBiome = -1;
		public int biomeSize = 4;
		public int riverSize = 4;
		public int dirtSize = 33;
		public int dirtCount = 10;
		public int dirtMinHeight = 0;
		public int dirtMaxHeight = 256;
		public int gravelSize = 33;
		public int gravelCount = 8;
		public int gravelMinHeight = 0;
		public int gravelMaxHeight = 256;
		public int graniteSize = 33;
		public int graniteCount = 10;
		public int graniteMinHeight = 0;
		public int graniteMaxHeight = 80;
		public int dioriteSize = 33;
		public int dioriteCount = 10;
		public int dioriteMinHeight = 0;
		public int dioriteMaxHeight = 80;
		public int andesiteSize = 33;
		public int andesiteCount = 10;
		public int andesiteMinHeight = 0;
		public int andesiteMaxHeight = 80;
		public int coalSize = 17;
		public int coalCount = 20;
		public int coalMinHeight = 0;
		public int coalMaxHeight = 128;
		public int ironSize = 9;
		public int ironCount = 20;
		public int ironMinHeight = 0;
		public int ironMaxHeight = 64;
		public int goldSize = 9;
		public int goldCount = 2;
		public int goldMinHeight = 0;
		public int goldMaxHeight = 32;
		public int redstoneSize = 8;
		public int redstoneCount = 8;
		public int redstoneMinHeight = 0;
		public int redstoneMaxHeight = 16;
		public int diamondSize = 8;
		public int diamondCount = 1;
		public int diamondMinHeight = 0;
		public int diamondMaxHeight = 16;
		public int lapisSize = 7;
		public int lapisCount = 1;
		public int lapisCenterHeight = 16;
		public int lapisSpread = 16;

		public static ChunkProviderSettings.Factory jsonToFactory(String p_177865_0_) {
			if (p_177865_0_.length() == 0) {
				return new ChunkProviderSettings.Factory();
			}
			try {
				return JSON_ADAPTER.fromJson(p_177865_0_, Factory.class);
			} catch (Exception var2) {
				return new ChunkProviderSettings.Factory();
			}
		}

		public String toString() {
			return JSON_ADAPTER.toJson(this);
		}

		public Factory() {
			this.func_177863_a();
		}

		public void func_177863_a() {
			this.coordinateScale = 684.412F;
			this.heightScale = 684.412F;
			this.upperLimitScale = 512.0F;
			this.lowerLimitScale = 512.0F;
			this.depthNoiseScaleX = 200.0F;
			this.depthNoiseScaleZ = 200.0F;
			this.depthNoiseScaleExponent = 0.5F;
			this.mainNoiseScaleX = 80.0F;
			this.mainNoiseScaleY = 160.0F;
			this.mainNoiseScaleZ = 80.0F;
			this.baseSize = 8.5F;
			this.stretchY = 12.0F;
			this.biomeDepthWeight = 1.0F;
			this.biomeDepthOffset = 0.0F;
			this.biomeScaleWeight = 1.0F;
			this.biomeScaleOffset = 0.0F;
			this.seaLevel = 63;
			this.useCaves = true;
			this.useDungeons = true;
			this.dungeonChance = 8;
			this.useStrongholds = true;
			this.useVillages = true;
			this.useMineShafts = true;
			this.useTemples = true;
			this.useMonuments = true;
			this.useRavines = true;
			this.useWaterLakes = true;
			this.waterLakeChance = 4;
			this.useLavaLakes = true;
			this.lavaLakeChance = 80;
			this.useLavaOceans = false;
			this.fixedBiome = -1;
			this.biomeSize = 4;
			this.riverSize = 4;
			this.dirtSize = 33;
			this.dirtCount = 10;
			this.dirtMinHeight = 0;
			this.dirtMaxHeight = 256;
			this.gravelSize = 33;
			this.gravelCount = 8;
			this.gravelMinHeight = 0;
			this.gravelMaxHeight = 256;
			this.graniteSize = 33;
			this.graniteCount = 10;
			this.graniteMinHeight = 0;
			this.graniteMaxHeight = 80;
			this.dioriteSize = 33;
			this.dioriteCount = 10;
			this.dioriteMinHeight = 0;
			this.dioriteMaxHeight = 80;
			this.andesiteSize = 33;
			this.andesiteCount = 10;
			this.andesiteMinHeight = 0;
			this.andesiteMaxHeight = 80;
			this.coalSize = 17;
			this.coalCount = 20;
			this.coalMinHeight = 0;
			this.coalMaxHeight = 128;
			this.ironSize = 9;
			this.ironCount = 20;
			this.ironMinHeight = 0;
			this.ironMaxHeight = 64;
			this.goldSize = 9;
			this.goldCount = 2;
			this.goldMinHeight = 0;
			this.goldMaxHeight = 32;
			this.redstoneSize = 8;
			this.redstoneCount = 8;
			this.redstoneMinHeight = 0;
			this.redstoneMaxHeight = 16;
			this.diamondSize = 8;
			this.diamondCount = 1;
			this.diamondMinHeight = 0;
			this.diamondMaxHeight = 16;
			this.lapisSize = 7;
			this.lapisCount = 1;
			this.lapisCenterHeight = 16;
			this.lapisSpread = 16;
		}

		public boolean equals(Object p_equals_1_) {
			if (this == p_equals_1_) {
				return true;
			}
			if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
				Factory chunkprovidersettings$factory = (Factory) p_equals_1_;
				return this.andesiteCount == chunkprovidersettings$factory.andesiteCount && (this.andesiteMaxHeight == chunkprovidersettings$factory.andesiteMaxHeight && (this.andesiteMinHeight == chunkprovidersettings$factory.andesiteMinHeight && (this.andesiteSize == chunkprovidersettings$factory.andesiteSize && (Float.compare(
						chunkprovidersettings$factory.baseSize, this.baseSize) == 0 && (Float.compare(chunkprovidersettings$factory.biomeDepthOffset,
						this.biomeDepthOffset) == 0 && (Float.compare(chunkprovidersettings$factory.biomeDepthWeight, this.biomeDepthWeight) == 0 && (Float.compare(
						chunkprovidersettings$factory.biomeScaleOffset, this.biomeScaleOffset) == 0 && (Float.compare(chunkprovidersettings$factory.biomeScaleWeight,
						this.biomeScaleWeight) == 0 && (this.biomeSize == chunkprovidersettings$factory.biomeSize && (this.coalCount == chunkprovidersettings$factory.coalCount && (this.coalMaxHeight == chunkprovidersettings$factory.coalMaxHeight && (this.coalMinHeight == chunkprovidersettings$factory.coalMinHeight && (this.coalSize == chunkprovidersettings$factory.coalSize && (Float.compare(
						chunkprovidersettings$factory.coordinateScale, this.coordinateScale) == 0 && (Float.compare(chunkprovidersettings$factory.depthNoiseScaleExponent,
						this.depthNoiseScaleExponent) == 0 && (Float.compare(chunkprovidersettings$factory.depthNoiseScaleX, this.depthNoiseScaleX) == 0 && (Float.compare(
						chunkprovidersettings$factory.depthNoiseScaleZ,
						this.depthNoiseScaleZ) == 0 && (this.diamondCount == chunkprovidersettings$factory.diamondCount && (this.diamondMaxHeight == chunkprovidersettings$factory.diamondMaxHeight && (this.diamondMinHeight == chunkprovidersettings$factory.diamondMinHeight && (this.diamondSize == chunkprovidersettings$factory.diamondSize && (this.dioriteCount == chunkprovidersettings$factory.dioriteCount && (this.dioriteMaxHeight == chunkprovidersettings$factory.dioriteMaxHeight && (this.dioriteMinHeight == chunkprovidersettings$factory.dioriteMinHeight && (this.dioriteSize == chunkprovidersettings$factory.dioriteSize && (this.dirtCount == chunkprovidersettings$factory.dirtCount && (this.dirtMaxHeight == chunkprovidersettings$factory.dirtMaxHeight && (this.dirtMinHeight == chunkprovidersettings$factory.dirtMinHeight && (this.dirtSize == chunkprovidersettings$factory.dirtSize && (this.dungeonChance == chunkprovidersettings$factory.dungeonChance && (this.fixedBiome == chunkprovidersettings$factory.fixedBiome && (this.goldCount == chunkprovidersettings$factory.goldCount && (this.goldMaxHeight == chunkprovidersettings$factory.goldMaxHeight && (this.goldMinHeight == chunkprovidersettings$factory.goldMinHeight && (this.goldSize == chunkprovidersettings$factory.goldSize && (this.graniteCount == chunkprovidersettings$factory.graniteCount && (this.graniteMaxHeight == chunkprovidersettings$factory.graniteMaxHeight && (this.graniteMinHeight == chunkprovidersettings$factory.graniteMinHeight && (this.graniteSize == chunkprovidersettings$factory.graniteSize && (this.gravelCount == chunkprovidersettings$factory.gravelCount && (this.gravelMaxHeight == chunkprovidersettings$factory.gravelMaxHeight && (this.gravelMinHeight == chunkprovidersettings$factory.gravelMinHeight && (this.gravelSize == chunkprovidersettings$factory.gravelSize && (Float.compare(
						chunkprovidersettings$factory.heightScale,
						this.heightScale) == 0 && (this.ironCount == chunkprovidersettings$factory.ironCount && (this.ironMaxHeight == chunkprovidersettings$factory.ironMaxHeight && (this.ironMinHeight == chunkprovidersettings$factory.ironMinHeight && (this.ironSize == chunkprovidersettings$factory.ironSize && (this.lapisCenterHeight == chunkprovidersettings$factory.lapisCenterHeight && (this.lapisCount == chunkprovidersettings$factory.lapisCount && (this.lapisSize == chunkprovidersettings$factory.lapisSize && (this.lapisSpread == chunkprovidersettings$factory.lapisSpread && (this.lavaLakeChance == chunkprovidersettings$factory.lavaLakeChance && (Float.compare(
						chunkprovidersettings$factory.lowerLimitScale, this.lowerLimitScale) == 0 && (Float.compare(chunkprovidersettings$factory.mainNoiseScaleX,
						this.mainNoiseScaleX) == 0 && (Float.compare(chunkprovidersettings$factory.mainNoiseScaleY, this.mainNoiseScaleY) == 0 && (Float.compare(
						chunkprovidersettings$factory.mainNoiseScaleZ,
						this.mainNoiseScaleZ) == 0 && (this.redstoneCount == chunkprovidersettings$factory.redstoneCount && (this.redstoneMaxHeight == chunkprovidersettings$factory.redstoneMaxHeight && (this.redstoneMinHeight == chunkprovidersettings$factory.redstoneMinHeight && (this.redstoneSize == chunkprovidersettings$factory.redstoneSize && (this.riverSize == chunkprovidersettings$factory.riverSize && (this.seaLevel == chunkprovidersettings$factory.seaLevel && (Float.compare(
						chunkprovidersettings$factory.stretchY, this.stretchY) == 0 && (Float.compare(chunkprovidersettings$factory.upperLimitScale,
						this.upperLimitScale) == 0 && (this.useCaves == chunkprovidersettings$factory.useCaves && (this.useDungeons == chunkprovidersettings$factory.useDungeons && (this.useLavaLakes == chunkprovidersettings$factory.useLavaLakes && (this.useLavaOceans == chunkprovidersettings$factory.useLavaOceans && (this.useMineShafts == chunkprovidersettings$factory.useMineShafts && (this.useRavines == chunkprovidersettings$factory.useRavines && (this.useStrongholds == chunkprovidersettings$factory.useStrongholds && (this.useTemples == chunkprovidersettings$factory.useTemples && (this.useMonuments == chunkprovidersettings$factory.useMonuments && (this.useVillages == chunkprovidersettings$factory.useVillages && (this.useWaterLakes == chunkprovidersettings$factory.useWaterLakes && this.waterLakeChance == chunkprovidersettings$factory.waterLakeChance))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))));
			}
			return false;
		}

		public int hashCode() {
			int i = this.coordinateScale != 0.0F ? Float.floatToIntBits(this.coordinateScale) : 0;
			i = 31 * i + (this.heightScale != 0.0F ? Float.floatToIntBits(this.heightScale) : 0);
			i = 31 * i + (this.upperLimitScale != 0.0F ? Float.floatToIntBits(this.upperLimitScale) : 0);
			i = 31 * i + (this.lowerLimitScale != 0.0F ? Float.floatToIntBits(this.lowerLimitScale) : 0);
			i = 31 * i + (this.depthNoiseScaleX != 0.0F ? Float.floatToIntBits(this.depthNoiseScaleX) : 0);
			i = 31 * i + (this.depthNoiseScaleZ != 0.0F ? Float.floatToIntBits(this.depthNoiseScaleZ) : 0);
			i = 31 * i + (this.depthNoiseScaleExponent != 0.0F ? Float.floatToIntBits(this.depthNoiseScaleExponent) : 0);
			i = 31 * i + (this.mainNoiseScaleX != 0.0F ? Float.floatToIntBits(this.mainNoiseScaleX) : 0);
			i = 31 * i + (this.mainNoiseScaleY != 0.0F ? Float.floatToIntBits(this.mainNoiseScaleY) : 0);
			i = 31 * i + (this.mainNoiseScaleZ != 0.0F ? Float.floatToIntBits(this.mainNoiseScaleZ) : 0);
			i = 31 * i + (this.baseSize != 0.0F ? Float.floatToIntBits(this.baseSize) : 0);
			i = 31 * i + (this.stretchY != 0.0F ? Float.floatToIntBits(this.stretchY) : 0);
			i = 31 * i + (this.biomeDepthWeight != 0.0F ? Float.floatToIntBits(this.biomeDepthWeight) : 0);
			i = 31 * i + (this.biomeDepthOffset != 0.0F ? Float.floatToIntBits(this.biomeDepthOffset) : 0);
			i = 31 * i + (this.biomeScaleWeight != 0.0F ? Float.floatToIntBits(this.biomeScaleWeight) : 0);
			i = 31 * i + (this.biomeScaleOffset != 0.0F ? Float.floatToIntBits(this.biomeScaleOffset) : 0);
			i = 31 * i + this.seaLevel;
			i = 31 * i + (this.useCaves ? 1 : 0);
			i = 31 * i + (this.useDungeons ? 1 : 0);
			i = 31 * i + this.dungeonChance;
			i = 31 * i + (this.useStrongholds ? 1 : 0);
			i = 31 * i + (this.useVillages ? 1 : 0);
			i = 31 * i + (this.useMineShafts ? 1 : 0);
			i = 31 * i + (this.useTemples ? 1 : 0);
			i = 31 * i + (this.useMonuments ? 1 : 0);
			i = 31 * i + (this.useRavines ? 1 : 0);
			i = 31 * i + (this.useWaterLakes ? 1 : 0);
			i = 31 * i + this.waterLakeChance;
			i = 31 * i + (this.useLavaLakes ? 1 : 0);
			i = 31 * i + this.lavaLakeChance;
			i = 31 * i + (this.useLavaOceans ? 1 : 0);
			i = 31 * i + this.fixedBiome;
			i = 31 * i + this.biomeSize;
			i = 31 * i + this.riverSize;
			i = 31 * i + this.dirtSize;
			i = 31 * i + this.dirtCount;
			i = 31 * i + this.dirtMinHeight;
			i = 31 * i + this.dirtMaxHeight;
			i = 31 * i + this.gravelSize;
			i = 31 * i + this.gravelCount;
			i = 31 * i + this.gravelMinHeight;
			i = 31 * i + this.gravelMaxHeight;
			i = 31 * i + this.graniteSize;
			i = 31 * i + this.graniteCount;
			i = 31 * i + this.graniteMinHeight;
			i = 31 * i + this.graniteMaxHeight;
			i = 31 * i + this.dioriteSize;
			i = 31 * i + this.dioriteCount;
			i = 31 * i + this.dioriteMinHeight;
			i = 31 * i + this.dioriteMaxHeight;
			i = 31 * i + this.andesiteSize;
			i = 31 * i + this.andesiteCount;
			i = 31 * i + this.andesiteMinHeight;
			i = 31 * i + this.andesiteMaxHeight;
			i = 31 * i + this.coalSize;
			i = 31 * i + this.coalCount;
			i = 31 * i + this.coalMinHeight;
			i = 31 * i + this.coalMaxHeight;
			i = 31 * i + this.ironSize;
			i = 31 * i + this.ironCount;
			i = 31 * i + this.ironMinHeight;
			i = 31 * i + this.ironMaxHeight;
			i = 31 * i + this.goldSize;
			i = 31 * i + this.goldCount;
			i = 31 * i + this.goldMinHeight;
			i = 31 * i + this.goldMaxHeight;
			i = 31 * i + this.redstoneSize;
			i = 31 * i + this.redstoneCount;
			i = 31 * i + this.redstoneMinHeight;
			i = 31 * i + this.redstoneMaxHeight;
			i = 31 * i + this.diamondSize;
			i = 31 * i + this.diamondCount;
			i = 31 * i + this.diamondMinHeight;
			i = 31 * i + this.diamondMaxHeight;
			i = 31 * i + this.lapisSize;
			i = 31 * i + this.lapisCount;
			i = 31 * i + this.lapisCenterHeight;
			i = 31 * i + this.lapisSpread;
			return i;
		}

		public ChunkProviderSettings func_177864_b() {
			return new ChunkProviderSettings(this);
		}

	}

	public static class Serializer implements JsonDeserializer<ChunkProviderSettings.Factory>, JsonSerializer<ChunkProviderSettings.Factory> {

		public ChunkProviderSettings.Factory deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
			JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
			ChunkProviderSettings.Factory chunkprovidersettings$factory = new ChunkProviderSettings.Factory();

			try {
				chunkprovidersettings$factory.coordinateScale = JsonUtils.getFloat(jsonobject, "coordinateScale", chunkprovidersettings$factory.coordinateScale);
				chunkprovidersettings$factory.heightScale = JsonUtils.getFloat(jsonobject, "heightScale", chunkprovidersettings$factory.heightScale);
				chunkprovidersettings$factory.lowerLimitScale = JsonUtils.getFloat(jsonobject, "lowerLimitScale", chunkprovidersettings$factory.lowerLimitScale);
				chunkprovidersettings$factory.upperLimitScale = JsonUtils.getFloat(jsonobject, "upperLimitScale", chunkprovidersettings$factory.upperLimitScale);
				chunkprovidersettings$factory.depthNoiseScaleX = JsonUtils.getFloat(jsonobject, "depthNoiseScaleX", chunkprovidersettings$factory.depthNoiseScaleX);
				chunkprovidersettings$factory.depthNoiseScaleZ = JsonUtils.getFloat(jsonobject, "depthNoiseScaleZ", chunkprovidersettings$factory.depthNoiseScaleZ);
				chunkprovidersettings$factory.depthNoiseScaleExponent = JsonUtils.getFloat(jsonobject, "depthNoiseScaleExponent", chunkprovidersettings$factory.depthNoiseScaleExponent);
				chunkprovidersettings$factory.mainNoiseScaleX = JsonUtils.getFloat(jsonobject, "mainNoiseScaleX", chunkprovidersettings$factory.mainNoiseScaleX);
				chunkprovidersettings$factory.mainNoiseScaleY = JsonUtils.getFloat(jsonobject, "mainNoiseScaleY", chunkprovidersettings$factory.mainNoiseScaleY);
				chunkprovidersettings$factory.mainNoiseScaleZ = JsonUtils.getFloat(jsonobject, "mainNoiseScaleZ", chunkprovidersettings$factory.mainNoiseScaleZ);
				chunkprovidersettings$factory.baseSize = JsonUtils.getFloat(jsonobject, "baseSize", chunkprovidersettings$factory.baseSize);
				chunkprovidersettings$factory.stretchY = JsonUtils.getFloat(jsonobject, "stretchY", chunkprovidersettings$factory.stretchY);
				chunkprovidersettings$factory.biomeDepthWeight = JsonUtils.getFloat(jsonobject, "biomeDepthWeight", chunkprovidersettings$factory.biomeDepthWeight);
				chunkprovidersettings$factory.biomeDepthOffset = JsonUtils.getFloat(jsonobject, "biomeDepthOffset", chunkprovidersettings$factory.biomeDepthOffset);
				chunkprovidersettings$factory.biomeScaleWeight = JsonUtils.getFloat(jsonobject, "biomeScaleWeight", chunkprovidersettings$factory.biomeScaleWeight);
				chunkprovidersettings$factory.biomeScaleOffset = JsonUtils.getFloat(jsonobject, "biomeScaleOffset", chunkprovidersettings$factory.biomeScaleOffset);
				chunkprovidersettings$factory.seaLevel = JsonUtils.getInt(jsonobject, "seaLevel", chunkprovidersettings$factory.seaLevel);
				chunkprovidersettings$factory.useCaves = JsonUtils.getBoolean(jsonobject, "useCaves", chunkprovidersettings$factory.useCaves);
				chunkprovidersettings$factory.useDungeons = JsonUtils.getBoolean(jsonobject, "useDungeons", chunkprovidersettings$factory.useDungeons);
				chunkprovidersettings$factory.dungeonChance = JsonUtils.getInt(jsonobject, "dungeonChance", chunkprovidersettings$factory.dungeonChance);
				chunkprovidersettings$factory.useStrongholds = JsonUtils.getBoolean(jsonobject, "useStrongholds", chunkprovidersettings$factory.useStrongholds);
				chunkprovidersettings$factory.useVillages = JsonUtils.getBoolean(jsonobject, "useVillages", chunkprovidersettings$factory.useVillages);
				chunkprovidersettings$factory.useMineShafts = JsonUtils.getBoolean(jsonobject, "useMineShafts", chunkprovidersettings$factory.useMineShafts);
				chunkprovidersettings$factory.useTemples = JsonUtils.getBoolean(jsonobject, "useTemples", chunkprovidersettings$factory.useTemples);
				chunkprovidersettings$factory.useMonuments = JsonUtils.getBoolean(jsonobject, "useMonuments", chunkprovidersettings$factory.useMonuments);
				chunkprovidersettings$factory.useRavines = JsonUtils.getBoolean(jsonobject, "useRavines", chunkprovidersettings$factory.useRavines);
				chunkprovidersettings$factory.useWaterLakes = JsonUtils.getBoolean(jsonobject, "useWaterLakes", chunkprovidersettings$factory.useWaterLakes);
				chunkprovidersettings$factory.waterLakeChance = JsonUtils.getInt(jsonobject, "waterLakeChance", chunkprovidersettings$factory.waterLakeChance);
				chunkprovidersettings$factory.useLavaLakes = JsonUtils.getBoolean(jsonobject, "useLavaLakes", chunkprovidersettings$factory.useLavaLakes);
				chunkprovidersettings$factory.lavaLakeChance = JsonUtils.getInt(jsonobject, "lavaLakeChance", chunkprovidersettings$factory.lavaLakeChance);
				chunkprovidersettings$factory.useLavaOceans = JsonUtils.getBoolean(jsonobject, "useLavaOceans", chunkprovidersettings$factory.useLavaOceans);
				chunkprovidersettings$factory.fixedBiome = JsonUtils.getInt(jsonobject, "fixedBiome", chunkprovidersettings$factory.fixedBiome);

				if (chunkprovidersettings$factory.fixedBiome < 38 && chunkprovidersettings$factory.fixedBiome >= -1) {
					if (chunkprovidersettings$factory.fixedBiome >= BiomeGenBase.hell.getLegacyId()) {
						chunkprovidersettings$factory.fixedBiome += 2;
					}
				} else {
					chunkprovidersettings$factory.fixedBiome = -1;
				}

				chunkprovidersettings$factory.biomeSize = JsonUtils.getInt(jsonobject, "biomeSize", chunkprovidersettings$factory.biomeSize);
				chunkprovidersettings$factory.riverSize = JsonUtils.getInt(jsonobject, "riverSize", chunkprovidersettings$factory.riverSize);
				chunkprovidersettings$factory.dirtSize = JsonUtils.getInt(jsonobject, "dirtSize", chunkprovidersettings$factory.dirtSize);
				chunkprovidersettings$factory.dirtCount = JsonUtils.getInt(jsonobject, "dirtCount", chunkprovidersettings$factory.dirtCount);
				chunkprovidersettings$factory.dirtMinHeight = JsonUtils.getInt(jsonobject, "dirtMinHeight", chunkprovidersettings$factory.dirtMinHeight);
				chunkprovidersettings$factory.dirtMaxHeight = JsonUtils.getInt(jsonobject, "dirtMaxHeight", chunkprovidersettings$factory.dirtMaxHeight);
				chunkprovidersettings$factory.gravelSize = JsonUtils.getInt(jsonobject, "gravelSize", chunkprovidersettings$factory.gravelSize);
				chunkprovidersettings$factory.gravelCount = JsonUtils.getInt(jsonobject, "gravelCount", chunkprovidersettings$factory.gravelCount);
				chunkprovidersettings$factory.gravelMinHeight = JsonUtils.getInt(jsonobject, "gravelMinHeight", chunkprovidersettings$factory.gravelMinHeight);
				chunkprovidersettings$factory.gravelMaxHeight = JsonUtils.getInt(jsonobject, "gravelMaxHeight", chunkprovidersettings$factory.gravelMaxHeight);
				chunkprovidersettings$factory.graniteSize = JsonUtils.getInt(jsonobject, "graniteSize", chunkprovidersettings$factory.graniteSize);
				chunkprovidersettings$factory.graniteCount = JsonUtils.getInt(jsonobject, "graniteCount", chunkprovidersettings$factory.graniteCount);
				chunkprovidersettings$factory.graniteMinHeight = JsonUtils.getInt(jsonobject, "graniteMinHeight", chunkprovidersettings$factory.graniteMinHeight);
				chunkprovidersettings$factory.graniteMaxHeight = JsonUtils.getInt(jsonobject, "graniteMaxHeight", chunkprovidersettings$factory.graniteMaxHeight);
				chunkprovidersettings$factory.dioriteSize = JsonUtils.getInt(jsonobject, "dioriteSize", chunkprovidersettings$factory.dioriteSize);
				chunkprovidersettings$factory.dioriteCount = JsonUtils.getInt(jsonobject, "dioriteCount", chunkprovidersettings$factory.dioriteCount);
				chunkprovidersettings$factory.dioriteMinHeight = JsonUtils.getInt(jsonobject, "dioriteMinHeight", chunkprovidersettings$factory.dioriteMinHeight);
				chunkprovidersettings$factory.dioriteMaxHeight = JsonUtils.getInt(jsonobject, "dioriteMaxHeight", chunkprovidersettings$factory.dioriteMaxHeight);
				chunkprovidersettings$factory.andesiteSize = JsonUtils.getInt(jsonobject, "andesiteSize", chunkprovidersettings$factory.andesiteSize);
				chunkprovidersettings$factory.andesiteCount = JsonUtils.getInt(jsonobject, "andesiteCount", chunkprovidersettings$factory.andesiteCount);
				chunkprovidersettings$factory.andesiteMinHeight = JsonUtils.getInt(jsonobject, "andesiteMinHeight", chunkprovidersettings$factory.andesiteMinHeight);
				chunkprovidersettings$factory.andesiteMaxHeight = JsonUtils.getInt(jsonobject, "andesiteMaxHeight", chunkprovidersettings$factory.andesiteMaxHeight);
				chunkprovidersettings$factory.coalSize = JsonUtils.getInt(jsonobject, "coalSize", chunkprovidersettings$factory.coalSize);
				chunkprovidersettings$factory.coalCount = JsonUtils.getInt(jsonobject, "coalCount", chunkprovidersettings$factory.coalCount);
				chunkprovidersettings$factory.coalMinHeight = JsonUtils.getInt(jsonobject, "coalMinHeight", chunkprovidersettings$factory.coalMinHeight);
				chunkprovidersettings$factory.coalMaxHeight = JsonUtils.getInt(jsonobject, "coalMaxHeight", chunkprovidersettings$factory.coalMaxHeight);
				chunkprovidersettings$factory.ironSize = JsonUtils.getInt(jsonobject, "ironSize", chunkprovidersettings$factory.ironSize);
				chunkprovidersettings$factory.ironCount = JsonUtils.getInt(jsonobject, "ironCount", chunkprovidersettings$factory.ironCount);
				chunkprovidersettings$factory.ironMinHeight = JsonUtils.getInt(jsonobject, "ironMinHeight", chunkprovidersettings$factory.ironMinHeight);
				chunkprovidersettings$factory.ironMaxHeight = JsonUtils.getInt(jsonobject, "ironMaxHeight", chunkprovidersettings$factory.ironMaxHeight);
				chunkprovidersettings$factory.goldSize = JsonUtils.getInt(jsonobject, "goldSize", chunkprovidersettings$factory.goldSize);
				chunkprovidersettings$factory.goldCount = JsonUtils.getInt(jsonobject, "goldCount", chunkprovidersettings$factory.goldCount);
				chunkprovidersettings$factory.goldMinHeight = JsonUtils.getInt(jsonobject, "goldMinHeight", chunkprovidersettings$factory.goldMinHeight);
				chunkprovidersettings$factory.goldMaxHeight = JsonUtils.getInt(jsonobject, "goldMaxHeight", chunkprovidersettings$factory.goldMaxHeight);
				chunkprovidersettings$factory.redstoneSize = JsonUtils.getInt(jsonobject, "redstoneSize", chunkprovidersettings$factory.redstoneSize);
				chunkprovidersettings$factory.redstoneCount = JsonUtils.getInt(jsonobject, "redstoneCount", chunkprovidersettings$factory.redstoneCount);
				chunkprovidersettings$factory.redstoneMinHeight = JsonUtils.getInt(jsonobject, "redstoneMinHeight", chunkprovidersettings$factory.redstoneMinHeight);
				chunkprovidersettings$factory.redstoneMaxHeight = JsonUtils.getInt(jsonobject, "redstoneMaxHeight", chunkprovidersettings$factory.redstoneMaxHeight);
				chunkprovidersettings$factory.diamondSize = JsonUtils.getInt(jsonobject, "diamondSize", chunkprovidersettings$factory.diamondSize);
				chunkprovidersettings$factory.diamondCount = JsonUtils.getInt(jsonobject, "diamondCount", chunkprovidersettings$factory.diamondCount);
				chunkprovidersettings$factory.diamondMinHeight = JsonUtils.getInt(jsonobject, "diamondMinHeight", chunkprovidersettings$factory.diamondMinHeight);
				chunkprovidersettings$factory.diamondMaxHeight = JsonUtils.getInt(jsonobject, "diamondMaxHeight", chunkprovidersettings$factory.diamondMaxHeight);
				chunkprovidersettings$factory.lapisSize = JsonUtils.getInt(jsonobject, "lapisSize", chunkprovidersettings$factory.lapisSize);
				chunkprovidersettings$factory.lapisCount = JsonUtils.getInt(jsonobject, "lapisCount", chunkprovidersettings$factory.lapisCount);
				chunkprovidersettings$factory.lapisCenterHeight = JsonUtils.getInt(jsonobject, "lapisCenterHeight", chunkprovidersettings$factory.lapisCenterHeight);
				chunkprovidersettings$factory.lapisSpread = JsonUtils.getInt(jsonobject, "lapisSpread", chunkprovidersettings$factory.lapisSpread);
			} catch (Exception var7) {
			}

			return chunkprovidersettings$factory;
		}

		public JsonElement serialize(ChunkProviderSettings.Factory factory, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
			JsonObject jsonobject = new JsonObject();
			jsonobject.addProperty("coordinateScale", factory.coordinateScale);
			jsonobject.addProperty("heightScale", factory.heightScale);
			jsonobject.addProperty("lowerLimitScale", factory.lowerLimitScale);
			jsonobject.addProperty("upperLimitScale", factory.upperLimitScale);
			jsonobject.addProperty("depthNoiseScaleX", factory.depthNoiseScaleX);
			jsonobject.addProperty("depthNoiseScaleZ", factory.depthNoiseScaleZ);
			jsonobject.addProperty("depthNoiseScaleExponent", factory.depthNoiseScaleExponent);
			jsonobject.addProperty("mainNoiseScaleX", factory.mainNoiseScaleX);
			jsonobject.addProperty("mainNoiseScaleY", factory.mainNoiseScaleY);
			jsonobject.addProperty("mainNoiseScaleZ", factory.mainNoiseScaleZ);
			jsonobject.addProperty("baseSize", factory.baseSize);
			jsonobject.addProperty("stretchY", factory.stretchY);
			jsonobject.addProperty("biomeDepthWeight", factory.biomeDepthWeight);
			jsonobject.addProperty("biomeDepthOffset", factory.biomeDepthOffset);
			jsonobject.addProperty("biomeScaleWeight", factory.biomeScaleWeight);
			jsonobject.addProperty("biomeScaleOffset", factory.biomeScaleOffset);
			jsonobject.addProperty("seaLevel", factory.seaLevel);
			jsonobject.addProperty("useCaves", factory.useCaves);
			jsonobject.addProperty("useDungeons", factory.useDungeons);
			jsonobject.addProperty("dungeonChance", factory.dungeonChance);
			jsonobject.addProperty("useStrongholds", factory.useStrongholds);
			jsonobject.addProperty("useVillages", factory.useVillages);
			jsonobject.addProperty("useMineShafts", factory.useMineShafts);
			jsonobject.addProperty("useTemples", factory.useTemples);
			jsonobject.addProperty("useMonuments", factory.useMonuments);
			jsonobject.addProperty("useRavines", factory.useRavines);
			jsonobject.addProperty("useWaterLakes", factory.useWaterLakes);
			jsonobject.addProperty("waterLakeChance", factory.waterLakeChance);
			jsonobject.addProperty("useLavaLakes", factory.useLavaLakes);
			jsonobject.addProperty("lavaLakeChance", factory.lavaLakeChance);
			jsonobject.addProperty("useLavaOceans", factory.useLavaOceans);
			jsonobject.addProperty("fixedBiome", factory.fixedBiome);
			jsonobject.addProperty("biomeSize", factory.biomeSize);
			jsonobject.addProperty("riverSize", factory.riverSize);
			jsonobject.addProperty("dirtSize", factory.dirtSize);
			jsonobject.addProperty("dirtCount", factory.dirtCount);
			jsonobject.addProperty("dirtMinHeight", factory.dirtMinHeight);
			jsonobject.addProperty("dirtMaxHeight", factory.dirtMaxHeight);
			jsonobject.addProperty("gravelSize", factory.gravelSize);
			jsonobject.addProperty("gravelCount", factory.gravelCount);
			jsonobject.addProperty("gravelMinHeight", factory.gravelMinHeight);
			jsonobject.addProperty("gravelMaxHeight", factory.gravelMaxHeight);
			jsonobject.addProperty("graniteSize", factory.graniteSize);
			jsonobject.addProperty("graniteCount", factory.graniteCount);
			jsonobject.addProperty("graniteMinHeight", factory.graniteMinHeight);
			jsonobject.addProperty("graniteMaxHeight", factory.graniteMaxHeight);
			jsonobject.addProperty("dioriteSize", factory.dioriteSize);
			jsonobject.addProperty("dioriteCount", factory.dioriteCount);
			jsonobject.addProperty("dioriteMinHeight", factory.dioriteMinHeight);
			jsonobject.addProperty("dioriteMaxHeight", factory.dioriteMaxHeight);
			jsonobject.addProperty("andesiteSize", factory.andesiteSize);
			jsonobject.addProperty("andesiteCount", factory.andesiteCount);
			jsonobject.addProperty("andesiteMinHeight", factory.andesiteMinHeight);
			jsonobject.addProperty("andesiteMaxHeight", factory.andesiteMaxHeight);
			jsonobject.addProperty("coalSize", factory.coalSize);
			jsonobject.addProperty("coalCount", factory.coalCount);
			jsonobject.addProperty("coalMinHeight", factory.coalMinHeight);
			jsonobject.addProperty("coalMaxHeight", factory.coalMaxHeight);
			jsonobject.addProperty("ironSize", factory.ironSize);
			jsonobject.addProperty("ironCount", factory.ironCount);
			jsonobject.addProperty("ironMinHeight", factory.ironMinHeight);
			jsonobject.addProperty("ironMaxHeight", factory.ironMaxHeight);
			jsonobject.addProperty("goldSize", factory.goldSize);
			jsonobject.addProperty("goldCount", factory.goldCount);
			jsonobject.addProperty("goldMinHeight", factory.goldMinHeight);
			jsonobject.addProperty("goldMaxHeight", factory.goldMaxHeight);
			jsonobject.addProperty("redstoneSize", factory.redstoneSize);
			jsonobject.addProperty("redstoneCount", factory.redstoneCount);
			jsonobject.addProperty("redstoneMinHeight", factory.redstoneMinHeight);
			jsonobject.addProperty("redstoneMaxHeight", factory.redstoneMaxHeight);
			jsonobject.addProperty("diamondSize", factory.diamondSize);
			jsonobject.addProperty("diamondCount", factory.diamondCount);
			jsonobject.addProperty("diamondMinHeight", factory.diamondMinHeight);
			jsonobject.addProperty("diamondMaxHeight", factory.diamondMaxHeight);
			jsonobject.addProperty("lapisSize", factory.lapisSize);
			jsonobject.addProperty("lapisCount", factory.lapisCount);
			jsonobject.addProperty("lapisCenterHeight", factory.lapisCenterHeight);
			jsonobject.addProperty("lapisSpread", factory.lapisSpread);
			return jsonobject;
		}

	}

}