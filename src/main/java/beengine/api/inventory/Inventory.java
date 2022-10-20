package beengine.api.inventory;

import beengine.api.item.Item;
import beengine.api.player.Player;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Inventory {
	int size();

	int maxStackSize();

	Item getItem(int index);

	void setItem(int index, Item item);

	Item[] contents();

	Map<Integer, ? extends Item> getContents();

	Map<Integer, ? extends Item> getContents(boolean includeEmpty);

	void setContents(Map<Integer, ? extends Item> items);

	void setContents(Item[] items);

	boolean contains(Item item);

	void remove(Item item);

	int firstEmpty();

	boolean isSlotEmpty(int index);

	boolean canAddItem(Item item);

	List<? extends Item> addItem(Item... slots);

	List<? extends Item> removeItem(Item... slots);

	void removeAt(int index);

	void clear();

	void swap(int slot1, int slot2);

	boolean containsSlot(int slot);

	InventoryListener[] listeners();

	void addListener(InventoryListener listener);

	void removeListener(InventoryListener listener);

	Set<? extends Player> viewers();
}
