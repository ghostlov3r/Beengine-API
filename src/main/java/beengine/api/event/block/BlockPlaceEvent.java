package beengine.api.event.block;

import beengine.api.block.Block;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.item.Item;
import beengine.api.player.Player;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Called when a player places a block
 */
@Accessors(fluent = true)
@Getter
public class BlockPlaceEvent extends BlockEvent implements Cancellable {
	
	private final Player player;
	private final Block blockReplaced;
	private final Block blockAgainst;
	private final Item itemInHand;
	
	public BlockPlaceEvent(Player player, Block blockPlace, Block blockReplaced, Block blockAgainst, Item itemInHand) {
		super(blockPlace);
		this.player = player;
		this.blockReplaced = blockReplaced;
		this.blockAgainst = blockAgainst;
		this.itemInHand = itemInHand;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onBlockPlace(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}