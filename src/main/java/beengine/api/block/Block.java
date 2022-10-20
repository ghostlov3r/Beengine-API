package beengine.api.block;

import beengine.api.item.Item;
import beengine.api.math.AxisAlignedBB;
import beengine.api.math.Facing;
import beengine.api.world.Position;

import java.util.Iterator;

public interface Block extends Cloneable {

	String name();

	int id();

	int meta();

	int fullId();

	Position pos();

	Item asItem();

	Block clone();

	boolean isSameType(Block other);

	boolean isSameState(Block other);

	boolean canBePlaced();

	boolean canBeReplaced();

	boolean ticksRandomly();

	float frictionFactor();

	int lightLevel();

	int lightFilter();

	boolean blocksDirectSkyLight();

	boolean isTransparent();

	boolean isSolid();

	boolean canBeFlowedInto();

	boolean hasEntityCollision();

	boolean canClimb();

	Item[] getDropsFor(Item item);

	Item[] getDropsForCompatibleTool(Item item);

	Item[] silkTouchDropsFor(Item item);

	int getXpDropForTool(Item item);

	boolean isAffectedBySilkTouch();

	int fuelTime();

	int flameEncouragement();

	int flammability();

	boolean burnsForever();

	boolean isFlammable();

	Block getSide(Facing side);

	Block getSide(Facing side, int step);

	Block down(int step);

	Block up(int step);

	Block north(int step);

	Block south(int step);

	Block west(int step);

	Block east(int step);

	Block down();

	Block up();

	Block north();

	Block south();

	Block west();

	Block east();

	Iterator<? extends Block> allSides();

	Iterator<? extends Block> horizontalSides();

	boolean collidesWithBB(AxisAlignedBB bb);

	AxisAlignedBB[] collisionBoxes();
}
