package beengine.api.event.block;

import beengine.api.block.Block;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;

/**
 * Called when leaves decay due to not being attached to wood.
 */
public class LeavesDecayEvent extends BlockEvent implements Cancellable {

	public LeavesDecayEvent(Block block) {
		super(block);
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onLeavesDecay(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}