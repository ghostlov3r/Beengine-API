package beengine.api.event.entity;

import beengine.api.entity.Entity;
import beengine.api.entity.EntityLiving;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.annotation.Nullable;
import java.lang.ref.WeakReference;

/**
 * Called when an entity takes damage from another entity.
 */
@Accessors(fluent = true)
@Getter
public class EntityDamageByEntityEvent extends EntityDamageEvent {
	
	@Getter
	private float knockBack;
	
	private WeakReference<Entity> damager;
	
	public EntityDamageByEntityEvent (Entity damager, Entity entity, Cause cause, float damage) {
		this(damager, entity, cause, damage, new float[16]);
	}

	public EntityDamageByEntityEvent (Entity damager, Entity entity, Cause cause, float damage, float[] modifiers) {
		this(damager, entity, cause, damage, modifiers, 0.4f);
	}

	public EntityDamageByEntityEvent (Entity damager, Entity entity, Cause cause, float damage, float[] modifiers, float knockBack) {
		super(entity, cause, damage, modifiers);
		this.damager = new WeakReference<>(damager);
		this.knockBack = knockBack;
		this.addAttackerModifiers(damager);
	}

	protected void addAttackerModifiers (Entity damager) {
		if (damager instanceof EntityLiving livingDamager) { //TODO: move this to entity classes

			// TODO UNCOMMENT
			/*EffectManager effects = livingDamager.effects();
			
			EffectInstance strength = effects.get(Effects.STRENGTH);
			if (strength != null) {
				this.setModifier(this.baseDamage() * 0.3f * strength.effectLevel(), Modifier.STRENGTH);
			}
			EffectInstance weakness = effects.get(Effects.WEAKNESS);
			if (weakness != null) {
				this.setModifier(-(this.baseDamage() * 0.2f * weakness.effectLevel()), Modifier.WEAKNESS);
			}*/
		}
	}
	
	public void setKnockBack(float knockBack) {
		this.knockBack = knockBack;
	}

	/**
	 * Returns the attacking entity, or null if the attacker has been killed or closed.
	 */
	public @Nullable Entity damager () {
		Entity d = damager.get();
		return d == null || !d.isSpawned() ? null : d;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		if (depth > 0) {
			super.handle(handler, depth - 1);
			return;
		}
		handler.onEntityDamageByEntity(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}