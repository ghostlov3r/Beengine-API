package beengine.api.event.entity;

import beengine.api.block.Block;
import beengine.api.entity.projectile.EntityProjectile;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.math.RayTraceResult;
import lombok.Getter;

@Getter
public class ProjectileHitBlockEvent extends ProjectileHitEvent {
	/**
	 * The Block struck by the projectile.
	 * Hint: to get the block face hit, look at the RayTraceResult.
	 */
	private final Block blockHit;

	public ProjectileHitBlockEvent(EntityProjectile entity, RayTraceResult rayTraceResult, Block blockHit) {
		super(entity, rayTraceResult);
		this.blockHit = blockHit;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		if (depth > 0) {
			super.handle(handler, depth - 1);
			return;
		}
		handler.onProjectileHitBlock(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}