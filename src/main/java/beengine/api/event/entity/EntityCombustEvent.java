package beengine.api.event.entity;

import beengine.api.entity.Entity;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityCombustEvent extends EntityEvent<Entity> implements Cancellable {

	/**
	 * The duration in seconds the entity will burn for.
	 */
	private int duration;

	public EntityCombustEvent (Entity combustee, int duration) {
		super(combustee);
		this.duration = duration;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onEntityCombust(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}