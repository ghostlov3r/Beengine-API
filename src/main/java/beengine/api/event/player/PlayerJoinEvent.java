package beengine.api.event.player;

import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.player.Player;

/**
 * Called when the player spawns in the world after logging in, when they first see the terrain.
 *
 * Note: A lot of data is sent to the player between login and this event. Disconnecting the player during this event
 * will cause this data to be wasted. Prefer disconnecting at login-time if possible to minimize bandwidth wastage.
 * @see PlayerLoginEvent
 */
public class PlayerJoinEvent extends PlayerEvent {
	// todo add method with string param
	
	Object joinMessage;
	
	public PlayerJoinEvent (Player player, Object joinMessage) {
		super(player);
		this.joinMessage = joinMessage;
	}
	
	public Object joinMessage() {
		return joinMessage;
	}
	
	public void setJoinMessage(Object joinMessage) {
		this.joinMessage = joinMessage;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPlayerJoin(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}