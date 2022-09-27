package beengine.api.event.player;

import beengine.api.entity.Entity;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.item.Item;
import beengine.api.math.Vector3;
import beengine.api.player.Player;

import javax.annotation.Nullable;

public class PlayerInteractEntityEvent extends PlayerInteractEvent implements Cancellable {
	
	Entity entity;

	@Nullable Vector3 clickPos;
	
	public PlayerInteractEntityEvent(Player player, Entity entity, Item item, @Nullable Vector3 clickPos) {
		super(player, item);
		
		this.entity = entity;
		this.clickPos = clickPos;
	}
	
	public Entity entity() {
		return entity;
	}
	
	@Nullable
	public Vector3 clickPos() {
		return clickPos;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		if (depth > 0) {
			super.handle(handler, depth - 1);
			return;
		}
		handler.onPlayerInteractEntity(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}
