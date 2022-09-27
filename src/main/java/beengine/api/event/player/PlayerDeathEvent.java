package beengine.api.event.player;

import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.event.entity.EntityDeathEvent;
import beengine.api.item.Item;
import beengine.api.player.Player;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static beengine.api.event.entity.EntityDamageEvent.Cause.*;

@Getter
@Setter
public class PlayerDeathEvent extends EntityDeathEvent {

	@Getter(AccessLevel.NONE)
	private Object deathMessage;
	private boolean keepInventory = false;
	
	/**
	 * @param deathMessage Null will cause the default vanilla message to be used
	 */
	public PlayerDeathEvent (Player entity, List<Item> drops, int xp, Object deathMessage){
		super(entity, drops, xp);
		this.deathMessage = (deathMessage != null ? deathMessage : "");
	}
	
	public void setMessage (Object message) {
		setDeathMessage(message);
	}
	
	@Override
	public Player entity () {
		return (Player) this.entity;
	}

	public Player player () {
		return (Player) this.entity;
	}
	
	public Object deathMessage() {
		return deathMessage;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		if (depth > 0) {
			super.handle(handler, depth - 1);
			return;
		}
		handler.onPlayerDeath(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}