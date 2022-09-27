package beengine.api.event.player;

import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.player.Player;
import lombok.Getter;

@Getter
public class PlayerToggleSneakEvent extends PlayerEvent implements Cancellable {

    private final boolean isSneaking;

    public PlayerToggleSneakEvent(Player player, boolean isSneaking) {
        super(player);
        this.isSneaking = isSneaking;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onPlayerToggleSneak(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}
