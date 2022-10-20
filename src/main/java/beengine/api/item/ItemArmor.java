package beengine.api.item;

import beengine.api.event.entity.EntityDamageEvent;
import beengine.api.util.Color;

import javax.annotation.Nullable;

public interface ItemArmor {
	int armorSlot();

	int getEnchantmentProtectionFactor(EntityDamageEvent event);

	ItemArmor customColor(@Nullable Color customColor);

	Color customColor();
}
