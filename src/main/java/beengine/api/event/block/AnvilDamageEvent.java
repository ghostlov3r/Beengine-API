package beengine.api.event.block;

import beengine.api.block.Block;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.player.Player;

public class AnvilDamageEvent extends BlockEvent implements Cancellable {
    
    public enum DamageCause {
        USE,
        FALL
    }
    
    private final int oldDamage;
    private int newDamage;
    private DamageCause cause;
    private final Player player;

    public AnvilDamageEvent(Block block, int oldDamage, int newDamage, DamageCause cause, Player player) {
        super(block);
        this.oldDamage = oldDamage;
        this.newDamage = newDamage;
        this.cause = cause;
        this.player = player;
    }

    public int oldDamage() {
        return this.oldDamage;
    }

    public int newDamage() {
        return this.newDamage;
    }

    public void setNewDamage(int newDamage) {
        this.newDamage = newDamage;
    }

    public DamageCause cause() {
        return this.cause;
    }

    public Player player() {
        return this.player;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onAnvilDamage(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}