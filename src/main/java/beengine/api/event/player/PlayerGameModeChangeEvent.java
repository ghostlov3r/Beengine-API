package beengine.api.event.player;

import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.player.GameMode;
import beengine.api.player.Player;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * Called when a player has its gamemode changed
 */
@Accessors(fluent = true)
@Getter
public class PlayerGameModeChangeEvent extends PlayerEvent implements Cancellable {

    private GameMode newGamemode;

    public PlayerGameModeChangeEvent(Player player, GameMode newGamemode) {
        super(player);
        this.newGamemode = newGamemode;
    }
    
    public void setNewGamemode(GameMode newGamemode) {
        this.newGamemode = newGamemode;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onPlayerGameModeChange(this);
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}