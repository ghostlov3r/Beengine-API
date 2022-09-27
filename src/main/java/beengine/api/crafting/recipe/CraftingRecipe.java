package beengine.api.crafting.recipe;

import beengine.api.crafting.CraftingGrid;
import beengine.api.item.Item;

import java.util.List;

public interface CraftingRecipe {
	/**
	 * Returns a list of items needed to craft this recipe. This MUST NOT include Air items or items with a zero count.
	 */
	List<Item> getIngredientList ();

	/**
	 * Returns a list of results this recipe will produce when the inputs in the given crafting grid are consumed.
	 */
	List<Item> getResultsFor (CraftingGrid grid);

	/**
	 * Returns whether the given crafting grid meets the requirements to craft this recipe.
	 */
	boolean matchesCraftingGrid (CraftingGrid grid);
}