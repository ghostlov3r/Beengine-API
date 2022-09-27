package beengine.api.inventory;

import beengine.api.item.Item;

/**
 * Classes implementing this interface can be injected into inventories to receive notifications when content changes
 * occur.
 * @see CallbackInventoryListener for a closure-based listener
 *
 * @see beengine.inventory.Inventory ::getListeners
 */
public interface InventoryListener {
	
	InventoryListener[] EMPTY_ARRAY = {};

	void onSlotChange(Inventory inventory, int slot, Item oldItem);

	void onContentChange(Inventory inventory, /* was Map<Integer, Item>*/
						Item[] oldContents);
}