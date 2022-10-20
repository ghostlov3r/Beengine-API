package beengine.api.event.entity;

import beengine.api.effect.Effect;
import beengine.api.entity.Entity;
import beengine.api.event.Cancellable;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public abstract class EntityEffectEvent extends EntityEvent<Entity> implements Cancellable {

    protected final Effect effect;

    public EntityEffectEvent (Entity entity, Effect effect) {
        super(entity);
        this.effect = effect;
    }
}