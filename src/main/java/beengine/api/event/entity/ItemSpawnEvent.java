package beengine.api.event.entity;

import beengine.api.entity.obj.ItemEntity;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;

public class ItemSpawnEvent extends EntityEvent<ItemEntity> {

	public ItemSpawnEvent(ItemEntity item) {
		super(item);
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onItemSpawn(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}