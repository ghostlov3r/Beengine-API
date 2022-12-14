package beengine.api.event.inventory;

import beengine.api.entity.projectile.EntityArrow;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.inventory.Inventory;

public class InventoryPickupArrowEvent extends InventoryEvent implements Cancellable {

    private final EntityArrow arrow;

    public InventoryPickupArrowEvent(Inventory inventory, EntityArrow arrow) {
        super(inventory);
        this.arrow = arrow;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onInventoryPickupArrow(this);
    }
    
    public EntityArrow arrow() {
        return this.arrow;
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}