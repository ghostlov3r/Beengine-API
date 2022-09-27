package beengine.api.event.inventory;

import beengine.api.crafting.recipe.CraftingRecipe;
import beengine.api.event.Cancellable;
import beengine.api.event.Event;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.inventory.tx.InventoryTransaction;
import beengine.api.item.Item;
import beengine.api.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CraftItemEvent extends Event implements Cancellable {

	/**
	 * Returns the inventory transaction involved in this crafting event.
	 */
	private final InventoryTransaction transaction;

	/**
	 * Returns the recipe crafted.
	 */
	private final CraftingRecipe recipe;

	/**
	 * Returns the number of times the recipe was crafted. This is usually 1, but might be more in the case of recipe
	 * book shift-clicks (which craft lots of items in a batch).
	 */
	private final int repetitions;

	/**
	 * Returns a list of items destroyed as ingredients of the recipe.
	 */
	private final List<Item> inputs;

	/**
	 * Returns a list of items created by crafting the recipe.
	 */
	private final List<Item> outputs;

	public final Player player () {
		return this.transaction.source();
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onCraftItem(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}