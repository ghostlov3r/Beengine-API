package beengine.api.inventory.tx;

import beengine.api.item.Item;

public interface InventoryAction {
	Item sourceItem();

	Item targetItem();
}
