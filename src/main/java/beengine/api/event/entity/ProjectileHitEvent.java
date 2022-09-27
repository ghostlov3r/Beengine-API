package beengine.api.event.entity;

import beengine.api.entity.projectile.EntityProjectile;
import beengine.api.event.AllowHandle;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.math.RayTraceResult;
import lombok.Getter;

@AllowHandle
@Getter
public abstract class ProjectileHitEvent extends EntityEvent<EntityProjectile> {
	/**
	 * A RayTraceResult object containing information such as the exact position struck,
	 * the AABB it hit, and the face of the AABB that it hit.
	 */
	private final RayTraceResult rayTraceResult;

	public ProjectileHitEvent(EntityProjectile entity, RayTraceResult rayTraceResult) {
		super(entity);
		this.rayTraceResult = rayTraceResult;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onProjectileHit(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}