package net.minecraft.entity.projectile.simulant;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.Player;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class EntityGiparrow {

	static final Random rand = new Random();
	private final World world;
	private int xTile = -1;
	private int yTile = -1;
	private int zTile = -1;
	public Block inTile;
	private int inData;
	private boolean inGround;
	public Entity shootingEntity;
	private int ticksInGround;
	private int ticksInAir;
	private double lastTickPosX, lastTickPosY, lastTickPosZ;
	public double posX, posY, posZ;
	private double prevPosX, prevPosY, prevPosZ;
	private float rotationYaw, rotationPitch;
	private float prevRotationYaw, prevRotationPitch;
	private double motionX, motionZ, motionY;
	private AxisAlignedBB bb;
	public boolean destinated;
	public Entity entityHit;

	public EntityGiparrow(World worldIn, EntityLivingBase shooter, float velocity) {
		this.shootingEntity = shooter;
		this.world = shooter.worldObj;

		this.setLocationAndAngles(shooter.posX, shooter.posY + (double) shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
		// Вывод стрелы за пределы хитбокса
		this.posX -= (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		this.posY -= 0.1D;
		this.posZ -= (double) (MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.motionX = (double) (-MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI));
		this.motionZ = (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI));
		this.motionY = (double) -MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI);
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, velocity * 1.5F, 0F);
	}

	public void setLocationAndAngles(double x, double y, double z, float yaw, float pitch) {
		this.lastTickPosX = this.prevPosX = this.posX = x;
		this.lastTickPosY = this.prevPosY = this.posY = y;
		this.lastTickPosZ = this.prevPosZ = this.posZ = z;
		this.rotationYaw = yaw;
		this.rotationPitch = pitch;
		this.setPosition(this.posX, this.posY, this.posZ);
	}

	public void setPosition(double x, double y, double z) {
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		float f = 0.5F / 2.0F;
		float f1 = 0.5F;
		this.bb = new AxisAlignedBB(x - (double) f, y, z - (double) f, x + (double) f, y + (double) f1, z + (double) f);
	}


	/**
	 * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
	 */
	public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy) {
		float f = MathHelper.sqrt_double(x * x + y * y + z * z);
		x /= (double) f;
		y /= (double) f;
		z /= (double) f;
		x += 0.5F * (double) (rand.nextBoolean() ? -1 : 1) * 0.0075 * (double) inaccuracy;
		y += 0.5F * (double) (rand.nextBoolean() ? -1 : 1) * 0.0075 * (double) inaccuracy;
		z += 0.5F * (double) (rand.nextBoolean() ? -1 : 1) * 0.0075 * (double) inaccuracy;
		x *= (double) velocity;
		y *= (double) velocity;
		z *= (double) velocity;
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
		float f1 = MathHelper.sqrt_double(x * x + z * z);
		this.prevRotationYaw = this.rotationYaw = (float) (MathHelper.func_181159_b(x, z) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float) (MathHelper.func_181159_b(y, (double) f1) * 180.0D / Math.PI);
		this.ticksInGround = 0;
	}

	public void onUpdate() {

		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.prevRotationPitch = this.rotationPitch;
		this.prevRotationYaw = this.rotationYaw;

		if (this.posY < -64.0D) this.setDead();

		//		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
		//			float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		//			this.prevRotationYaw = this.rotationYaw = (float) (MathHelper.func_181159_b(this.motionX, this.motionZ) * 180.0D / Math.PI);
		//			this.prevRotationPitch = this.rotationPitch = (float) (MathHelper.func_181159_b(this.motionY, (double) f) * 180.0D / Math.PI);
		//		}

		//		BlockPos blockpos = new BlockPos(this.xTile, this.yTile, this.zTile);
		//		IBlockState iblockstate = this.world.getBlockState(blockpos);
		//		Block block = iblockstate.getBlock();
		//
		//		if (block.getMaterial() != Material.air) {
		//			block.setBlockBoundsBasedOnState(this.world, blockpos);
		//			AxisAlignedBB axisalignedbb = block.getCollisionBoundingBox(this.world, blockpos, iblockstate);
		//
		//			if (axisalignedbb != null && axisalignedbb.isVecInside(new Vec3(this.posX, this.posY, this.posZ))) {
		//				this.inGround = true;
		//				destinated = true;
		//			}
		//		}


		++this.ticksInAir;
		Vec3d vec31D = new Vec3d(this.posX, this.posY, this.posZ);
		Vec3d vec3D = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition movingobjectposition = this.world.rayTraceBlocks(vec31D, vec3D, false, true, false);
		vec31D = new Vec3d(this.posX, this.posY, this.posZ);
		vec3D = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

		if (movingobjectposition != null) {
			vec3D = new Vec3d(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
		}

		Entity entity = null;
		List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(null, this.bb.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
		double d0 = 0.0D;

		for (int i = 0; i < list.size(); ++i) {
			Entity entity1 = list.get(i);

			if (entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5)) {
				float f1 = 0.3F;
				AxisAlignedBB axisalignedbb1 = entity1.getEntityBoundingBox().expand((double) f1, (double) f1, (double) f1);
				MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec31D, vec3D);

				if (movingobjectposition1 != null) {
					double d1 = vec31D.squareDistanceTo(movingobjectposition1.hitVec);

					if (d1 < d0 || d0 == 0.0D) {
						entity = entity1;
						d0 = d1;
					}
				}
			}
		}

		if (entity != null) {
			movingobjectposition = new MovingObjectPosition(entity);
		}

		if (movingobjectposition != null && movingobjectposition.entityHit instanceof Player) {
			Player entityplayer = (Player) movingobjectposition.entityHit;

			if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof Player && !((Player) this.shootingEntity).canAttackPlayer(entityplayer)) {
				movingobjectposition = null;
			}
		}

		if (movingobjectposition != null) {
			if (movingobjectposition.entityHit != null) {
				float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
				int l = MathHelper.ceiling_double_int(f2);
				destinated = true;
				entityHit = movingobjectposition.entityHit;

				this.motionX *= -0.1;
				this.motionY *= -0.1;
				this.motionZ *= -0.1;
				this.rotationYaw += 180.0F;
				this.prevRotationYaw += 180.0F;
				this.ticksInAir = 0;
			} else {
				// Врезались в блок
				BlockPos blockpos1 = movingobjectposition.getBlockPos();
				this.xTile = blockpos1.getX();
				this.yTile = blockpos1.getY();
				this.zTile = blockpos1.getZ();
				IBlockState iblockstate1 = this.world.getBlockState(blockpos1);
				this.inTile = iblockstate1.getBlock();
				this.inData = this.inTile.getMetaFromState(iblockstate1);
				this.motionX = (double) (float) (movingobjectposition.hitVec.xCoord - this.posX);
				this.motionY = (double) (float) (movingobjectposition.hitVec.yCoord - this.posY);
				this.motionZ = (double) (float) (movingobjectposition.hitVec.zCoord - this.posZ);
				float f5 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
				this.posX -= this.motionX / (double) f5 * 0.05000000074505806D;
				this.posY -= this.motionY / (double) f5 * 0.05000000074505806D;
				this.posZ -= this.motionZ / (double) f5 * 0.05000000074505806D;
				this.inGround = true;
				destinated = true;

			}
		}

		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		float f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		this.rotationYaw = (float) (MathHelper.func_181159_b(this.motionX, this.motionZ) * 180.0D / Math.PI);

		for (this.rotationPitch = (float) (MathHelper.func_181159_b(this.motionY,
				(double) f3) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {

		}

		while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
			this.prevRotationPitch += 360.0F;
		}

		while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
			this.prevRotationYaw -= 360.0F;
		}

		while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
			this.prevRotationYaw += 360.0F;
		}

		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
		this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
		float f4 = 0.99F;
		float f6 = 0.05F;

		this.motionX *= (double) f4;
		this.motionY *= (double) f4;
		this.motionZ *= (double) f4;
		this.motionY -= (double) f6;
		this.setPosition(this.posX, this.posY, this.posZ);
		//			this.doBlockCollisions();
		//		}
	}

	public void setDead() {
		destinated = true;
	}

}
