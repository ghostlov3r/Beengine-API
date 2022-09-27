package beengine.api.event.entity;

import beengine.api.entity.Entity;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;

public class EntityVehicleEnterEvent extends EntityEvent<Entity> implements Cancellable {
	
	// It may be a horse - horse does not extend EntityVehicle
	Entity vehicle;
	
	public EntityVehicleEnterEvent(Entity entity, Entity vehicle) {
		super(entity);
		this.vehicle = vehicle;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onEntityVehicleEnter(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}

	public Entity vehicle() {
		return vehicle;
	}
}
