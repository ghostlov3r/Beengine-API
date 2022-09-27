package beengine.api.event.block;

import beengine.api.block.Block;

public abstract class BlockChangeEvent extends BlockEvent {
	
	private final Block newState;
	
	public BlockChangeEvent(Block block, Block newState) {
		super(block);
		this.newState = newState;
	}
	
	public Block newState() {
		return newState;
	}
}
