package beengine.api.event.player;

import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.item.Item;
import beengine.api.player.Player;

/**
 * Called when a player eats something
 */

public class PlayerItemConsumeEvent extends PlayerEvent implements Cancellable {

	private final Item item;

	public final Item item () {
		return this.item;
	}

	public PlayerItemConsumeEvent(Player player, Item item) {
		super(player);
		this.item = item;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPlayerItemConsume(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}