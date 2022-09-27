package beengine.api.event.block;

import beengine.api.block.Block;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.math.Vector3;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlockTeleportEvent extends BlockEvent implements Cancellable {

    private Vector3 targetPos;

    public BlockTeleportEvent (Block block, Vector3 targetPos) {
        super(block);
        this.targetPos = targetPos;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onBlockTeleport(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}