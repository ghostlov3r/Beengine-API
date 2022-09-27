package beengine.api.event.block;

import beengine.api.block.Block;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import lombok.Getter;

/**
 * Called when a block is burned away by fire.
 *
 * causingBlock The block (usually Fire) which caused the target block to be burned away.
 */
@Getter
public class BlockBurnEvent extends BlockEvent implements Cancellable {

    private final Block causingBlock;

    public BlockBurnEvent (Block block, Block causingBlock) {
        super(block);
        this.causingBlock = causingBlock;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onBlockBurn(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}