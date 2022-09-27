package beengine.api.world;

import beengine.api.block.Block;
import beengine.api.entity.Entity;
import beengine.api.entity.obj.ItemEntity;
import beengine.api.item.Item;
import beengine.api.math.Facing;
import beengine.api.math.Vector3;

import javax.annotation.Nullable;

public interface World {
	int Y_MAX = 0x100; //256
	int HALF_Y_MAX = Y_MAX / 2;

	static long blockHash(int x, int y, int z) {
		int shiftedY = y - World.HALF_Y_MAX;
		if (shiftedY < -512 || shiftedY >= 512) {
			throw new IllegalArgumentException("Y coordinate y is out of range!");
		}
		return ((x & 0x7ffffffL) << 37) |
				((shiftedY & 0x3ffL) << 27) |
				(z & 0x7ffffffL);
	}

	static int blockHashToX(long blockHash) {
		return (int) (blockHash >> 37);
	}

	static int blockHashToY(long blockHash) {
		return (int) ((blockHash << 27 >> 54) + World.HALF_Y_MAX);
	}

	static int blockHashToZ(long blockHash) {
		return (int) (blockHash << 37 >> 37);
	}

	boolean isClosed();

	boolean doesWeatherCycle();

	void setDoWeatherCycle(boolean weatherCycle);

	int rainTime();

	void setRainTime(int rainTime);

	int thunderTime();

	void setThunderTime(int time);

	boolean isRaining();

	boolean setRaining(boolean raining);

	boolean isThundering();

	boolean setThundering(boolean thundering);

	Block getBlock(Vector3 pos);

	Block getBlock(Vector3 pos, boolean cached);

	Block getBlock(Vector3 pos, boolean cached, boolean addToCache);

	Block getBlock(float x, float y, float z);

	Block getBlock(int x, int y, int z, boolean cached);

	Block getBlockAtSide(Vector3 pos, Facing side);

	Block getBlockAtSide(Vector3 pos, Facing side, int step);

	Block getBlockAtSide(int x, int y, int z, Facing side);

	Block getBlockAtSide(int x, int y, int z, Facing side, int step);

	Block getBlock(int x, int y, int z, boolean cached, boolean addToCache);

	boolean setBlock(Vector3 pos, Block block, boolean update);

	void setBlock(int x, int y, int z, Block block);

	boolean setBlock(int x, int y, int z, Block block, boolean update);

	boolean setBlock(int x, int y, int z, Block block, boolean update, boolean addToCache);

	@Nullable
	ItemEntity dropItem(Vector3 source, Item item);

	@Nullable
	ItemEntity dropItem(Vector3 source, Item item, @Nullable Vector3 motion);

	@Nullable
	ItemEntity dropItem(float x, float y, float z, Item item);

	@Nullable
	ItemEntity dropItem(float x, float y, float z, Item item, @Nullable Vector3 motion);

	@Nullable
	ItemEntity dropItem(float x, float y, float z, Item item, @Nullable Vector3 motion, int delay);

	@Nullable
	Entity getEntity(long entityId);

	int getHighestBlockAt(int x, int z);

	boolean isInLoadedTerrain(Vector3 pos);

	Position getSpawnPosition();

	void setSpawnLocation(Vector3 pos);

	String displayName();

	String uniqueName();

	void setTime(int time);

	void stopTime();

	void startTime();
}
