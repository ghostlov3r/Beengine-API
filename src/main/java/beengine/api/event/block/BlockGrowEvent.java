package beengine.api.event.block;

import beengine.api.block.Block;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import lombok.Getter;

/**
 * Called when plants or crops grow.
 */
@Getter
public class BlockGrowEvent extends BlockChangeEvent implements Cancellable {
    
    public BlockGrowEvent(Block block, Block newState) {
        super(block, newState);
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onBlockGrow(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}