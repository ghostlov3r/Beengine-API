package beengine.api.event.player;

import beengine.api.entity.EntityHuman;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.event.entity.EntityEvent;
import lombok.*;

@Getter
public class PlayerExhaustEvent extends EntityEvent<EntityHuman> implements Cancellable {

	@Setter private float amount;
	private final Cause cause;

	public PlayerExhaustEvent (EntityHuman player, float amount, Cause cause) {
		super(player);
		this.amount = amount;
		this.cause = cause;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPlayerExhaust(this);
	}
	
	public enum Cause {
		ATTACK,
		DAMAGE,
		MINING,
		HEALTH_REGEN,
		POTION,
		WALKING,
		SPRINTING,
		SWIMMING,
		JUMPING,
		SPRINT_JUMPING,
		CUSTOM
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}