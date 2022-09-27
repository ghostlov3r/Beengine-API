package beengine.api.event.player;

import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.player.Player;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Called when a player leaves the server
 */
@Getter
@Accessors(fluent = true)
public class PlayerKickEvent extends PlayerEvent implements Cancellable {

	/**
	 * String or TranslationContainer
	 */
	String reason;
	Object quitMessage;

	public PlayerKickEvent (Player player, String reason, Object quitMessage) {
		super(player);
		this.reason = reason;
		this.quitMessage = quitMessage;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public void setQuitMessage(Object quitMessage) {
		this.quitMessage = quitMessage;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPlayerKick(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}