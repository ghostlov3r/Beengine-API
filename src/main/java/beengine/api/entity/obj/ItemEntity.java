package beengine.api.entity.obj;

import beengine.api.entity.Entity;

public interface ItemEntity extends Entity {

	int DEFAULT_DESPAWN_DELAY = 6000; // 5 minutes
	int NEVER_DESPAWN = -1;
	int MAX_DESPAWN_DELAY = 32767 + DEFAULT_DESPAWN_DELAY; //max value storable by mojang NBT :(

	int pickupDelay();

	void setPickupDelay(int delay);

	int despawnDelay();

	void setDespawnDelay(int despawnDelay);

	String owner();

	void setOwner(String owner);

	String thrower();

	void setThrower(String thrower);

	boolean canBeMerged();

	void setCanBeMerged(boolean canBeMerged);
}
