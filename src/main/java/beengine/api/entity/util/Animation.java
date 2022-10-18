package beengine.api.entity.util;

import beengine.api.entity.EntityHuman;
import beengine.api.entity.EntityLiving;
import beengine.api.entity.EntitySquid;
import beengine.api.entity.projectile.EntityArrow;
import beengine.api.item.Item;

/**
 * Prepares a packet for an animation such as an arm swing, or other visual effect done by entities.
 */
public interface Animation {
	
	static Animation ARM_SWING(EntityLiving entity) {
		return AnimationFactory.ARM_SWING.apply(entity); }
	
	static Animation ARROW_SHAKE(EntityArrow arrow, int durationInTicks) {
		return AnimationFactory.ARROW_SHAKE.apply(arrow, durationInTicks); }
	
	static Animation CONSUMING_ITEM(EntityHuman human, Item item) {
		return AnimationFactory.CONSUMING_ITEM.apply(human, item); }
	
	static Animation CRITICAL_HIT(EntityLiving entity) {
		return AnimationFactory.CRITICAL_HIT.apply(entity); }
	
	static Animation DEATH(EntityLiving entity) {
		return AnimationFactory.DEATH.apply(entity); }
	
	static Animation HURT(EntityLiving entity) {
		return AnimationFactory.HURT.apply(entity); }
	
	static Animation RESPAWN(EntityLiving entity) {
		return AnimationFactory.RESPAWN.apply(entity); }
	
	static Animation SQUID_INK_CLOUD(EntitySquid squid) {
		return AnimationFactory.SQUID_INK_CLOUD.apply(squid); }
	
	static Animation TOTEM_USE(EntityHuman human) {
		return AnimationFactory.TOTEM_USE.apply(human); }
	
}
