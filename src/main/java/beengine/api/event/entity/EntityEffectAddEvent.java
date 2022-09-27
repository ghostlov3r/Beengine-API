package beengine.api.event.entity;

import beengine.api.effect.EffectInstance;
import beengine.api.entity.Entity;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import lombok.Getter;

import javax.annotation.Nullable;

/**
 * Called when an effect is added to an Entity.
 */
@Getter
public class EntityEffectAddEvent extends EntityEffectEvent {

    private final @Nullable EffectInstance oldEffect;

    public EntityEffectAddEvent (Entity entity, EffectInstance effect) {
        this(entity, effect, null);
    }

    public EntityEffectAddEvent (Entity entity, EffectInstance effect, @Nullable EffectInstance oldEffect) {
        super(entity, effect);
        this.oldEffect = oldEffect;
    }

    /**
     * @return Whether the effect addition will replace an existing effect already applied to the entity.
     */
	public boolean willModify () {
	    return hasOldEffect();
    }

	public boolean hasOldEffect () {
	    return oldEffect != null;
    }
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onEntityEffectAdd(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}