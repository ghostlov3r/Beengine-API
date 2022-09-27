package beengine.api.event.world;

import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.world.Chunk;
import beengine.api.world.World;

/**
 * Called when a Chunk is populated (after receiving it on the main thread)
 */
public class ChunkPopulateEvent extends ChunkEvent {

	public ChunkPopulateEvent(World world, Chunk chunk) {
		super(world, chunk);
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onChunkPopulate(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}