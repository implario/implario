package net.minecraft.util;

public class Vec3d implements Location{

	/**
	 * X coordinate of Vec3D
	 */
	public final double xCoord;

	/**
	 * Y coordinate of Vec3D
	 */
	public final double yCoord;

	/**
	 * Z coordinate of Vec3D
	 */
	public final double zCoord;

	public Vec3d(double x, double y, double z) {
		if (x == -0.0D) {
			x = 0.0D;
		}

		if (y == -0.0D) {
			y = 0.0D;
		}

		if (z == -0.0D) {
			z = 0.0D;
		}

		this.xCoord = x;
		this.yCoord = y;
		this.zCoord = z;
	}

	public Vec3d(Vec3i p_i46377_1_) {
		this((double) p_i46377_1_.getX(), (double) p_i46377_1_.getY(), (double) p_i46377_1_.getZ());
	}

	@Override
	public double x() {
		return xCoord;
	}

	@Override
	public double y() {
		return yCoord;
	}

	@Override
	public double z() {
		return zCoord;
	}

	/**
	 * Returns a new vector with the result of the specified vector minus this.
	 */
	public Vec3d subtractReverse(Vec3d vec) {
		return new Vec3d(vec.xCoord - this.xCoord, vec.yCoord - this.yCoord, vec.zCoord - this.zCoord);
	}

	/**
	 * Normalizes the vector to a length of 1 (except if it is the zero vector)
	 */
	public Vec3d normalize() {
		double d0 = (double) MathHelper.sqrt_double(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
		return d0 < 0.0001 ? new Vec3d(0.0D, 0.0D, 0.0D) : new Vec3d(this.xCoord / d0, this.yCoord / d0, this.zCoord / d0);
	}

	public double dotProduct(Vec3d vec) {
		return this.xCoord * vec.xCoord + this.yCoord * vec.yCoord + this.zCoord * vec.zCoord;
	}

	/**
	 * Returns a new vector with the result of this vector x the specified vector.
	 */
	public Vec3d crossProduct(Vec3d vec) {
		return new Vec3d(this.yCoord * vec.zCoord - this.zCoord * vec.yCoord, this.zCoord * vec.xCoord - this.xCoord * vec.zCoord, this.xCoord * vec.yCoord - this.yCoord * vec.xCoord);
	}

	public Vec3d subtract(Vec3d vec) {
		return this.subtract(vec.xCoord, vec.yCoord, vec.zCoord);
	}

	public Vec3d subtract(double x, double y, double z) {
		return this.addVector(-x, -y, -z);
	}

	public Vec3d add(Vec3d vec) {
		return this.addVector(vec.xCoord, vec.yCoord, vec.zCoord);
	}

	/**
	 * Adds the specified x,y,z vector components to this vector and returns the resulting vector. Does not change this
	 * vector.
	 */
	public Vec3d addVector(double x, double y, double z) {
		return new Vec3d(this.xCoord + x, this.yCoord + y, this.zCoord + z);
	}

	/**
	 * Euclidean distance between this and the specified vector, returned as double.
	 */
	public double distanceTo(Vec3d vec) {
		double d0 = vec.xCoord - this.xCoord;
		double d1 = vec.yCoord - this.yCoord;
		double d2 = vec.zCoord - this.zCoord;
		return (double) MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
	}

	/**
	 * The square of the Euclidean distance between this and the specified vector.
	 */
	public double squareDistanceTo(Vec3d vec) {
		double d0 = vec.xCoord - this.xCoord;
		double d1 = vec.yCoord - this.yCoord;
		double d2 = vec.zCoord - this.zCoord;
		return d0 * d0 + d1 * d1 + d2 * d2;
	}

	/**
	 * Returns the length of the vector.
	 */
	public double lengthVector() {
		return (double) MathHelper.sqrt_double(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
	}

	/**
	 * Returns a new vector with x value equal to the second parameter, along the line between this vector and the
	 * passed in vector, or null if not possible.
	 */
	public Vec3d getIntermediateWithXValue(Vec3d vec, double x) {
		double d0 = vec.xCoord - this.xCoord;
		double d1 = vec.yCoord - this.yCoord;
		double d2 = vec.zCoord - this.zCoord;

		if (d0 * d0 < 0.0000001) return null;
		double d3 = (x - this.xCoord) / d0;
		return d3 >= 0.0D && d3 <= 1.0D ? new Vec3d(this.xCoord + d0 * d3, this.yCoord + d1 * d3, this.zCoord + d2 * d3) : null;
	}

	/**
	 * Returns a new vector with y value equal to the second parameter, along the line between this vector and the
	 * passed in vector, or null if not possible.
	 */
	public Vec3d getIntermediateWithYValue(Vec3d vec, double y) {
		double d0 = vec.xCoord - this.xCoord;
		double d1 = vec.yCoord - this.yCoord;
		double d2 = vec.zCoord - this.zCoord;

		if (d1 * d1 < 1.0000000116860974E-7D) {
			return null;
		}
		double d3 = (y - this.yCoord) / d1;
		return d3 >= 0.0D && d3 <= 1.0D ? new Vec3d(this.xCoord + d0 * d3, this.yCoord + d1 * d3, this.zCoord + d2 * d3) : null;
	}

	/**
	 * Returns a new vector with z value equal to the second parameter, along the line between this vector and the
	 * passed in vector, or null if not possible.
	 */
	public Vec3d getIntermediateWithZValue(Vec3d vec, double z) {
		double d0 = vec.xCoord - this.xCoord;
		double d1 = vec.yCoord - this.yCoord;
		double d2 = vec.zCoord - this.zCoord;

		if (d2 * d2 < 1.0000000116860974E-7D) {
			return null;
		}
		double d3 = (z - this.zCoord) / d2;
		return d3 >= 0.0D && d3 <= 1.0D ? new Vec3d(this.xCoord + d0 * d3, this.yCoord + d1 * d3, this.zCoord + d2 * d3) : null;
	}

	public String toString() {
		return "(" + this.xCoord + ", " + this.yCoord + ", " + this.zCoord + ")";
	}

	public Vec3d rotatePitch(float pitch) {
		float f = MathHelper.cos(pitch);
		float f1 = MathHelper.sin(pitch);
		double d0 = this.xCoord;
		double d1 = this.yCoord * (double) f + this.zCoord * (double) f1;
		double d2 = this.zCoord * (double) f - this.yCoord * (double) f1;
		return new Vec3d(d0, d1, d2);
	}

	public Vec3d rotateYaw(float yaw) {
		float f = MathHelper.cos(yaw);
		float f1 = MathHelper.sin(yaw);
		double d0 = this.xCoord * (double) f + this.zCoord * (double) f1;
		double d1 = this.yCoord;
		double d2 = this.zCoord * (double) f - this.xCoord * (double) f1;
		return new Vec3d(d0, d1, d2);
	}

}
