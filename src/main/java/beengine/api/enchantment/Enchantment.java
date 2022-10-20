package beengine.api.enchantment;

import lombok.Getter;
import lombok.experimental.Accessors;

import javax.annotation.concurrent.Immutable;

/**
 * Container for type data applied to items.
 *
 * Note: This class is assumed to be immutable. Consider this before making alterations.
 */

@Accessors(fluent = true)
@Getter
@Immutable
public class Enchantment {
	
	/**
	 * The type of this EnchantmentInstance.
	 */
	private final EnchantmentType type;
	
	/**
	 * The level of the enchantment type.
	 */
	private final int level;
	
	public Enchantment(EnchantmentType type) {
		this(type, 1);
	}
	
	public Enchantment(EnchantmentType type, int level) {
		this.type = type;
		this.level = level;
	}

	@Override
	public boolean equals (Object o) {
		return this == o || o instanceof Enchantment ench && level == ench.level && type == ench.type;
	}
	
	public static final Enchantment[] EMPTY_ARRAY = new Enchantment[0];
}