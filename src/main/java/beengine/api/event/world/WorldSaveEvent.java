package beengine.api.event.world;

import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.world.World;

/**
 * Called when a World is saved
 */
public class WorldSaveEvent extends WorldEvent {

	public WorldSaveEvent(World world) {
		super(world);
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onWorldSave(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}