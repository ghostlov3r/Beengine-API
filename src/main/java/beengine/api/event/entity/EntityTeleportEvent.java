package beengine.api.event.entity;

import beengine.api.entity.Entity;
import beengine.api.entity.Location;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;

public class EntityTeleportEvent extends EntityEvent<Entity> implements Cancellable {
    
    private Location from;
    
    private Location destination;

    public EntityTeleportEvent (Entity entity, Location from, Location destination) {
        super(entity);
        this.from = from;
        this.destination = destination;
    }
    
    public Location from() {
        return from;
    }
    
    public Location destination() {
        return destination;
    }
    
    public void setDestination(Location destination) {
        this.destination = destination;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onEntityTeleport(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}