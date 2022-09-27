package beengine.api.event.world;

import beengine.api.world.Chunk;
import beengine.api.world.World;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Chunk-related events
 */
@Accessors(fluent = true)
@Getter
public abstract class ChunkEvent extends WorldEvent {

	private final Chunk chunk;
	
	public ChunkEvent(World world, Chunk chunk) {
		super(world);
		this.chunk = chunk;
	}
}