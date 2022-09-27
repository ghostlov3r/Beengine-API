package beengine.api.entity.projectile;

import beengine.api.entity.Entity;

public interface EntityProjectile extends Entity {
	float baseDamage();

	void setBaseDamage(float damage);

	int resultDamage();
}
