package beengine.api.event.entity;

import beengine.api.entity.Entity;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import lombok.Getter;
import lombok.Setter;

/**
 * Called when a entity decides to explode
 */
@Getter
@Setter
public final class ExplosionPrimeEvent extends EntityEvent<Entity> implements Cancellable {

	/** is affectsBlocks  */
	private boolean isBlockBreaking;
	private float force;

	public ExplosionPrimeEvent(Entity entity, float force) {
		super(entity);
		this.force = force;
		this.isBlockBreaking = true;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onExplosionPrime(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}