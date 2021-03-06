package net.minecraft.client.renderer.block.model;

import __google_.util.ByteUnzip;
import __google_.util.ByteZip;
import com.google.gson.*;
import net.minecraft.Utils;
import net.minecraft.block.Block;
import net.minecraft.util.JsonUtils;

import java.lang.reflect.Type;

public class BlockFaceUV {

	public float[] uvs;
	public final int rotation;

	public BlockFaceUV(float[] uvsIn, int rotationIn) {
		this.uvs = uvsIn;
		this.rotation = rotationIn;
	}

	public float func_178348_a(int p_178348_1_) {
		if (this.uvs == null) throw new NullPointerException("uvs");
		int i = this.func_178347_d(p_178348_1_);
		return i != 0 && i != 1 ? this.uvs[2] : this.uvs[0];
	}

	public float func_178346_b(int p_178346_1_) {
		if (this.uvs == null) {
			throw new NullPointerException("uvs");
		}
		int i = this.func_178347_d(p_178346_1_);
		return i != 0 && i != 3 ? this.uvs[3] : this.uvs[1];
	}

	private int func_178347_d(int p_178347_1_) {
		return (p_178347_1_ + this.rotation / 90) % 4;
	}

	public int func_178345_c(int p_178345_1_) {
		return (p_178345_1_ + 4 - this.rotation / 90) % 4;
	}

	public void setUvs(float[] uvsIn) {
		if (this.uvs == null) {
			this.uvs = uvsIn;
		}
	}

	public static BlockFaceUV deserialize(byte array[]) {
		ByteUnzip unzip = new ByteUnzip(array);
		float f[] = new float[4];
		for (int i = 0; i < f.length; i++) f[i] = Utils.toFloat(unzip.getBytes());
		int i = unzip.getInt();
		return new BlockFaceUV(f, i);
	}

	public static byte[] serialize(BlockFaceUV faceUV) {
		ByteZip zip = new ByteZip();
		for (int i = 0; i < faceUV.uvs.length; i++) zip.add(Utils.toBytes(faceUV.uvs[i]));
		return zip.add(faceUV.rotation).build();
	}

	static class Deserializer implements JsonDeserializer<BlockFaceUV> {

		public BlockFaceUV deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
			JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
			float[] afloat = this.parseUV(jsonobject);
			int i = this.parseRotation(jsonobject);
			return new BlockFaceUV(afloat, i);
		}

		protected int parseRotation(JsonObject p_178291_1_) {
			int i = JsonUtils.getInt(p_178291_1_, "rotation", 0);

			if (i >= 0 && i % 90 == 0 && i / 90 <= 3) {
				return i;
			}
			throw new JsonParseException("Invalid rotation " + i + " found, only 0/90/180/270 allowed");
		}

		private float[] parseUV(JsonObject p_178292_1_) {
			if (!p_178292_1_.has("uv")) {
				return null;
			}
			JsonArray jsonarray = JsonUtils.getJsonArray(p_178292_1_, "uv");

			if (jsonarray.size() != 4) {
				throw new JsonParseException("Expected 4 uv values, found: " + jsonarray.size());
			}
			float[] afloat = new float[4];

			for (int i = 0; i < afloat.length; ++i) {
				afloat[i] = JsonUtils.getFloat(jsonarray.get(i), "uv[" + i + "]");
			}

			return afloat;
		}

	}

}
