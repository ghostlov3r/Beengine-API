package beengine.api.event.player;

import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.player.Player;

/**
 * Called when a player jumps
 */
public class PlayerJumpEvent extends PlayerEvent {

	public PlayerJumpEvent(Player player) {
		super(player);
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPlayerJump(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}