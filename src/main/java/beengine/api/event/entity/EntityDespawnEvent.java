package beengine.api.event.entity;

import beengine.api.entity.Entity;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;

/**
 * Called when a entity is despawned
 */
public class EntityDespawnEvent extends EntityEvent<Entity> {

	public EntityDespawnEvent(Entity entity) {
		super(entity);
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onEntityDespawn(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}