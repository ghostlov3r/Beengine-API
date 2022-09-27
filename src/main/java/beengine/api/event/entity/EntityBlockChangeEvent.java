package beengine.api.event.entity;

import beengine.api.block.Block;
import beengine.api.entity.Entity;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import lombok.Getter;

/**
 * Called when an Entity, excluding players, changes a block directly
 */
@Getter
public class EntityBlockChangeEvent extends EntityEvent<Entity> implements Cancellable {

    private final Block blockFrom;
    private final Block blockTo;

    public EntityBlockChangeEvent (Entity entity, Block blockFrom, Block blockTo) {
        super(entity);
        this.blockFrom = blockFrom;
        this.blockTo = blockTo;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onEntityBlockChange(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}