package beengine.api.event.block;

import beengine.api.block.Block;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;

/**
 * Called when a block tries to be updated due to a neighbor change
 */
public class BlockUpdateEvent extends BlockEvent {

	public BlockUpdateEvent (Block block) {
		super(block);
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onBlockUpdate(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}