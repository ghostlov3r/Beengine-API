package beengine.api.event.player;

import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.player.Player;
import lombok.Getter;

@Getter
public class PlayerToggleFlightEvent extends PlayerEvent implements Cancellable {

    private final boolean isFlying;

    public PlayerToggleFlightEvent(Player player, boolean isFlying) {
        super(player);
        this.isFlying = isFlying;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onPlayerToggleFlight(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}