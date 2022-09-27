package beengine.api.event.entity;

import beengine.api.entity.Entity;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;

/**
 * Called when a entity is spawned
 */
public class EntitySpawnEvent extends EntityEvent<Entity> {

	public EntitySpawnEvent (Entity entity) {
		super(entity);
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onEntitySpawn(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}