package beengine.api.event.player;

import beengine.api.event.Cancellable;
import beengine.api.event.Event;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.player.Player;
import lombok.*;
import lombok.experimental.Accessors;

import javax.annotation.Nullable;

/**
 * Called when a player's data is about to be saved to disk.
 */
@AllArgsConstructor
@Accessors(fluent = true)
@Getter
public class PlayerDataSaveEvent extends Event implements Cancellable {

	// TODO uncomment
	//private NbtMap data;

	private final String playerName;
	
	/**
	 * The player whose data is being saved, if online.
	 * If null, this data is for an offline player (possibly just disconnected).
	 */
	private final @Nullable Player player;
	
	/**
	 * Sets the data to be written to disk as a CompoundTag
	 */
	/*public void setData (NbtMap data) {
		this.data = data;
	}*/
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPlayerDataSave(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}