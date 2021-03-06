package net.minecraft.util;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.Player;
import net.minecraft.inventory.Inventory;

public final class EntitySelectors {

	public static final Predicate<Entity> IS_STANDALONE = p_apply_1_ -> p_apply_1_.isEntityAlive() && p_apply_1_.riddenByEntity == null && p_apply_1_.ridingEntity == null;
	public static final Predicate<Entity> selectInventories = p_apply_1_ -> p_apply_1_ instanceof Inventory && p_apply_1_.isEntityAlive();
	public static final Predicate<Entity> NOT_SPECTATING = p_apply_1_ -> !(p_apply_1_ instanceof Player) || !((Player) p_apply_1_).isSpectator();


}
