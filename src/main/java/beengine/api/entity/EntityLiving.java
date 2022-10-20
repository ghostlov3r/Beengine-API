package beengine.api.entity;

import beengine.api.block.Block;
import beengine.api.entity.util.EffectManager;
import beengine.api.item.Item;
import beengine.api.math.Vector3;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public interface EntityLiving extends Entity {
	String name();

	boolean isBaby();

	void setBaby(boolean value);

	boolean isBreathing();

	void setAttackDamage(float damage);

	float attackDamage();

	int airSupplyTicks();

	int maxAirSupplyTicks();

	int armorPoints();

	float absorption();

	void setAbsorption(float value);

	float movementSpeed();

	void setMovementSpeed(float value);

	void setMovementSpeed(float v, boolean fit);

	void setSprinting(boolean value);

	boolean isSprinting();

	void setFollowRange(float range);

	float followRange();

	// boolean hasLineOfSight(Entity entity);

	void jump();

	void setSneaking(boolean sneaking);

	boolean isSneaking();

	void knockBack(float x, float z);

	void knockBack(float x, float z, float base);

	Collection<? extends Item> getDrops();

	int getXpDropAmount();

	List<? extends Block> getLineOfSight(int maxDistance);

	List<? extends Block> getLineOfSight(int maxDistance, int maxLength);

	@Nullable
	Block getTargetBlock(int maxDistance);

	void lookAt(Vector3 target);

	EffectManager effects();

	Random random();
}
