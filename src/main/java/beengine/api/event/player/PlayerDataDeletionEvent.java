package beengine.api.event.player;

import beengine.api.event.Event;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.annotation.Nullable;

/**
 * Called when a player's data is about to be deleted from a disk.
 *
 * Note that it is just deleting data from a disk, if saved before.
 */
@AllArgsConstructor
@Accessors(fluent = true)
@Getter
public class PlayerDataDeletionEvent extends Event {
	
	private final String playerName;
	
	/**
	 * The player whose data is being deleted, if online.
	 * If null, this data is for an offline player.
	 */
	private final @Nullable Player player;
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPlayerDataDeletion(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}
