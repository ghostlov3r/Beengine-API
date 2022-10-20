package beengine.api.item;

import beengine.api.block.Block;
import beengine.api.math.Facing;

import javax.annotation.Nullable;

public interface Item extends Cloneable {

	int id();
	int meta();
	int count();
	Item setCount(int count);
	boolean hasCustomName();
	String customName();
	Item setCustomName(String name);
	String name ();
	boolean isNull();
	Item pop();
	Item pop (int count);
	boolean canBePlaced ();
	Block blockEquivalent();
	Block blockEquivalent (@Nullable Facing clickedFace);
	boolean hasAnyDamageValue();
	int maxStackSize();
	int fuelTime();
	Item fuelResidue();
	int attackPoints ();
	int defensePoints ();
	int blockToolType();
	int blockToolHarvestLevel();
	float getMiningEfficiency (boolean isCorrectTool);
	Item clone ();
}
