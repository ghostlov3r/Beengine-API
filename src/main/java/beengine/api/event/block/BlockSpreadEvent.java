package beengine.api.event.block;

import beengine.api.block.Block;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import lombok.Getter;

/**
 * Called when a block spreads to another block, such as grass spreading to nearby dirt blocks.
 */
@Getter
public class BlockSpreadEvent extends BlockChangeEvent {

	private final Block source;

	public BlockSpreadEvent (Block block, Block source, Block newState) {
		super(block, newState);
		this.source = source;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onBlockSpread(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}