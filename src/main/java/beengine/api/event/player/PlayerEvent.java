package beengine.api.event.player;

import beengine.api.event.Event;
import beengine.api.player.Player;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public abstract class PlayerEvent extends Event {

	protected Player player;
	
	public PlayerEvent (Player player) {
		this.player = player;
	}
}