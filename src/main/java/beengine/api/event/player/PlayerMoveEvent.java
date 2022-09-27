package beengine.api.event.player;

import beengine.api.entity.Location;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.player.Player;
import lombok.*;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class PlayerMoveEvent extends PlayerEvent implements Cancellable {

    private final Location startPoint;
    
    @Setter
    private Location endPoint;

    public PlayerMoveEvent(Player player, Location from, Location to) {
        super(player);
        this.startPoint = from;
        this.endPoint = to;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onPlayerMove(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}