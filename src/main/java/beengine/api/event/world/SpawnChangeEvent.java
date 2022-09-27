package beengine.api.event.world;

import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.world.Position;
import beengine.api.world.World;
import lombok.Getter;

/**
 * An event that is called when a world spawn changes.
 * The previous spawn is included
 */
@Getter
public class SpawnChangeEvent extends WorldEvent {

    private final Position previousSpawn;

    public SpawnChangeEvent(World world, Position previousSpawn) {
        super(world);
        this.previousSpawn = previousSpawn;
    }

    public final Position getNewSpawn() {
        return this.world.getSpawnPosition();
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onSpawnChange(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}