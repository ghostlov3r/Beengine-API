package beengine.api.event.entity;

import beengine.api.entity.Entity;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;

import javax.annotation.Nullable;
import java.lang.ref.WeakReference;

/**
 * Called when an entity takes damage from an entity sourced from another entity, for example being hit by a snowball thrown by a Player.
 */
public class EntityDamageByChildEntityEvent extends EntityDamageByEntityEvent {
	
	private WeakReference<Entity> childEntity;
	
	public EntityDamageByChildEntityEvent (Entity damager, Entity childEntity, Entity entity, Cause cause, float damage) {
		this(damager, childEntity, entity, cause, damage, new float[16]);
	}

	public EntityDamageByChildEntityEvent (Entity damager, Entity childEntity, Entity entity, Cause cause, float damage, float[] modifiers) {
		super(damager, entity, cause, damage, modifiers);
		this.childEntity = new WeakReference<>(childEntity);
	}

	/**
	 * Returns the entity which caused the damage, or null if the entity has been killed or closed.
	 */
	public @Nullable Entity child () {
		Entity e = childEntity.get();
		return e == null || !e.isSpawned() ? null : e;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		if (depth > 0) {
			super.handle(handler, depth - 1);
			return;
		}
		handler.onEntityDamageByChildEntity(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}