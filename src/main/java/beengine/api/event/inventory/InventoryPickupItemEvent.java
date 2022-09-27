package beengine.api.event.inventory;

import beengine.api.entity.obj.ItemEntity;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.inventory.Inventory;

public class InventoryPickupItemEvent extends InventoryEvent implements Cancellable {

    private final ItemEntity item;

    public InventoryPickupItemEvent(Inventory inventory, ItemEntity item) {
        super(inventory);
        this.item = item;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onInventoryPickupItem(this);
    }
    
    public ItemEntity item() {
        return this.item;
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}