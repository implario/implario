package vanilla;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.resources.Registrar;
//import vanilla.client.renderer.entity.RenderMinecartMobSpawner;
import vanilla.entity.EntityLeashKnot;
import vanilla.entity.VanillaEntity;
import vanilla.entity.ai.EntityMinecartMobSpawner;
import vanilla.entity.boss.EntityDragon;
import vanilla.entity.boss.EntityWither;
import vanilla.entity.monster.*;
import vanilla.entity.passive.*;

public class VEntities {

	public void load(Registrar registrar) {

		registrar.registerEntity(EntityLeashKnot.class, "LeashKnot", 8);
		registrar.registerEntity(EntityMinecartMobSpawner.class, EntityMinecart.EnumMinecartType.SPAWNER.getName(), 47);
		registrar.registerEntity(VanillaEntity.class, "Mob", 48);
		registrar.registerEntity(EntityMob.class, "Monster", 49);
		registrar.registerMob(EntityCreeper.class, "Creeper", 50, 894731, 0);
		registrar.registerMob(EntitySkeleton.class, "Skeleton", 51, 12698049, 4802889);
		registrar.registerMob(EntitySpider.class, "Spider", 52, 3419431, 11013646);
		registrar.registerEntity(EntityGiantZombie.class, "Giant", 53);
		registrar.registerMob(EntityZombie.class, "Zombie", 54, 44975, 7969893);
		registrar.registerMob(EntitySlime.class, "Slime", 55, 5349438, 8306542);
		registrar.registerMob(EntityGhast.class, "Ghast", 56, 16382457, 12369084);
		registrar.registerMob(EntityPigZombie.class, "PigZombie", 57, 15373203, 5009705);
		registrar.registerMob(EntityEnderman.class, "Enderman", 58, 1447446, 0);
		registrar.registerMob(EntityCaveSpider.class, "CaveSpider", 59, 803406, 11013646);
		registrar.registerMob(EntitySilverfish.class, "Silverfish", 60, 7237230, 3158064);
		registrar.registerMob(EntityBlaze.class, "Blaze", 61, 16167425, 16775294);
		registrar.registerMob(EntityMagmaCube.class, "LavaSlime", 62, 3407872, 16579584);
		registrar.registerEntity(EntityDragon.class, "EnderDragon", 63);
		registrar.registerEntity(EntityWither.class, "WitherBoss", 64);
		registrar.registerMob(EntityBat.class, "Bat", 65, 4996656, 986895);
		registrar.registerMob(EntityWitch.class, "Witch", 66, 3407872, 5349438);
		registrar.registerMob(EntityEndermite.class, "Endermite", 67, 1447446, 7237230);
		registrar.registerMob(EntityGuardian.class, "Guardian", 68, 5931634, 15826224);
		registrar.registerMob(EntityPig.class, "Pig", 90, 15771042, 14377823);
		registrar.registerMob(EntitySheep.class, "Sheep", 91, 15198183, 16758197);
		registrar.registerMob(EntityCow.class, "Cow", 92, 4470310, 10592673);
		registrar.registerMob(EntityChicken.class, "Chicken", 93, 10592673, 16711680);
		registrar.registerMob(EntitySquid.class, "Squid", 94, 2243405, 7375001);
		registrar.registerMob(EntityWolf.class, "Wolf", 95, 14144467, 13545366);
		registrar.registerMob(EntityMooshroom.class, "MushroomCow", 96, 10489616, 12040119);
		registrar.registerEntity(EntitySnowman.class, "SnowMan", 97);
		registrar.registerMob(EntityOcelot.class, "Ozelot", 98, 15720061, 5653556);
		registrar.registerEntity(EntityIronGolem.class, "VillagerGolem", 99);
		registrar.registerMob(EntityHorse.class, "EntityHorse", 100, 12623485, 15656192);
		registrar.registerMob(EntityRabbit.class, "Rabbit", 101, 10051392, 7555121);
		registrar.registerMob(EntityVillager.class, "Villager", 120, 5651507, 12422002);

	}

}
