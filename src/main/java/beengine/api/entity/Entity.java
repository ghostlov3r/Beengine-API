package beengine.api.entity;

import beengine.api.entity.util.Animation;
import beengine.api.event.entity.EntityDamageEvent;
import beengine.api.event.entity.EntityRegainHealthEvent;
import beengine.api.math.Facing;
import beengine.api.math.Vector3;
import beengine.api.player.Player;
import beengine.api.util.Promise;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public interface Entity {

	//void init(Location location);

	void spawn();

	void despawn();

	void maxHealth(int maxHealth);

	boolean uniqueIdCalculated();

	UUID uniqueId();

	long runtimeId();

	EntityType type();

	Long owningEntityId();

	Long targetEntityId();

	//@Nullable
	Entity owningEntity();

	//@Nullable
	Entity owningEntity(boolean anyWorld);

	void setOwningEntity(/*@Nullable*/ Entity entity);

	//@Nullable
	Entity targetEntity();

	//@Nullable
	Entity targetEntity(boolean anyWorld);

	void setTargetEntity(/*@Nullable*/ Entity entity);

	boolean isRiding();

	void setRiding(boolean value);

	void resetMotion();

	boolean isAlive();

	boolean isOnFire();

	boolean isFireProof();

	boolean isImmobile();

	void setImmobile();

	void setImmobile(boolean immobile);

	boolean isSilent();

	void setSilent(boolean silent);

	boolean isSwimmer();

	void setSwimmer(boolean value);

	boolean canFly();

	void setCanFly(boolean value);

	Facing horizontalFacing();

	Vector3 directionVector();

	Set<Player> viewers();

	float eyeHeight();

	Vector3 eyePos();

	boolean isUnderwater();

	boolean isInsideOfSolid();

	boolean isInsideOfLava();

	boolean isInsideOfWater();

	boolean isWet();

	void setFireTicks(int ticks);

	void setScale(float value);

	float scale();

	void setHealth(float amount);

	boolean hasGravity();

	String nameTag();

	void setNameTag(String nameTag);

	void setHasGravity(boolean value);

	boolean isNameTagVisible();

	void setNameTagVisible();

	void setNameTagVisible(boolean value);

	boolean isNameTagAlwaysVisible();

	void setNameTagAlwaysVisible(boolean value);

	void setNameTagAlwaysVisible();

	boolean isInvisible();

	void setInvisible();

	void setInvisible(boolean invisible);

	boolean isInLove();

	void setInLove(boolean value);

	boolean canClimb();

	void setCanClimb(boolean can);

	void setOnFire(int seconds);

	boolean setMotion(Vector3 motion);

	boolean setMotion(float x, float y, float z);

	void attack(float damage);

	void attack(EntityDamageEvent source);

	void heal(float amount);

	void heal(EntityRegainHealthEvent source);

	void kill();

	void extinguish();

	boolean canCollideWith(Entity entity);

	boolean canBeCollidedWith();

	boolean canBePushed();

	boolean canPush();

	void setRotation(float yaw, float pitch);

	void addMotion(float x, float y, float z);

	Promise<Object> teleport(Vector3 pos);

	void flagForDespawn();

	boolean isFlaggedForDespawn();

	void broadcastAnimation(Animation animation);

	void broadcastAnimation(Animation animation, Collection<Player> targets);

	//void broadcastSound(Sound sound);

	//void broadcastSound(Sound sound, Collection<Player> targets);

	EntityDamageEvent lastDamageCause();

	float health();

	int maxHealth();

	int fireTicks();

	boolean isSpawned();
}
