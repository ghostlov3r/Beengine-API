package beengine.api.inventory;

import beengine.api.item.Item;

public interface ArmorInventory extends Inventory {
	int SLOT_HEAD = 0;
	int SLOT_CHEST = 1;
	int SLOT_LEGS = 2;
	int SLOT_FEET = 3;

	Item helmet();

	void setHelmet(Item helmet);

	Item chestplate();

	void setChestplate(Item chestplate);

	Item leggings();

	void setLeggings(Item leggings);

	Item boots();

	void setBoots(Item boots);
}
