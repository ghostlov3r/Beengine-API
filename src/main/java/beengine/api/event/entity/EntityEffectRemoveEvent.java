package beengine.api.event.entity;

import beengine.api.effect.EffectInstance;
import beengine.api.entity.Entity;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;

/**
 * Called when an effect is removed from an entity.
 */
public class EntityEffectRemoveEvent extends EntityEffectEvent {

	public EntityEffectRemoveEvent(Entity entity, EffectInstance effect) {
		super(entity, effect);
	}

	@Override
	public void cancel() {
		if (effect.duration() <= 0) {
			throw new IllegalStateException("Removal of expired effects cannot be cancelled");
		}
		super.cancel();
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onEntityEffectRemove(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}