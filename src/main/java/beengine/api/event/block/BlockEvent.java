package beengine.api.event.block;

import beengine.api.block.Block;
import beengine.api.event.Event;

public abstract class BlockEvent extends Event {

	private final Block block;

	public BlockEvent (Block block) {
		this.block = block;
	}
	
	public Block block() {
		return block;
	}
}