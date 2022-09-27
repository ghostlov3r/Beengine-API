package beengine.api.event.world;

import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.world.Chunk;
import beengine.api.world.World;
import lombok.Getter;

/**
 * Called when a Chunk is loaded
 */
@Getter
public class ChunkLoadEvent extends ChunkEvent {

	private final boolean isNewChunk;

	public ChunkLoadEvent(World world, Chunk chunk, boolean isNewChunk) {
		super(world, chunk);
		this.isNewChunk = isNewChunk;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onChunkLoad(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}