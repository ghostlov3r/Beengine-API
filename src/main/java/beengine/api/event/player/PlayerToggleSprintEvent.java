package beengine.api.event.player;

import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.player.Player;
import lombok.Getter;

@Getter
public class PlayerToggleSprintEvent extends PlayerEvent implements Cancellable {

    private final boolean isSprinting;

    public PlayerToggleSprintEvent(Player player, boolean isSprinting) {
        super(player);
        this.isSprinting = isSprinting;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onPlayerToggleSprint(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}