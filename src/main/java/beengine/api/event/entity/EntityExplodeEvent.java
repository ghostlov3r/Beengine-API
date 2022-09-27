package beengine.api.event.entity;

import beengine.api.block.Block;
import beengine.api.entity.Entity;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.world.World;
import lombok.Getter;

import java.util.Collection;

/**
 * Called when a entity explodes
 */
@Getter
public class EntityExplodeEvent extends EntityEvent<Entity> implements Cancellable {

    private final float x, y, z;
    private final World world;
    private final Collection<Block> blockList;
    private final float yield;

    public EntityExplodeEvent (Entity entity, World world, float x, float y, float z, Collection<Block> blockList, float yield) {
        super(entity);
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.blockList = blockList;
        this.yield = yield;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onEntityExplode(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}