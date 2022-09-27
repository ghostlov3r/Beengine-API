package beengine.api.event.inventory;

import beengine.api.event.Cancellable;
import beengine.api.event.Event;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.inventory.tx.InventoryTransaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Called when there is a transaction between two Inventory objects.
 * The source of this can be a Player, entities, mobs, or even hoppers in the future!
 */
@Accessors(fluent = true)
@AllArgsConstructor
@Getter
public class InventoryTransactionEvent extends Event implements Cancellable {

	private final InventoryTransaction transaction;
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onInventoryTransaction(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}