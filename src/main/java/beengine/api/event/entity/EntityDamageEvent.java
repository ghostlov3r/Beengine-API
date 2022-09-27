package beengine.api.event.entity;

import beengine.api.entity.Entity;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Called when an entity takes damage.
 */
@Accessors(fluent = true)
@Getter
public class EntityDamageEvent extends EntityEvent<Entity> implements Cancellable {
	
	@RequiredArgsConstructor
	@Getter
	public enum Modifier {
		ARMOR                   ,
		STRENGTH                ,
		WEAKNESS                ,
		RESISTANCE              ,
		ABSORPTION              ,
		ARMOR_ENCHANTMENTS      ,
		CRITICAL                ,
		TOTEM                   ,
		WEAPON_ENCHANTMENTS     ,
		PREVIOUS_DAMAGE_COOLDOWN;
	}
	
	public enum Cause {
		CONTACT,
		ENTITY_ATTACK,
		PROJECTILE,
		SUFFOCATION,
		FALL,
		FIRE,
		FIRE_TICK,
		LAVA,
		DROWNING,
		BLOCK_EXPLOSION,
		ENTITY_EXPLOSION,
		VOID,
		SUICIDE,
		MAGIC,
		CUSTOM,
		STARVATION;
	}

	private Cause cause;
	
	/**
	 * Returns the base amount of damage applied, before modifiers.
	 * TODO: add ability to recalculate modifiers when this is set
	 */
	private float baseDamage;
	
	/**
	 * Returns the original base amount of damage applied, before alterations by plugins.
	 */
	private float originalBaseDamage;

	private float[] modifiers;
	private float[] originalModifiers;
	
	/** The cooldown in ticks before the target entity can be attacked again.
	 *  This value is not used in non-Living entities */
	private int attackCooldown = 10;
	
	public EntityDamageEvent (Entity entity, Cause cause, float damage) {
		this(entity, cause, damage, new float[16]);
	}

	public EntityDamageEvent (Entity entity, Cause cause, float damage, float[] modifiers) {
		super(entity);
		this.cause = cause;
		this.baseDamage = damage;
		this.originalBaseDamage = damage;

		this.modifiers = modifiers.clone();
		this.originalModifiers = modifiers;
	}

	public void setBaseDamage (float damage) {
		this.baseDamage = damage;
	}

	public float getOriginalModifier (Modifier type) {
		return this.originalModifiers[type.ordinal()];
	}

	public float getModifier (Modifier type) {
		return this.modifiers[type.ordinal()];
	}

	public void setModifier (float damage, Modifier type) {
		this.modifiers[type.ordinal()] = damage;
	}

	public boolean isApplicable (Modifier type) {
		return this.modifiers[type.ordinal()] != 0;
	}

	public float finalDamage () {
		float result = baseDamage;
		for (float modifier : modifiers) {
			result += modifier;
		}
		return Math.max(0, result);
	}
	
	public boolean isFatalDamage () {
		return (entity().health() - finalDamage()) <= 0;
	}

	/**
	 * Returns whether an entity can use armour points to reduce this type of damage.
	 */
	public boolean canBeReducedByArmor () {
		return switch (cause) {
			case FIRE_TICK, SUFFOCATION, DROWNING, STARVATION, FALL, VOID, MAGIC, SUICIDE -> false;
			default -> true;
		};
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onEntityDamage(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}