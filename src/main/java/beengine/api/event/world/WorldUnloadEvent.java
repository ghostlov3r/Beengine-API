package beengine.api.event.world;

import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.world.World;

/**
 * Called when a World is unloaded
 */
public class WorldUnloadEvent extends WorldEvent implements Cancellable {

	public WorldUnloadEvent(World world) {
		super(world);
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onWorldUnload(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}