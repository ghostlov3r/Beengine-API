package beengine.api.event.entity;

import beengine.api.entity.EntityLiving;
import beengine.api.entity.projectile.EntityProjectile;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.item.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
public class EntityShootBowEvent extends EntityEvent<EntityLiving> implements Cancellable {

	private final Item bow;
	@Setter private float force;
	private EntityProjectile projectile;

	public void setProjectile (EntityProjectile proj) {
		if (proj != projectile) {
			if (projectile.viewers().size() == 0)
				projectile.despawn();

			projectile = proj;
		}
	}

	public EntityShootBowEvent (EntityLiving shooter, Item bow, EntityProjectile projectile, float force) {
		super(shooter);
		this.bow = bow;
		this.force = force;
		this.projectile = projectile;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onEntityShootBow(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}