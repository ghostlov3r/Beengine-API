package beengine.api.event.player;

import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.item.Item;
import beengine.api.math.Vector3;
import beengine.api.player.Player;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Called when a player uses its held item, for example when throwing a projectile.
 */
@Accessors(fluent = true)
@Getter
public class PlayerItemUseEvent extends PlayerEvent implements Cancellable {

	/**
	 * The item used.
	 */
	private final Item item;

	/**
	 * The direction the player is aiming when activating this item.
	 * Used for projectile direction.
	 */
	private final Vector3 directionVector;

	public PlayerItemUseEvent(Player player, Item item, Vector3 directionVector) {
		super(player);
		this.item = item;
		this.directionVector = directionVector;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPlayerItemUse(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}