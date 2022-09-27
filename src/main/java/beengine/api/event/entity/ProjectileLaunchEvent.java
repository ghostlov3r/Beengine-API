package beengine.api.event.entity;

import beengine.api.entity.projectile.EntityProjectile;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;

/**
 * Entity is not spawned
 */
public class ProjectileLaunchEvent extends EntityEvent<EntityProjectile> implements Cancellable {

	public ProjectileLaunchEvent(EntityProjectile entity) {
		super(entity);
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onProjectileLaunch(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}