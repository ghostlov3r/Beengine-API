package beengine.api.crafting;

import beengine.api.item.Item;
import beengine.api.player.Player;

public interface CraftingGrid {
	int recipeWidth();

	int recipeHeight();

	Item getIngredient(int x, int y);

	Player holder();

	int gridWidth();
}
