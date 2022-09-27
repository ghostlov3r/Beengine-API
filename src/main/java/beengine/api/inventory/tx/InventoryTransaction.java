package beengine.api.inventory.tx;

import beengine.api.inventory.Inventory;
import beengine.api.player.Player;

import java.util.List;
import java.util.Set;

public interface InventoryTransaction {
	List<InventoryAction> actions();

	Set<Inventory> inventories();

	Player source();
}
