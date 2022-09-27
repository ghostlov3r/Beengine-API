package beengine.api.event.player;

import beengine.api.entity.Entity;
import beengine.api.entity.projectile.EntityFishingHook;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.player.Player;

import javax.annotation.Nullable;

public class PlayerFishEvent extends PlayerEvent implements Cancellable {
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPlayerFish(this);
	}
	
	public enum State {
		FISHING,
		CAUGHT_FISH,
		CAUGHT_ENTITY,
	}
	
	protected EntityFishingHook hook;
	protected int xpDropAmount;
	protected State state;
	
	public PlayerFishEvent(Player fisher, EntityFishingHook hook, State state) {
		this(fisher, hook, state, 0);
	}
	
	public PlayerFishEvent(Player fisher, EntityFishingHook hook, State state, int xpDropAmount) {
		super(fisher);
		this.hook = hook;
		this.state = state;
		this.xpDropAmount = xpDropAmount;
	}

	// TODO uncomment
	/*@Nullable
	public Entity caughtEntity() {
		return this.hook.riding;
	}*/
	
	public EntityFishingHook hook() {
		return hook;
	}
	
	public int xpDropAmount() {
		return xpDropAmount;
	}
	
	public void setXpDropAmount(int xpDropAmount) {
		this.xpDropAmount = xpDropAmount;
	}
	
	public State state() {
		return state;
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}
