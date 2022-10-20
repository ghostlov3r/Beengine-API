package beengine.api.effect;

import beengine.api.entity.Entity;
import beengine.api.entity.EntityHuman;
import beengine.api.entity.EntityLiving;
import beengine.api.entity.util.HungerManager;
import beengine.api.event.entity.EntityDamageByChildEntityEvent;
import beengine.api.event.entity.EntityDamageByEntityEvent;
import beengine.api.event.entity.EntityDamageEvent;
import beengine.api.event.entity.EntityRegainHealthEvent;
import beengine.api.event.player.PlayerExhaustEvent;
import beengine.api.player.Player;
import beengine.api.util.Color;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.annotation.Nullable;

/**
 * TODO: implement inverse effect results for undead mobs
 *
 * @see Effect Use it to give an effect to the entity
 */
@Accessors(fluent = true)
@Getter
public enum EffectType {

	ABSORPTION ("%potion.absorption", new Color(0x25, 0x52, 0xa5)) {
		@Override
		public void add (EntityLiving entity, Effect instance) {
			int newAbsorption = (4 * instance.effectLevel());

			if (newAbsorption > entity.absorption()) {
				entity.setAbsorption(newAbsorption);
			}
		}

		@Override
		public void remove (EntityLiving entity, Effect instance) {
			entity.setAbsorption(0);
		}
	},

	// TODO: bad_omen

	BLINDNESS ("%potion.blindness", new Color(0x1f, 0x1f, 0x23), true),

	CONDUIT_POWER ("%potion.conduitPower", new Color(0x1d, 0xc2, 0xd1)),

	FATAL_POISON ("%potion.poison", new Color(0x4e, 0x93, 0x31), true, true) {
		@Override
		public boolean canTick (Effect instance) {
			int interval = 25 >> instance.amplifier();

			if (interval > 0) {
				return (instance.duration() % interval) == 0;
			}
			return true;
		}

		@Override
		public void applyEffect (EntityLiving entity, Effect instance, float potency, @Nullable Entity source) {
			var ev = new EntityDamageEvent(entity, EntityDamageEvent.Cause.MAGIC, 1);
			entity.attack(ev);
		}
	},

	FIRE_RESISTANCE ("%potion.fireResistance", new Color(0xe4, 0x9a, 0x3a)),

	HASTE ("%potion.digSpeed", new Color(0xd9, 0xc0, 0x43)),

	HEALTH_BOOST ("%potion.healthBoost", new Color(0xf8, 0x7d, 0x23)) {
		@Override
		public void add (EntityLiving entity, Effect instance) {
			entity.maxHealth(entity.maxHealth() + 4 * instance.effectLevel());
		}

		@Override
		public void remove (EntityLiving entity, Effect instance) {
			entity.maxHealth(entity.maxHealth() - 4 * instance.effectLevel());
		}
	},

	HUNGER ("%potion.hunger", new Color(0x58, 0x76, 0x53), true) {
		@Override
		public boolean canTick (Effect instance) {
			return true;
		}

		@Override
		public void applyEffect (EntityLiving entity, Effect instance, float potency, @Nullable Entity source) {
			if (entity instanceof EntityHuman human){
				human.hungerManager().exhaust(0.025f * instance.effectLevel(), PlayerExhaustEvent.Cause.POTION);
			}
		}
	},

	INSTANT_DAMAGE ("%potion.harm", new Color(0x43, 0x0a, 0x09), true, false) {
		@Override
		public boolean isInstant() {
			return true;
		}

		@Override
		public boolean canTick(Effect instance) {
			//If forced to last longer than 1 tick, these apply every tick.

			return true;
		}

		@Override
		public int defaultDuration() {
			return 1;
		}

		@Override
		public void applyEffect (EntityLiving entity, Effect instance, float potency, @Nullable Entity source) {
			//TODO: add particles (witch spell)

			float damage = (4 << instance.amplifier()) * potency;
			EntityDamageEvent ev;

			if (source != null) {
				Entity sourceOwner = source.owningEntity();
				if (sourceOwner != null) {
					ev = new EntityDamageByChildEntityEvent(sourceOwner, source, entity, EntityDamageEvent.Cause.MAGIC, damage);
				} else {
					ev = new EntityDamageByEntityEvent(source, entity, EntityDamageEvent.Cause.MAGIC, damage);
				}
			} else {
				ev = new EntityDamageEvent(entity, EntityDamageEvent.Cause.MAGIC, damage);
			}
			entity.attack(ev);
		}
	},

	INSTANT_HEALTH ("%potion.heal", new Color(0xf8, 0x24, 0x23), false, false) {
		@Override
		public boolean isInstant() {
			return true;
		}

		@Override
		public boolean canTick(Effect instance) {
			//If forced to last longer than 1 tick, these apply every tick.

			return true;
		}

		@Override
		public int defaultDuration() {
			return 1;
		}

		@Override
		public void applyEffect (EntityLiving entity, Effect instance, float potency, @Nullable Entity source) {
			if (entity.health() < entity.maxHealth()) {

				entity.heal(
						new EntityRegainHealthEvent(
								entity,
								(4 << instance.amplifier()) * potency,
								EntityRegainHealthEvent.Cause.MAGIC
						)
				);
			}
		}
	},

	INVISIBILITY ("%potion.invisibility", new Color(0x7f, 0x83, 0x92)) {
		@Override
		public void add (EntityLiving entity, Effect instance) {
			entity.setInvisible();
			entity.setNameTagVisible(false);
		}

		@Override
		public void remove (EntityLiving entity, Effect instance) {
			entity.setInvisible(false);
			entity.setNameTagVisible();
		}
	},

	JUMP_BOOST ("%potion.jump", new Color(0x22, 0xff, 0x4c)),

	LEVITATION ("%potion.levitation", new Color(0xce, 0xff, 0xff)) {
		@Override
		public boolean canTick (Effect instance) {
			return true;
		}

		@Override
		public void applyEffect (EntityLiving entity, Effect instance, float potency, @Nullable Entity source) {
			//TODO: ugly hack, player motion isn't updated properly by the server yet :(

			if (!(entity instanceof Player)) {
				entity.addMotion(0, (instance.effectLevel() / 20f - entity.motion().y) / 5, 0);
			}
		}

		@Override
		public void add (EntityLiving entity, Effect instance) {
			entity.setHasGravity(false);
		}

		@Override
		public void remove (EntityLiving entity, Effect instance) {
			entity.setHasGravity(true);
		}
	},

	MINING_FATIGUE ("%potion.digSlowDown", new Color(0x4a, 0x42, 0x17), true),

	NAUSEA ("%potion.confusion", new Color(0x55, 0x1d, 0x4a), true),

	NIGHT_VISION ("%potion.nightVision", new Color(0x1f, 0x1f, 0xa1)),

	POISON ("%potion.poison", new Color(0x4e, 0x93, 0x31), true) {
		@Override
		public boolean canTick (Effect instance) {
			int interval = 25 >> instance.amplifier();

			if (interval > 0) {
				return (instance.duration() % interval) == 0;
			}
			return true;
		}

		@Override
		public void applyEffect (EntityLiving entity, Effect instance, float potency, @Nullable Entity source) {
			if (entity.health() > 1) {
				var ev = new EntityDamageEvent(entity, EntityDamageEvent.Cause.MAGIC, 1);
				entity.attack(ev);
			}
		}
	},

	REGENERATION ("%potion.regeneration", new Color(0xcd, 0x5c, 0xab)) {
		@Override
		public boolean canTick (Effect instance) {
			int interval = 40 >> instance.amplifier();

			if (interval > 0) {
				return (instance.duration() % interval) == 0;
			}
			return true;
		}

		@Override
		public void applyEffect (EntityLiving entity, Effect instance, float potency, @Nullable Entity source) {
			if (entity.health() < entity.maxHealth()) {
				entity.heal(
						new EntityRegainHealthEvent(
								entity,
								1f,
								EntityRegainHealthEvent.Cause.MAGIC
						)
				);
			}
		}
	},

	RESISTANCE ("%potion.resistance", new Color(0x99, 0x45, 0x3a)),

	SATURATION ("%potion.saturation", new Color(0xf8, 0x24, 0x23), false) {
		@Override
		public void applyEffect (EntityLiving entity, Effect instance, float potency, @Nullable Entity source) {
			if (entity instanceof EntityHuman human) {
				HungerManager manager = human.hungerManager();

				manager.addFood(instance.effectLevel());
				manager.addSaturation(instance.effectLevel() * 2);
			}
		}
	},

	// TODO: slow_falling

	SLOWNESS ("%potion.moveSlowdown", new Color(0x5a, 0x6c, 0x81), true) {
		@Override
		public void add (EntityLiving entity, Effect instance) {
			entity.setMovementSpeed(entity.movementSpeed() * (1 - 0.15f * Math.min(instance.effectLevel(), 6)), true);
		}

		@Override
		public void remove (EntityLiving entity, Effect instance) {
			entity.setMovementSpeed(entity.movementSpeed() / (1 - 0.15f * Math.min(instance.effectLevel(), 6)));
		}
	},

	SPEED ("%potion.moveSpeed", new Color(0x7c, 0xaf, 0xc6)) {
		@Override
		public void add (EntityLiving entity, Effect instance) {
			entity.setMovementSpeed(entity.movementSpeed() * (1 + 0.2f * instance.effectLevel()));
		}

		@Override
		public void remove (EntityLiving entity, Effect instance) {
			entity.setMovementSpeed(entity.movementSpeed() / (1 + 0.2f * instance.effectLevel()));
		}
	},

	STRENGTH ("%potion.damageBoost", new Color(0x93, 0x24, 0x23)),

	// TODO: village_hero

	WATER_BREATHING ("%potion.waterBreathing", new Color(0x2e, 0x52, 0x99)),

	WEAKNESS ("%potion.weakness", new Color(0x48, 0x4d, 0x48), true),

	WITHER ("%potion.wither", new Color(0x35, 0x2a, 0x27), true) {
		@Override
		public boolean canTick (Effect instance) {
			int interval = 50 >> instance.amplifier();

			if (interval > 0) {
				return (instance.duration() % interval) == 0;
			}
			return true;
		}

		@Override
		public void applyEffect (EntityLiving entity, Effect instance, float potency, @Nullable Entity source) {
			entity.attack(
					new EntityDamageEvent(
							entity,
							EntityDamageEvent.Cause.MAGIC,
							1
					)
			);
		}
	}
	;

	/**
	 * Translation key used for effect name
	 */
	final String translation;

	/**
	 * Color of bubbles given by this effect
	 */
	final Color color;

	/**
	 * Whether the effect is harmful
	 */
	final boolean isBad;

	/**
	 * Whether the effect has potion bubbles.
	 * Some do not (e.g. Instant Damage has its own particles instead of bubbles)
	 */
	final boolean hasBubbles;

	EffectType(String translation, Color color) {
		this(translation, color, false);
	}

	EffectType(String translation, Color color, boolean isBad) {
		this(translation, color, isBad, false);
	}

	EffectType(String translation, Color color, boolean isBad, boolean hasBubbles) {
		this.translation = translation;
		this.color = color;
		this.isBad = isBad;
		this.hasBubbles = hasBubbles;
	}

	/**
	 * The default duration (in ticks) this effect will apply for if a duration is not specified.
	 */
	public int defaultDuration () { return 600; }

	/**
	 * @return Whether the effect will do something on the current tick.
	 */
	public boolean canTick (Effect instance) { return false; }
	
	/**
	 * Applies effect results to an entity.
	 * This will not be called unless [canTick] returns true.
	 */
	public final void applyEffect(EntityLiving entity, Effect instance) {
		applyEffect(entity, instance, 1.0f);
	}
	
	/**
	 * Applies effect results to an entity.
	 * This will not be called unless [canTick] returns true.
	 */
	public final void applyEffect(EntityLiving entity, Effect instance, float potency) {
		applyEffect(entity, instance, potency, null);
	}
	
	/**
	 * Applies effect results to an entity.
	 * This will not be called unless [canTick] returns true.
	 */
	public void applyEffect (EntityLiving entity, Effect instance, float potency, @Nullable Entity source) {}

	/**
	 * Applies effects to the entity when the effect is first added.
	 */
	public void add (EntityLiving entity, Effect instance) {}

	/**
	 * Removes the effect from the entity, resetting any changed values back to their original defaults.
	 */
	public void remove (EntityLiving entity, Effect instance) {}

	public boolean isInstant () {
		return false;
	}

	// HELPING METHODS
	
	public Effect create () {
		return new Effect(this);
	}
	
	public Effect create (int duration) {
		return new Effect(this, duration);
	}
	
	public Effect create (int duration, int amplifier) {
		return new Effect(this, duration, amplifier);
	}
	
	public Effect create (int duration, int amplifier, boolean isVisible) {
		return new Effect(this, duration, amplifier, isVisible);
	}
	
	public Effect create (int duration, int amplifier, boolean isVisible, boolean isAmbient) {
		return new Effect(this, duration, amplifier, isVisible, isAmbient);
	}
	
	public Effect create (int duration, int amplifier, boolean isVisible, boolean isAmbient, Color color) {
		return new Effect(this, duration, amplifier, isVisible, isAmbient, color);
	}

	public static EffectType fromString (String name) {
		try {
			return valueOf(name.toUpperCase());
		}
		catch (Exception e) {
			return null;
		}
	}
}