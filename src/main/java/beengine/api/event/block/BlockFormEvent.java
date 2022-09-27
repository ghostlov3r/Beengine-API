package beengine.api.event.block;

import beengine.api.block.Block;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;

/**
 * Called when a new block forms, usually as the result of some action.
 * This could be things like obsidian forming due to collision of lava and water.
 */
public class BlockFormEvent extends BlockChangeEvent {
	
	public BlockFormEvent (Block block, Block newState) {
		super(block, newState);
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onBlockForm(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}