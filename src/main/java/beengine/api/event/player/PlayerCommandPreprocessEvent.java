package beengine.api.event.player;

import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.player.Player;

/**
 * Called when a player runs a command or chats, early in the process
 *
 * You don't want to use this except for a few cases like logging commands,
 * blocking commands on certain places, or applying modifiers.
 *
 * The message contains a slash at the start
 */

public class PlayerCommandPreprocessEvent extends PlayerEvent implements Cancellable {

    private String message;

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public String message() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public PlayerCommandPreprocessEvent(Player player, String message) {
        super(player);
        this.message = message;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onPlayerCommandPreprocess(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}