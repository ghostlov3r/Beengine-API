package beengine.api.event.player;

import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.player.Player;

/**
 * Called after the player has successfully authenticated, before it spawns. The player is on the loading screen when
 * this is called.
 * Cancelling this event will cause the player to be disconnected with the kick message set.
 */
public class PlayerLoginEvent extends PlayerEvent implements Cancellable {
	
	private String disconnectionMessage;

	public PlayerLoginEvent (Player player, String kickMessage) {
		super(player);
		this.disconnectionMessage = kickMessage;
	}
	
	public String disconnectionMessage() {
		return disconnectionMessage;
	}
	
	public void setDisconnectionMessage(String disconnectionMessage) {
		this.disconnectionMessage = disconnectionMessage;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPlayerLogin(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}