package beengine.api.entity.util;

import beengine.api.entity.EntityHuman;
import beengine.api.entity.EntityLiving;
import beengine.api.entity.EntitySquid;
import beengine.api.entity.projectile.EntityArrow;
import beengine.api.item.Item;

import java.util.function.BiFunction;
import java.util.function.Function;

class AnimationFactory {

	static Function<EntityLiving, Animation> ARM_SWING;

	static BiFunction<EntityArrow, Integer, Animation> ARROW_SHAKE;

	static BiFunction<EntityHuman, Item, Animation> CONSUMING_ITEM;

	static Function<EntityLiving, Animation> CRITICAL_HIT;

	static Function<EntityLiving, Animation> DEATH;

	static Function<EntityLiving, Animation> HURT;

	static Function<EntityLiving, Animation> RESPAWN;

	static Function<EntitySquid, Animation> SQUID_INK_CLOUD;

	static Function<EntityHuman, Animation> TOTEM_USE;

}
