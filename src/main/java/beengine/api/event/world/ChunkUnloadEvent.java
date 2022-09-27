package beengine.api.event.world;

import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.world.Chunk;
import beengine.api.world.World;

/**
 * Called when a Chunk is unloaded
 */
public class ChunkUnloadEvent extends ChunkEvent implements Cancellable {

    public ChunkUnloadEvent(World world, Chunk chunk) {
        super(world, chunk);
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onChunkUnload(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}