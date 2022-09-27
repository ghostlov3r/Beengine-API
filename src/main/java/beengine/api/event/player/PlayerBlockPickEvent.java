package beengine.api.event.player;

import beengine.api.block.Block;
import beengine.api.event.RegisteredListener;
import beengine.api.item.Item;
import beengine.api.player.Player;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import lombok.Getter;

/**
 * Called when a player middle-clicks on a block to get an item in creative mode.
 */
@Getter
public class PlayerBlockPickEvent extends PlayerEvent implements Cancellable {

    private final Block block;
    private final Item resultItem;

    public PlayerBlockPickEvent(Player player, Block block, Item resultItem) {
        super(player);
        this.block = block;
        this.resultItem = resultItem;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onPlayerBlockPick(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}