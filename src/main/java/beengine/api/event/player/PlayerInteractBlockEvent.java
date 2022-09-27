package beengine.api.event.player;

import beengine.api.block.Block;
import beengine.api.event.RegisteredListener;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.item.Item;
import beengine.api.math.Facing;
import beengine.api.math.Vector3;
import beengine.api.player.Player;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Called when a player interacts or touches a block (including air?)
 */
@Accessors(fluent = true)
@Getter
public class PlayerInteractBlockEvent extends PlayerInteractEvent implements Cancellable {
    
    private final Block blockTouched;
    private final Facing blockFace;
    private final Vector3 touchVector;
    private final Action action;

    public enum Action {
        LEFT_CLICK,
        RIGHT_CLICK;
    }

    public PlayerInteractBlockEvent(Player player, Item item, Block blockTouched, Facing blockFace) {
        this(player, item, blockTouched, blockFace, new Vector3());
    }

    public PlayerInteractBlockEvent(Player player, Item item, Block blockTouched, Facing blockFace, Vector3 touchVector) {
        this(player, item, blockTouched, blockFace, touchVector, Action.RIGHT_CLICK);
    }
    
    public PlayerInteractBlockEvent(Player player, Item item, Block blockTouched, Facing blockFace, Action action) {
        this(player, item, blockTouched, blockFace, new Vector3(), action);
    }
    
    public PlayerInteractBlockEvent(Player player, Item item, Block blockTouched, Facing blockFace, Vector3 touchVector, Action action) {
        super(player, item);
        this.blockTouched = blockTouched;
        this.blockFace = blockFace;
        this.touchVector = touchVector;
        this.action = action;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        if (depth > 0) {
            super.handle(handler, depth - 1);
            return;
        }
        handler.onPlayerInteractBlock(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}