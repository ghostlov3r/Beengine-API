package beengine.api.event.entity;

import beengine.api.entity.Entity;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class EntityRegainHealthEvent extends EntityEvent<Entity> implements Cancellable {

	@Setter
	private float amount;
	private final Cause cause;

	public EntityRegainHealthEvent (Entity entity, float amount, Cause cause) {
		super(entity);
		this.amount = amount;
		this.cause = cause;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onEntityRegainHealth(this);
	}
	
	public enum Cause {
		REGEN,
		EATING,
		MAGIC,
		CUSTOM,
		SATURATION
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}