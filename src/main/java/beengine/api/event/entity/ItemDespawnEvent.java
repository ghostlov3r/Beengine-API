package beengine.api.event.entity;

import beengine.api.entity.obj.ItemEntity;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;

public class ItemDespawnEvent extends EntityEvent<ItemEntity> implements Cancellable {

	public ItemDespawnEvent(ItemEntity item) {
		super(item);
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onItemDespawn(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}