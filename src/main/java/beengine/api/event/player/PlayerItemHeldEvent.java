package beengine.api.event.player;

import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.item.Item;
import beengine.api.player.Player;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class PlayerItemHeldEvent extends PlayerEvent implements Cancellable {

	/**
	 * The item in the slot that the player is trying to equip.
	 */
	private final Item item;

	/**
	 * The hotbar slot the player is attempting to hold.
	 *
	 * NOTE: This event is called BEFORE the slot is equipped server-side. Setting the player's held item during this
	 * event will result in the **old** slot being changed, not this one.
	 *
	 * To change the item in the slot that the player is attempting to hold, set the slot that this function reports.
	 */
	private final int hotbarSlot;

	public PlayerItemHeldEvent(Player player, Item item, int hotbarSlot) {
		super(player);
		this.item = item;
		this.hotbarSlot = hotbarSlot;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPlayerItemHeld(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}