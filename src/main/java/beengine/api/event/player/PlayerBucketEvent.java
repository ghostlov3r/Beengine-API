package beengine.api.event.player;

import beengine.api.block.Block;
import beengine.api.event.AllowHandle;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.item.Item;
import beengine.api.math.Facing;
import beengine.api.player.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllowHandle
@Accessors(fluent = true)
@Getter
public abstract class PlayerBucketEvent extends PlayerEvent implements Cancellable {

    private final Block blockClicked;
    private final Facing blockFace;

    /** The bucket used in this event  */
    private final Item bucket;

    /** the item in hand after the event  */ // todo rename
    @Setter private Item item;

    public PlayerBucketEvent(Player who, Block blockClicked, Facing blockFace, Item bucket, Item item) {
        super(who);
        this.blockClicked = blockClicked;
        this.blockFace = blockFace;
        this.bucket = bucket;
        this.item = item;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onPlayerBucket(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}