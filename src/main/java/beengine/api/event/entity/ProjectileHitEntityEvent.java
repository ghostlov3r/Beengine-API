package beengine.api.event.entity;

import beengine.api.entity.Entity;
import beengine.api.entity.projectile.EntityProjectile;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.math.RayTraceResult;
import lombok.Getter;

@Getter
public class ProjectileHitEntityEvent extends ProjectileHitEvent {
    /**
     * The Entity struck by the projectile.
     */
    private final Entity entityHit;

    public ProjectileHitEntityEvent(EntityProjectile entity, RayTraceResult rayTraceResult, Entity entityHit) {
    	super(entity, rayTraceResult);
        this.entityHit = entityHit;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        if (depth > 0) {
            super.handle(handler, depth - 1);
            return;
        }
        handler.onProjectileHitEntity(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}