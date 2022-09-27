package beengine.api.event.inventory;

import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.inventory.Inventory;
import beengine.api.player.Player;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class InventoryOpenEvent extends InventoryEvent implements Cancellable {

    /**
     * Who opened an inventory
     */
    private final Player player;

    public InventoryOpenEvent(Inventory inventory, Player player) {
        super(inventory);
        this.player = player;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onInventoryOpen(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}