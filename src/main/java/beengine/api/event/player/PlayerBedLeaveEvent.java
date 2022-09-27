package beengine.api.event.player;

import beengine.api.block.Block;
import beengine.api.event.RegisteredListener;
import beengine.api.player.Player;
import beengine.api.event.EventListener;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class PlayerBedLeaveEvent extends PlayerEvent {

	private final Block bed;

	public PlayerBedLeaveEvent(Player player, Block bed) {
		super(player);
		this.bed = bed;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPlayerBedLeave(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}