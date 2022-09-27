package beengine.api.event.entity;

import beengine.api.block.Block;
import beengine.api.entity.Entity;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityCombustByBlockEvent extends EntityCombustEvent {

    private Block combuster;

    public EntityCombustByBlockEvent (Block combuster, Entity combustee, int duration) {
        super(combustee, duration);
        this.combuster = combuster;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        if (depth > 0) {
            super.handle(handler, depth - 1);
            return;
        }
        handler.onEntityCombustByBlock(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}