package beengine.api.event.player;

import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.player.Player;
import lombok.*;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class PlayerTransferEvent extends PlayerEvent implements Cancellable {

    private String address;
    private int port;
    private String message;

    public PlayerTransferEvent(Player player, String address, int port, String message) {
        super(player);
        this.address = address;
        this.port = port;
        this.message = message;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setPort(int port) {
        this.port = port;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onPlayerTransfer(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}