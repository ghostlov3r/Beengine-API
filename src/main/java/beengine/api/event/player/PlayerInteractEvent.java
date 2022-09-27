package beengine.api.event.player;

import beengine.api.event.AllowHandle;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.item.Item;
import beengine.api.player.Player;

@AllowHandle
public abstract class PlayerInteractEvent extends PlayerEvent {
	
	private final Item item;
	
	public PlayerInteractEvent(Player player, Item item) {
		super(player);
		this.item = item;
	}
	
	public Item item() {
		return item;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPlayerInteract(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}
