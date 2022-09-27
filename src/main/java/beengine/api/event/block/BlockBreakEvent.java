package beengine.api.event.block;

import beengine.api.block.Block;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.item.Item;
import beengine.api.player.Player;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Called when a player destroys a block somewhere in the world.
 */
@Accessors(fluent = true)
@Getter
public class BlockBreakEvent extends BlockEvent implements Cancellable {

    /**
     * The player who is destroying the block
     */
    private final Player player;

    /**
     * The item used to destroy the block
     */
    private final Item item;

    /**
     * Whether the block may be broken in less than the amount of time calculated.
     * This is usually true for creative players
     */
    private boolean isInstaBreak;
    
    private Item[] drops;

    /**
     * How much XP will be dropped by breaking this block
     */
    private int xpDropAmount;

    public final void setXpDropAmount (int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be at least zero");
        }
        this.xpDropAmount = amount;
    }
    
    public BlockBreakEvent(Player player, Block block, Item item, boolean isInstaBreak) {
        this(player, block, item, isInstaBreak, new Item[0]);
    }
    
    public BlockBreakEvent (Player player, Block block, Item item, boolean isInstaBreak, Item[] drops) {
        this(player, block, item, isInstaBreak, drops, 0);
    }

    public BlockBreakEvent (Player player, Block block, Item item, boolean isInstaBreak, Item[] drops, int xpDropAmount) {
        super(block);
        this.player = player;
        this.item = item;
        this.isInstaBreak = isInstaBreak;
        this.drops = drops;
        this.setXpDropAmount(xpDropAmount);
    }
    
    public void setDrops(Item[] drops) {
        this.drops = drops;
    }
    
    public void setInstaBreak(boolean instaBreak) {
        isInstaBreak = instaBreak;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onBlockBreak(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}