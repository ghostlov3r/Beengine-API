package beengine.api.event.player;

import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.player.Player;
import beengine.api.world.Position;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Called when a player is respawned
 */
@Getter
@Accessors(fluent = true)
public class PlayerRespawnEvent extends PlayerEvent {

	private Position respawnPosition;

	public PlayerRespawnEvent(Player player, Position respawnPosition) {
		super(player);
		this.respawnPosition = respawnPosition;
	}

	public final void setRespawnPosition(Position position) {
		if (!position.isWorldValid()) {
			throw new IllegalArgumentException("Spawn position must reference a valid and loaded World");
		}
		this.respawnPosition = position;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPlayerRespawn(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}