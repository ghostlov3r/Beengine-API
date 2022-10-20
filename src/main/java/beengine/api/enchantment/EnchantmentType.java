package beengine.api.enchantment;

import beengine.api.effect.Effect;
import beengine.api.effect.EffectType;
import beengine.api.entity.Arthropod;
import beengine.api.entity.Entity;
import beengine.api.entity.EntityLiving;
import beengine.api.entity.Smite;
import beengine.api.event.entity.EntityDamageEvent;
import beengine.api.inventory.ArmorInventory;
import beengine.api.item.*;
import beengine.api.math.FMath;
import beengine.api.math.Vector3;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import static beengine.api.enchantment.EnchantmentType.Rarity.*;
import static beengine.api.enchantment.ItemFlags.*;

@Accessors(fluent = true)
@Getter
public enum EnchantmentType {

	BLAST_PROTECTION ("%enchantment.protect.explosion",
			RARE, ARMOR, NONE, 4, 1.5f,
			EntityDamageEvent.Cause.BLOCK_EXPLOSION,
			EntityDamageEvent.Cause.ENTITY_EXPLOSION),

	FEATHER_FALLING ("%enchantment.protect.fall",
			UNCOMMON, FEET, NONE, 4, 2.5f,
			EntityDamageEvent.Cause.FALL),

	FIRE_ASPECT ("%enchantment.fire", RARE, SWORD, NONE, 2) {
		@Override public boolean isMeleeWeapon() {
			return true;
		}

		@Override public boolean isApplicableTo (Entity victim) {
			return true;
		}

		@Override public void onPostAttack (Entity attacker, Entity victim, int enchantmentLevel) {
			victim.setOnFire(enchantmentLevel * 4);
		}

		@Override public int getMinEnchantAbility (int level) {
			return 10 + (level - 1) * 20;
		}

		@Override public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 50;
		}
	},

	FIRE_PROTECTION ("%enchantment.protect.fire",
			UNCOMMON, ARMOR, NONE, 4, 1.25f,
			EntityDamageEvent.Cause.FIRE,
			EntityDamageEvent.Cause.FIRE_TICK,
			EntityDamageEvent.Cause.LAVA),

	FLAME ("%enchantment.arrowFire", RARE, BOW, NONE, 1) {
		public int getMinEnchantAbility (int level) {
			return 20;
		}

		public int getMaxEnchantAbility (int level) {
			return 50;
		}
	},

	INFINITY ("%enchantment.arrowInfinite", MYTHIC, BOW, NONE, 1) {
		public int getMinEnchantAbility (int level) {
			return 20;
		}

		public int getMaxEnchantAbility (int level) {
			return 50;
		}
	},

	KNOCKBACK ("%enchantment.knockback", UNCOMMON, SWORD, NONE, 2) {
		@Override public boolean isMeleeWeapon() {
			return true;
		}

		@Override public boolean isApplicableTo (Entity victim) {
			return victim instanceof EntityLiving;
		}

		@Override public void onPostAttack (Entity attacker, Entity victim, int enchantmentLevel) {
			if (victim instanceof EntityLiving) {
				Vector3 diff = victim.pos().subtract(attacker.pos());
				((EntityLiving) victim).knockBack(diff.x, diff.z, enchantmentLevel * 0.5f);
			}
		}
	},

	MENDING ("%enchantment.mending", RARE, NONE, ALL, 1) {
		public int getMinEnchantAbility (int level) {
			return 25 + (level - 1) * 9;
		}

		public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 50;
		}

		public boolean canApplyTogether (EnchantmentType enchantment) {
			return super.canApplyTogether(enchantment) && enchantment != EnchantmentType.INFINITY;
		}
	},

	POWER ("%enchantment.arrowDamage", COMMON, BOW, NONE, 5) {
		public int getMinEnchantAbility (int level) {
			return 1 + (level - 1) * 20;
		}

		public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 15;
		}
	},

	PROJECTILE_PROTECTION ("%enchantment.protect.projectile",
			UNCOMMON, ARMOR, NONE, 4, 1.5f,
			EntityDamageEvent.Cause.PROJECTILE),

	PROTECTION ("%enchantment.protect.all",
			COMMON, ARMOR, NONE, 4, 0.75f,
			(EntityDamageEvent.Cause[]) null),

	PUNCH ("%enchantment.arrowKnockback", RARE, BOW, NONE, 2) {
		public int getMinEnchantAbility (int level) {
			return 12 + (level - 1) * 20;
		}

		public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 15;
		}
	},

	RESPIRATION ("%enchantment.oxygen", RARE, HEAD, NONE, 3) {
		public int getMinEnchantAbility (int level) {
			return 10 + (level - 1) * 20;
		}

		public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 50;
		}
	},

	SHARPNESS ("%enchantment.damage.all", COMMON, SWORD, AXE, 5) {
		@Override
		public boolean isMeleeWeapon() {
			return true;
		}

		public int getMinEnchantAbility (int level) {
			return 15 + (level - 1) * 9;
		}

		public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 50;
		}

		public boolean canApplyTogether (EnchantmentType enchantment) {
			return enchantment != SMITE;
		}

		public boolean canApply (Item item) {
			return item instanceof ItemAxe || super.canApply(item);
		}

		@Override
		public boolean isApplicableTo (Entity victim) {
			return true;
		}

		@Override
		public float getDamageBonus (int enchantmentLevel) {
			return 0.5f * (enchantmentLevel + 1);
		}
	},

	SILK_TOUCH ("%enchantment.untouching", MYTHIC, DIG, SHEARS, 1) {
		public int getMinEnchantAbility (int level) {
			return 15;
		}

		public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 50;
		}

		public boolean canApplyTogether (EnchantmentType enchantment) {
			return super.canApplyTogether(enchantment) && enchantment != EnchantmentType.FORTUNE;
		}

		public boolean canApply (Item item) {
			return item.id() == ItemIds.SHEARS || super.canApply(item);
		}
	},

	THORNS ("%enchantment.thorns", MYTHIC, TORSO, HEAD | LEGS | FEET, 3) {
		public int getMinEnchantAbility (int level) {
			return 10 + (level - 1) * 20;
		}

		public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 50;
		}
	},

	UNBREAKING ("%enchantment.durability",
			UNCOMMON, DIG | ARMOR | FISHING_ROD | BOW,
			TOOL | CARROT_STICK | ELYTRA, 3) {

		public int getMinEnchantAbility (int level) {
			return 5 + (level - 1) * 8;
		}

		public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 50;
		}

		public boolean canApplyTogether (EnchantmentType enchantment) {
			return super.canApplyTogether(enchantment) /*&& enchantment != Enchantments.FORTUNE*/; // TODO ПРОВЕРИТЬ! Видимо по ошибке попало сюда, в инете такого условия нет, и игра вроде не ругается
		}
	},

	VANISHING ("%enchantment.curse.vanishing", MYTHIC, NONE, ALL, 1) {
		public int getMinEnchantAbility (int level) {
			return 25;
		}

		public int getMaxEnchantAbility (int level) {
			return 50;
		}
	},

	BINDING ("%enchantment.curse.binding", MYTHIC, NONE, ALL, 1) {
		public int getMinEnchantAbility (int level) {
			return 25;
		}

		public int getMaxEnchantAbility (int level) {
			return 50;
		}
	},

	AQUA_AFFINITY ("%enchantment.aqua_affinity", RARE, HEAD, NONE, 1) {
		@Override public int getMinEnchantAbility (int level) {
			return 1;
		}

		@Override public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 40;
		}
	},

	BANE_OF_ARTHROPODS ("%enchantment.bane_of_arthropods", UNCOMMON, SWORD, AXE, 5) {
		@Override public int getMinEnchantAbility (int level) {
			return 5 + (level - 1) * 8;
		}

		@Override public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 20;
		}

		@Override public boolean isMeleeWeapon() {
			return true;
		}

		@Override public boolean isApplicableTo (Entity victim) {
			return victim instanceof Arthropod;
		}

		@Override public float getDamageBonus (int enchantmentLevel) {
			return enchantmentLevel * 2.5f;
		}

		@Override public void onPostAttack (Entity attacker, Entity victim, int enchantmentLevel) {
			if (victim instanceof EntityLiving livingVictim) {
				livingVictim.effects().add(new Effect(EffectType.SLOWNESS, 20 + livingVictim.random().nextInt(10) * enchantmentLevel, 3));
			}
		}
	},

	CHANNELING ("%enchantment.channeling", MYTHIC, TRIDENT, NONE, 1) {
		public int getMinEnchantAbility (int level) {
			return 20;
		}

		public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 50;
		}
	},

	RIPTIDE ("%enchantment.riptide", RARE, TRIDENT, NONE, 3) {
		public int getMinEnchantAbility (int level) {
			return 20;
		}

		public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 50;
		}
	},

	DEPTH_STRIDER ("%enchantment.depth_strider", RARE, FEET, NONE, 3) {
		public int getMinEnchantAbility (int level) {
			return level * 10;
		}

		public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 15;
		}
	},

	EFFICIENCY ("%enchantment.efficiency", COMMON, PICKAXE | AXE | SHOVEL, NONE, 5) {
		public int getMinEnchantAbility (int level) {
			return 1 + (level - 1) * 10;
		}

		public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 50;
		}

		public boolean canApply (Item item) {
			return item.id() == ItemIds.SHEARS || super.canApply(item);
		}
	},

	FORTUNE ("%enchantment.fortune", RARE, PICKAXE | SHOVEL | AXE, NONE, 3),

	LOOTING ("%enchantment.looting", RARE, SWORD, NONE, 3) {
		public int getMinEnchantAbility (int level) {
			return 15 + (level - 1) * 9;
		}

		public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 50;
		}

		public boolean canApplyTogether (EnchantmentType enchantment) {
			return super.canApplyTogether(enchantment) && enchantment != EnchantmentType.SILK_TOUCH;
		}
	},

	LUCK_OF_THE_SEA ("%enchantment.luck_of_the_sea", RARE, FISHING_ROD, NONE, 3),

	LURE ("%enchantment.lure", RARE, FISHING_ROD, NONE, 3) {
		public int getMinEnchantAbility (int level) {
			return 15 + (level - 1) * 9;
		}

		public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 50;
		}
	},

	SMITE ("%enchantment.smite", UNCOMMON, SWORD, AXE, 5) {
		@Override public boolean isMeleeWeapon() {
			return true;
		}

		@Override public boolean canApplyTogether (EnchantmentType enchantment) {
			return enchantment != SHARPNESS;
		}

		@Override public boolean canApply (Item item) {
			return item instanceof ItemAxe || super.canApply(item);
		}

		@Override public int getMinEnchantAbility (int level) {
			return 5 + (level - 1) * 8;
		}

		@Override public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 20;
		}

		@Override public boolean isApplicableTo (Entity victim) {
			return victim instanceof Smite;
		}

		@Override public float getDamageBonus (int enchantmentLevel) {
			return enchantmentLevel * 2.5f;
		}
	},

	FROST_WALKER ("%enchantment.frost_walker", RARE, NONE, FEET, 2) {
		public int getMinEnchantAbility (int level) {
			return level * 10;
		}

		public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 15;
		}
	},

	LOYALTY ("%enchantment.loyalty", UNCOMMON, TRIDENT, NONE, 3) {
		public int getMinEnchantAbility (int level) {
			return 20;
		}

		public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 50;
		}
	},

	IMPALING ("%enchantment.impaling", RARE, TRIDENT, NONE, 5) {
		public int getMinEnchantAbility (int level) {
			return 20;
		}

		public int getMaxEnchantAbility (int level) {
			return this.getMinEnchantAbility(level) + 50;
		}
	};
	
	/**
	 * A translation key for this enchantment's name.
	 */
	private final String translation;
	
	/**
	 * An int constant indicating how rare this enchantment type is.
	 */
	private final Rarity rarity;
	
	/**
	 * A bitset indicating what item types can have this item applied from an enchanting table.
	 */
	private final int primaryItemFlags;
	
	/**
	 * A bitset indicating what item types cannot have this item applied from an enchanting table, but can from
	 * an anvil.
	 */
	private final int secondaryItemFlags;
	
	/**
	 * The maximum level of this enchantment that can be found on an enchantment table.
	 */
	private final int maxLevel;

	/**
	 * The multiplier by which this enchantment type's EPF increases with each enchantment level.
	 */
	@Accessors(fluent = true)
	@Getter(AccessLevel.NONE)
	private final float typeModifier;

	/**
	 * EntityDamageEvent.Cause.* enum members which this enchantment type applies to,
	 *                                 or null if it applies to all types of damage.
	 */
	@Getter(AccessLevel.NONE)
	private final EntityDamageEvent.Cause[] applicableDamageTypes;

	EnchantmentType(String name, Rarity rarity, int primaryItemFlags, int secondaryItemFlags, int maxLevel) {
		this(name, rarity, primaryItemFlags, secondaryItemFlags, maxLevel, 0f);
	}

	EnchantmentType(String name, Rarity rarity, int primaryItemFlags, int secondaryItemFlags, int maxLevel,
					float typeModifier, EntityDamageEvent.Cause... applicableDamageTypes) {

		this.translation = name;
		this.rarity = rarity;
		this.primaryItemFlags = primaryItemFlags;
		this.secondaryItemFlags = secondaryItemFlags;
		this.maxLevel = maxLevel;
		this.typeModifier = typeModifier;
		this.applicableDamageTypes = applicableDamageTypes;
	}

	public Enchantment create () {
		return new Enchantment(this);
	}

	public Enchantment create (int level) {
		return new Enchantment(this, level);
	}

	/**
	 * Returns whether this enchantment can apply to the item type from an enchanting table.
	 */
	public boolean hasPrimaryItemType (int flag) {
		return (this.primaryItemFlags | flag) != 0;
	}

	/**
	 * Returns whether this enchantment can apply to the item type from an anvil, if it is not a primary item.
	 */
	public boolean hasSecondaryItemType (int flag) {
		return (this.secondaryItemFlags | flag) != 0;
	}
	
	public boolean canApplyTogether (EnchantmentType enchantment) {
		return enchantment != this;
	}
	
	public int getMinEnchantAbility (int level) {
		return 1 + level * 10;
	}
	
	public int getMaxEnchantAbility (int level) {
		return this.getMinEnchantAbility(level) + 5;
	}
	
	/**
	 * Determines if this enchantment can be applied to a specific Item.
	 */
	public boolean canApply (Item item) {
		if ((item.id() == ItemIds.SHEARS || item.id() == ItemIds.FLINT_STEEL || item instanceof ItemHoe)
				&& item instanceof ItemDurable durable && durable.maxDurability() >= 0) {
			return this.hasPrimaryItemType(TOOL) || this.hasSecondaryItemType(TOOL);
		}
		else if (item instanceof ItemArmor armor) {
			if (armor.armorSlot() == ArmorInventory.SLOT_HEAD) {
				return this.hasPrimaryItemType(HEAD) || this.hasSecondaryItemType(HEAD);
			}
			else if (armor.armorSlot() == ArmorInventory.SLOT_CHEST) {
				return this.hasPrimaryItemType(TORSO) || this.hasSecondaryItemType(TORSO);
			}
			else if (armor.armorSlot() == ArmorInventory.SLOT_LEGS) {
				return this.hasPrimaryItemType(LEGS) || this.hasSecondaryItemType(LEGS);
			}
			else if (armor.armorSlot() == ArmorInventory.SLOT_FEET) {
				return this.hasPrimaryItemType(FEET) || this.hasSecondaryItemType(FEET);
			}
		} else {
			if (item instanceof ItemSword) {
				return this.hasPrimaryItemType(SWORD) || this.hasSecondaryItemType(SWORD);
			}
			else if (item instanceof ItemPickaxe || item instanceof ItemShovel || item instanceof ItemAxe) {
				return this.hasPrimaryItemType(DIG) || this.hasSecondaryItemType(DIG);
			}
			else if (item.id() == ItemIds.BOW) {
				return this.hasPrimaryItemType(BOW) || this.hasSecondaryItemType(BOW);
			}
			else if (item.id() == ItemIds.FISHING_ROD) {
				return this.hasPrimaryItemType(FISHING_ROD) || this.hasSecondaryItemType(FISHING_ROD);
			}
			else if (item.id() == ItemIds.ELYTRA) {
				return this.hasPrimaryItemType(ELYTRA) || this.hasSecondaryItemType(ELYTRA);
			}
			else if (item instanceof ItemSkull || item.id() == ItemIds.PUMPKIN) {
				return this.hasPrimaryItemType(WEARABLE) || this.hasSecondaryItemType(WEARABLE);
			}
			else if (item.id() == ItemIds.SHIELD) {
				return this.hasPrimaryItemType(SHIELD) || this.hasSecondaryItemType(SHIELD);
			}
			else if (item.id() == ItemIds.TRIDENT) {
				return this.hasPrimaryItemType(TRIDENT) || this.hasSecondaryItemType(TRIDENT);
			}
		}
		
		return false;
	}

	public boolean isProtection () {
		return typeModifier > 0f;
	}

	/**
	 * Returns the base EPF this enchantment type offers for the given enchantment level.
	 */
	public int getProtectionFactor (int level) {
		return FMath.floor((6 + level * level) * this.typeModifier / 3f);
	}

	/**
	 * Returns whether this enchantment type offers protection from the specified damage source's cause.
	 */
	public boolean isApplicable (EntityDamageEvent event) {
		if (this.applicableDamageTypes == null) {
			return true;
		}
		for (EntityDamageEvent.Cause cause : applicableDamageTypes) {
			if (event.cause() == cause) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Classes extending this class can be applied to weapons
	 * and activate when used by a mob to attack another mob in melee combat.
	 */
	public boolean isMeleeWeapon () {
		return false;
	}

	/**
	 * Returns whether this melee enchantment has an effect on the target entity. For example, Smite only applies to
	 * undead mobs.
	 */
	public boolean isApplicableTo (Entity victim) {
		return false;
	}

	/**
	 * Returns the amount of additional damage caused by this enchantment to applicable targets.
	 */
	public float getDamageBonus (int enchantmentLevel) {
		return 0f;
	}

	/**
	 * Called after damaging the entity to apply any post damage effects to the target.
	 */
	public void onPostAttack (Entity attacker, Entity victim, int enchantmentLevel) {

	}
	
	@Getter
	@RequiredArgsConstructor
	public enum Rarity {
		COMMON   (10),
		UNCOMMON (5 ),
		RARE     (2 ),
		MYTHIC   (1 );
		
		private final int value;
	}
}