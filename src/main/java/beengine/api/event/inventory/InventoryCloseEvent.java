package beengine.api.event.inventory;

import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.inventory.Inventory;
import beengine.api.player.Player;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class InventoryCloseEvent extends InventoryEvent {

	/**
	 * Who closed an inventory
	 */
	private final Player player;

	public InventoryCloseEvent(Inventory inventory, Player player) {
		super(inventory);
		this.player = player;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onInventoryClose(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}