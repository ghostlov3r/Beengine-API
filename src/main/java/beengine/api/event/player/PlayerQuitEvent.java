package beengine.api.event.player;

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
public class PlayerQuitEvent extends PlayerEvent {
	
	private Object quitMessage;
	private final String quitReason;

	public PlayerQuitEvent (Player player, Object quitMessage, String quitReason) {
		super(player);
		this.quitMessage = quitMessage;
		this.quitReason = quitReason;
	}
	
	public void setQuitMessage(Object quitMessage) {
		this.quitMessage = quitMessage;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPlayerQuit(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}