package beengine.api.event.entity;

import beengine.api.entity.Entity;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;

import javax.annotation.Nullable;

public class EntityTargetChangeEvent extends EntityEvent<Entity>{
	
	@Nullable
	Entity newTarget;
	
	public EntityTargetChangeEvent(Entity entity, @Nullable Entity newTarget) {
		super(entity);
		this.newTarget = newTarget;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onEntityTargetChange(this);
	}
	
	@Nullable
	public Entity newTarget() {
		return newTarget;
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}
