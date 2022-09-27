package beengine.api.event.player;

import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.item.Item;
import beengine.api.player.Player;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Called when a player tries to drop an item from its inventory
 */
@Accessors(fluent = true)
@Getter
public class PlayerDropItemEvent extends PlayerEvent implements Cancellable {

    private final Item item;

    public PlayerDropItemEvent(Player player, Item drop) {
        super(player);
        this.item = drop;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onPlayerDropItem(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}