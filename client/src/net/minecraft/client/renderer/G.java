package net.minecraft.client.renderer;

import optifine.Config;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class G {


	private static G.AlphaState alphaState = new G.AlphaState();
	private static G.BooleanState lightingState = new G.BooleanState(2896);
	private static G.BooleanState[] lightState = new G.BooleanState[8];
	private static G.ColorMaterialState colorMaterialState = new G.ColorMaterialState(null);
	private static G.BlendState blendState = new G.BlendState(null);
	private static G.DepthState depthState = new G.DepthState(null);
	private static G.FogState fogState = new G.FogState(null);
	private static G.CullState cullState = new G.CullState(null);
	private static G.PolygonOffsetState polygonOffsetState = new G.PolygonOffsetState(null);
	private static G.ColorLogicState colorLogicState = new G.ColorLogicState(null);
	private static G.TexGenState texGenState = new G.TexGenState(null);
	private static G.ClearState clearState = new G.ClearState(null);
	private static G.BooleanState normalizeState = new G.BooleanState(2977);
	private static int activeTextureUnit = 0;
	private static G.TextureState[] textureState = new G.TextureState[32];
	private static int activeShadeModel = 7425;
	private static G.BooleanState rescaleNormalState = new G.BooleanState(32826);
	private static G.ColorMask colorMaskState = new G.ColorMask(null);
	private static G.Color colorState = new G.Color();

	public static void pushAttrib() {
		GL11.glPushAttrib(8256);
	}

	public static void popAttrib() {
		GL11.glPopAttrib();
	}

	public static void disableAlpha() {
		alphaState.state.setDisabled();
	}

	public static void enableAlpha() {
		alphaState.state.setEnabled();
	}

	public static void alphaFunc(int func, float ref) {
		if (func != alphaState.func || ref != alphaState.ref) {
			alphaState.func = func;
			alphaState.ref = ref;
			GL11.glAlphaFunc(func, ref);
		}
	}

	public static void enableLighting() {
		lightingState.setEnabled();
	}

	public static void disableLighting() {
		lightingState.setDisabled();
	}

	public static void enableLight(int light) {
		lightState[light].setEnabled();
	}

	public static void disableLight(int light) {
		lightState[light].setDisabled();
	}

	public static void enableColorMaterial() {
		colorMaterialState.field_179191_a.setEnabled();
	}

	public static void disableColorMaterial() {
		colorMaterialState.field_179191_a.setDisabled();
	}

	public static void colorMaterial(int face, int mode) {
		if (face != colorMaterialState.field_179189_b || mode != colorMaterialState.field_179190_c) {
			colorMaterialState.field_179189_b = face;
			colorMaterialState.field_179190_c = mode;
			GL11.glColorMaterial(face, mode);
		}
	}

	public static void disableDepth() {
		depthState.depthTest.setDisabled();
	}

	public static void enableDepth() {
		depthState.depthTest.setEnabled();
	}

	public static void depthFunc(int depthFunc) {
		if (depthFunc != depthState.depthFunc) {
			depthState.depthFunc = depthFunc;
			GL11.glDepthFunc(depthFunc);
		}
	}

	public static void depthMask(boolean flagIn) {
		if (flagIn != depthState.maskEnabled) {
			depthState.maskEnabled = flagIn;
			GL11.glDepthMask(flagIn);
		}
	}

	public static void disableBlend() {
		blendState.field_179213_a.setDisabled();
	}

	public static void enableBlend() {
		blendState.field_179213_a.setEnabled();
	}

	public static void blendFunc(int srcFactor, int dstFactor) {
		if (srcFactor != blendState.srcFactor || dstFactor != blendState.dstFactor) {
			blendState.srcFactor = srcFactor;
			blendState.dstFactor = dstFactor;
			GL11.glBlendFunc(srcFactor, dstFactor);
		}
	}

	public static void tryBlendFuncSeparate(int srcFactor, int dstFactor, int srcFactorAlpha, int dstFactorAlpha) {
		if (srcFactor != blendState.srcFactor || dstFactor != blendState.dstFactor || srcFactorAlpha != blendState.srcFactorAlpha || dstFactorAlpha != blendState.dstFactorAlpha) {
			blendState.srcFactor = srcFactor;
			blendState.dstFactor = dstFactor;
			blendState.srcFactorAlpha = srcFactorAlpha;
			blendState.dstFactorAlpha = dstFactorAlpha;
			OpenGlHelper.glBlendFunc(srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);
		}
	}

	public static void enableFog() {
		fogState.field_179049_a.setEnabled();
	}

	public static void disableFog() {
		fogState.field_179049_a.setDisabled();
	}

	public static void setFog(int param) {
		if (param != fogState.field_179047_b) {
			fogState.field_179047_b = param;
			GL11.glFogi(GL11.GL_FOG_MODE, param);
		}
	}

	public static void setFogDensity(float param) {
		if (param != fogState.field_179048_c) {
			fogState.field_179048_c = param;
			GL11.glFogf(GL11.GL_FOG_DENSITY, param);
		}
	}

	public static void setFogStart(float param) {
		if (param != fogState.field_179045_d) {
			fogState.field_179045_d = param;
			GL11.glFogf(GL11.GL_FOG_START, param);
		}
	}

	public static void setFogEnd(float param) {
		if (param != fogState.field_179046_e) {
			fogState.field_179046_e = param;
			GL11.glFogf(GL11.GL_FOG_END, param);
		}
	}

	public static void enableCull() {
		cullState.field_179054_a.setEnabled();
	}

	public static void disableCull() {
		cullState.field_179054_a.setDisabled();
	}

	public static void cullFace(int mode) {
		if (mode != cullState.field_179053_b) {
			cullState.field_179053_b = mode;
			GL11.glCullFace(mode);
		}
	}

	public static void enablePolygonOffset() {
		polygonOffsetState.field_179044_a.setEnabled();
	}

	public static void disablePolygonOffset() {
		polygonOffsetState.field_179044_a.setDisabled();
	}

	public static void doPolygonOffset(float factor, float units) {
		if (factor != polygonOffsetState.field_179043_c || units != polygonOffsetState.field_179041_d) {
			polygonOffsetState.field_179043_c = factor;
			polygonOffsetState.field_179041_d = units;
			GL11.glPolygonOffset(factor, units);
		}
	}

	public static void enableColorLogic() {
		colorLogicState.field_179197_a.setEnabled();
	}

	public static void disableColorLogic() {
		colorLogicState.field_179197_a.setDisabled();
	}

	public static void colorLogicOp(int opcode) {
		if (opcode != colorLogicState.field_179196_b) {
			colorLogicState.field_179196_b = opcode;
			GL11.glLogicOp(opcode);
		}
	}

	public static void enableTexGenCoord(G.TexGen p_179087_0_) {
		texGenCoord(p_179087_0_).field_179067_a.setEnabled();
	}

	public static void disableTexGenCoord(G.TexGen p_179100_0_) {
		texGenCoord(p_179100_0_).field_179067_a.setDisabled();
	}

	public static void texGen(G.TexGen p_179149_0_, int p_179149_1_) {
		G.TexGenCoord glstatemanager$texgencoord = texGenCoord(p_179149_0_);

		if (p_179149_1_ != glstatemanager$texgencoord.field_179066_c) {
			glstatemanager$texgencoord.field_179066_c = p_179149_1_;
			GL11.glTexGeni(glstatemanager$texgencoord.field_179065_b, GL11.GL_TEXTURE_GEN_MODE, p_179149_1_);
		}
	}

	public static void func_179105_a(G.TexGen p_179105_0_, int pname, FloatBuffer params) {
		GL11.glTexGen(texGenCoord(p_179105_0_).field_179065_b, pname, params);
	}

	private static G.TexGenCoord texGenCoord(G.TexGen p_179125_0_) {
		switch (G.GlStateManager$1.field_179175_a[p_179125_0_.ordinal()]) {

			case 2:
				return texGenState.field_179062_b;

			case 3:
				return texGenState.field_179063_c;

			case 4:
				return texGenState.field_179061_d;

			case 1:
			default:
				return texGenState.field_179064_a;
		}
	}

	public static void setActiveTexture(int texture) {
		if (activeTextureUnit != texture - OpenGlHelper.defaultTexUnit) {
			activeTextureUnit = texture - OpenGlHelper.defaultTexUnit;
			OpenGlHelper.setActiveTexture(texture);
		}
	}

	public static void enableTexture2D() {
		textureState[activeTextureUnit].texture2DState.setEnabled();
	}

	public static void disableTexture2D() {
		textureState[activeTextureUnit].texture2DState.setDisabled();
	}

	public static int generateTexture() {
		return GL11.glGenTextures();
	}

	public static void deleteTexture(int texture) {
		if (texture != 0) {
			GL11.glDeleteTextures(texture);

			for (G.TextureState glstatemanager$texturestate : textureState) {
				if (glstatemanager$texturestate.textureName == texture) {
					glstatemanager$texturestate.textureName = 0;
				}
			}
		}
	}

	private static int lastTexture = -124124;

	public static void bindTexture(int texture) {
		if (lastTexture == texture) return;
		lastTexture = texture;
		textureState[activeTextureUnit].textureName = texture;
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
	}

	public static void bindCurrentTexture() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureState[activeTextureUnit].textureName);
	}

	public static void enableNormalize() {
		normalizeState.setEnabled();
	}

	public static void disableNormalize() {
		normalizeState.setDisabled();
	}

	public static void shadeModel(int mode) {
		if (mode != activeShadeModel) {
			activeShadeModel = mode;
			GL11.glShadeModel(mode);
		}
	}

	public static void enableRescaleNormal() {
		rescaleNormalState.setEnabled();
	}

	public static void disableRescaleNormal() {
		rescaleNormalState.setDisabled();
	}

	public static void viewport(int x, int y, int width, int height) {
		GL11.glViewport(x, y, width, height);
	}

	public static void colorMask(boolean red, boolean green, boolean blue, boolean alpha) {
		if (red != colorMaskState.red || green != colorMaskState.green || blue != colorMaskState.blue || alpha != colorMaskState.alpha) {
			colorMaskState.red = red;
			colorMaskState.green = green;
			colorMaskState.blue = blue;
			colorMaskState.alpha = alpha;
			GL11.glColorMask(red, green, blue, alpha);
		}
	}

	public static void clearDepth(double depth) {
		if (depth != clearState.field_179205_a) {
			clearState.field_179205_a = depth;
			GL11.glClearDepth(depth);
		}
	}

	public static void clearColor(float red, float green, float blue, float alpha) {
		if (red != clearState.field_179203_b.red || green != clearState.field_179203_b.green || blue != clearState.field_179203_b.blue || alpha != clearState.field_179203_b.alpha) {
			clearState.field_179203_b.red = red;
			clearState.field_179203_b.green = green;
			clearState.field_179203_b.blue = blue;
			clearState.field_179203_b.alpha = alpha;
			GL11.glClearColor(red, green, blue, alpha);
		}
	}

	public static void clear(int mask) {
		GL11.glClear(mask);
	}

	public static void matrixMode(int mode) {
		GL11.glMatrixMode(mode);
	}

	public static void loadIdentity() {
		GL11.glLoadIdentity();
	}

	public static void pushMatrix() {
		GL11.glPushMatrix();
	}

	public static void popMatrix() {
		GL11.glPopMatrix();
	}

	public static void getFloat(int pname, FloatBuffer params) {
		GL11.glGetFloat(pname, params);
	}

	public static void ortho(double left, double right, double bottom, double top, double zNear, double zFar) {
		GL11.glOrtho(left, right, bottom, top, zNear, zFar);
	}

	public static void rotate(float angle, float x, float y, float z) {
		GL11.glRotatef(angle, x, y, z);
	}

	public static void scale(float x, float y, float z) {
		GL11.glScalef(x, y, z);
	}

	public static void scale(double x, double y, double z) {
		GL11.glScaled(x, y, z);
	}

	public static void translate(float x, float y, float z) {
		GL11.glTranslatef(x, y, z);
	}

	public static void translate(double x, double y, double z) {
		GL11.glTranslated(x, y, z);
	}

	public static void multMatrix(FloatBuffer matrix) {
		GL11.glMultMatrix(matrix);
	}

	public static void color(float colorRed, float colorGreen, float colorBlue, float colorAlpha) {
		if (colorRed != colorState.red || colorGreen != colorState.green || colorBlue != colorState.blue || colorAlpha != colorState.alpha) {
			colorState.red = colorRed;
			colorState.green = colorGreen;
			colorState.blue = colorBlue;
			colorState.alpha = colorAlpha;
			GL11.glColor4f(colorRed, colorGreen, colorBlue, colorAlpha);
		}
	}

	public static void colorNoAlpha(int color) {

		float f = (float) (color >> 16 & 255) / 255.0F;
		float f1 = (float) (color >> 8 & 255) / 255.0F;
		float f2 = (float) (color & 255) / 255.0F;

		color(f, f1, f2, 1);
	}

	public static void color(float colorRed, float colorGreen, float colorBlue) {
		color(colorRed, colorGreen, colorBlue, 1.0F);
	}

	public static void resetColor() {
		colorState.red = colorState.green = colorState.blue = colorState.alpha = -1.0F;
	}

	public static void callList(int list) {
		GL11.glCallList(list);
	}

	public static int getActiveTextureUnit() {
		return OpenGlHelper.defaultTexUnit + activeTextureUnit;
	}

	public static int getBoundTexture() {
		return textureState[activeTextureUnit].textureName;
	}

	public static void checkBoundTexture() {
		if (!Config.isMinecraftThread()) return;
		int i = GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE);
		int j = GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);
		int k = getActiveTextureUnit();
		int l = getBoundTexture();

		if (l <= 0) return;
		if (i == k && j == l) return;
		Config.dbg("checkTexture: act: " + k + ", glAct: " + i + ", tex: " + l + ", glTex: " + j);
	}

	public static void deleteTextures(IntBuffer p_deleteTextures_0_) {
		p_deleteTextures_0_.rewind();

		while (p_deleteTextures_0_.position() < p_deleteTextures_0_.limit()) {
			int i = p_deleteTextures_0_.get();
			deleteTexture(i);
		}

		p_deleteTextures_0_.rewind();
	}

	static {
		for (int i = 0; i < 8; ++i) {
			lightState[i] = new G.BooleanState(16384 + i);
		}

		for (int j = 0; j < textureState.length; ++j) {
			textureState[j] = new G.TextureState(null);
		}
	}

	static final class GlStateManager$1 {

		static final int[] field_179175_a = new int[G.TexGen.values().length];

		static {
			try {
				field_179175_a[G.TexGen.S.ordinal()] = 1;
			} catch (NoSuchFieldError ignored) {}

			try {
				field_179175_a[G.TexGen.T.ordinal()] = 2;
			} catch (NoSuchFieldError ignored) {}

			try {
				field_179175_a[G.TexGen.R.ordinal()] = 3;
			} catch (NoSuchFieldError ignored) {}

			try {
				field_179175_a[G.TexGen.Q.ordinal()] = 4;
			} catch (NoSuchFieldError ignored) {}
		}
	}

	static class AlphaState {

		public G.BooleanState state;
		public int func;
		public float ref;

		private AlphaState() {
			this.state = new G.BooleanState(3008);
			this.func = 519;
			this.ref = -1.0F;
		}

	}

	static class BlendState {

		public G.BooleanState field_179213_a;
		public int srcFactor;
		public int dstFactor;
		public int srcFactorAlpha;
		public int dstFactorAlpha;

		private BlendState() {
			this.field_179213_a = new G.BooleanState(3042);
			this.srcFactor = 1;
			this.dstFactor = 0;
			this.srcFactorAlpha = 1;
			this.dstFactorAlpha = 0;
		}

		BlendState(G.GlStateManager$1 p_i46488_1_) {
			this();
		}

	}

	static class BooleanState {

		private final int capability;
		private boolean currentState = false;

		public BooleanState(int capabilityIn) {
			this.capability = capabilityIn;
		}

		public void setDisabled() {
			this.setState(false);
		}

		public void setEnabled() {
			this.setState(true);
		}

		public void setState(boolean state) {
			if (state != this.currentState) {
				this.currentState = state;

				if (state) {
					GL11.glEnable(this.capability);
				} else {
					GL11.glDisable(this.capability);
				}
			}
		}

	}

	static class ClearState {

		public double field_179205_a;
		public G.Color field_179203_b;
		public int field_179204_c;


		private ClearState() {
			this.field_179205_a = 1.0D;
			this.field_179203_b = new G.Color(0.0F, 0.0F, 0.0F, 0.0F);
			this.field_179204_c = 0;
		}

		ClearState(G.GlStateManager$1 p_i46487_1_) {
			this();
		}

	}

	static class Color {

		public float red = 1.0F;
		public float green = 1.0F;
		public float blue = 1.0F;
		public float alpha = 1.0F;


		public Color() {
		}

		public Color(float redIn, float greenIn, float blueIn, float alphaIn) {
			this.red = redIn;
			this.green = greenIn;
			this.blue = blueIn;
			this.alpha = alphaIn;
		}

	}

	static class ColorLogicState {

		public G.BooleanState field_179197_a;
		public int field_179196_b;


		private ColorLogicState() {
			this.field_179197_a = new G.BooleanState(3058);
			this.field_179196_b = 5379;
		}

		ColorLogicState(G.GlStateManager$1 p_i46486_1_) {
			this();
		}

	}

	static class ColorMask {

		public boolean red;
		public boolean green;
		public boolean blue;
		public boolean alpha;


		private ColorMask() {
			this.red = true;
			this.green = true;
			this.blue = true;
			this.alpha = true;
		}

		ColorMask(G.GlStateManager$1 p_i46485_1_) {
			this();
		}

	}

	static class ColorMaterialState {

		public G.BooleanState field_179191_a;
		public int field_179189_b;
		public int field_179190_c;


		private ColorMaterialState() {
			this.field_179191_a = new G.BooleanState(2903);
			this.field_179189_b = 1032;
			this.field_179190_c = 5634;
		}

		ColorMaterialState(G.GlStateManager$1 p_i46484_1_) {
			this();
		}

	}

	static class CullState {

		public G.BooleanState field_179054_a;
		public int field_179053_b;


		private CullState() {
			this.field_179054_a = new G.BooleanState(2884);
			this.field_179053_b = 1029;
		}

		CullState(G.GlStateManager$1 p_i46483_1_) {
			this();
		}

	}

	static class DepthState {

		public G.BooleanState depthTest;
		public boolean maskEnabled;
		public int depthFunc;


		private DepthState() {
			this.depthTest = new G.BooleanState(2929);
			this.maskEnabled = true;
			this.depthFunc = 513;
		}

		DepthState(G.GlStateManager$1 p_i46482_1_) {
			this();
		}

	}

	static class FogState {

		public G.BooleanState field_179049_a;
		public int field_179047_b;
		public float field_179048_c;
		public float field_179045_d;
		public float field_179046_e;


		private FogState() {
			this.field_179049_a = new G.BooleanState(2912);
			this.field_179047_b = 2048;
			this.field_179048_c = 1.0F;
			this.field_179045_d = 0.0F;
			this.field_179046_e = 1.0F;
		}

		FogState(G.GlStateManager$1 p_i46481_1_) {
			this();
		}

	}

	static class PolygonOffsetState {

		public G.BooleanState field_179044_a;
		public G.BooleanState field_179042_b;
		public float field_179043_c;
		public float field_179041_d;


		private PolygonOffsetState() {
			this.field_179044_a = new G.BooleanState(32823);
			this.field_179042_b = new G.BooleanState(10754);
			this.field_179043_c = 0.0F;
			this.field_179041_d = 0.0F;
		}

		PolygonOffsetState(G.GlStateManager$1 p_i46480_1_) {
			this();
		}

	}

	public enum TexGen {
		S, T, R, Q
	}

	static class TexGenCoord {

		public G.BooleanState field_179067_a;
		public int field_179065_b;
		public int field_179066_c = -1;


		public TexGenCoord(int p_i46254_1_, int p_i46254_2_) {
			this.field_179065_b = p_i46254_1_;
			this.field_179067_a = new G.BooleanState(p_i46254_2_);
		}

	}

	static class TexGenState {

		public G.TexGenCoord field_179064_a;
		public G.TexGenCoord field_179062_b;
		public G.TexGenCoord field_179063_c;
		public G.TexGenCoord field_179061_d;


		private TexGenState() {
			this.field_179064_a = new G.TexGenCoord(8192, 3168);
			this.field_179062_b = new G.TexGenCoord(8193, 3169);
			this.field_179063_c = new G.TexGenCoord(8194, 3170);
			this.field_179061_d = new G.TexGenCoord(8195, 3171);
		}

		TexGenState(G.GlStateManager$1 p_i46477_1_) {
			this();
		}

	}

	static class TextureState {

		public G.BooleanState texture2DState;
		public int textureName;


		private TextureState() {
			this.texture2DState = new G.BooleanState(3553);
			this.textureName = 0;
		}

		TextureState(G.GlStateManager$1 p_i46476_1_) {
			this();
		}

	}

}
