package beengine.api.world;

public interface Chunk {

	/**
	 * Packs chunk X and Z into one value
	 */
	public static long hash (int chunkX, int chunkZ) {
		return (((long) chunkX) << 32) | (chunkZ & 0xFFFFFFFFL);
	}

	/**
	 * Calculates chunk X from hash
	 */
	public static int hashX (long hash) {
		return (int) (hash >> 32);
	}

	/**
	 * Calculates chunk Z from hash
	 */
	public static int hashZ (long hash) {
		return (int) hash;
	}
}
