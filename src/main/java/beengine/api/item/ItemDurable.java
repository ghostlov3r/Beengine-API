package beengine.api.item;

public interface ItemDurable extends Item {
	int damage();

	void setDamage(int damage);

	boolean isUnbreakable();

	void setUnbreakable(boolean unbreakable);

	/**
	 * Returns the maximum amount of damage this item can take before it breaks.
	 */
	int maxDurability();

	boolean isBroken();

	boolean applyDamage(int amount);
}
