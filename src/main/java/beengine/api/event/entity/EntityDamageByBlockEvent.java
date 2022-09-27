package beengine.api.event.entity;

import beengine.api.block.Block;
import beengine.api.entity.Entity;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import lombok.Getter;

/**
 * Called when an entity takes damage from a block.
 */
@Getter
public class EntityDamageByBlockEvent extends EntityDamageEvent {
	
	private Block damager;
	
	public EntityDamageByBlockEvent (Block damager, Entity entity, Cause cause, float damage) {
		this(damager, entity, cause, damage, new float[16]);
	}

	public EntityDamageByBlockEvent (Block damager, Entity entity, Cause cause, float damage, float[] modifiers) {
		super(entity, cause, damage, modifiers);
		this.damager = damager;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		if (depth > 0) {
			super.handle(handler, depth - 1);
			return;
		}
		handler.onEntityDamageByBlock(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}