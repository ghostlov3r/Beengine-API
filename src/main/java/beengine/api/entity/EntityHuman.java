package beengine.api.entity;

import beengine.api.entity.util.HungerManager;

public interface EntityHuman extends EntityLiving {

	HungerManager hungerManager();
}
