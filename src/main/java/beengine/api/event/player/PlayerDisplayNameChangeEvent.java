package beengine.api.event.player;

import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.player.Player;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class PlayerDisplayNameChangeEvent extends PlayerEvent {
	
	private final String oldName;
	private final String newName;
	
	public PlayerDisplayNameChangeEvent(Player player, String oldName, String newName) {
		super(player);
		this.oldName = oldName;
		this.newName = newName;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPlayerDisplayNameChange(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}
