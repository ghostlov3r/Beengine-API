package beengine.api.event.inventory;

import beengine.api.event.Event;
import beengine.api.inventory.Inventory;
import beengine.api.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public abstract class InventoryEvent extends Event {

	private final Inventory inventory;

	public final Set<Player> viewers() {
		return this.inventory.viewers();
	}

}