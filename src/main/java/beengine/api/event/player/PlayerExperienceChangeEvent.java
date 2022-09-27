package beengine.api.event.player;

import beengine.api.entity.EntityHuman;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.event.entity.EntityEvent;
import lombok.*;
import lombok.experimental.Accessors;

import javax.annotation.Nullable;

/**
 * Called when a player gains or loses XP levels and/or progress.
 */
@Accessors(fluent = true)
@Getter
public class PlayerExperienceChangeEvent extends EntityEvent<EntityHuman> implements Cancellable {

	private final int oldLevel;
	private final float oldProgress;

	@Setter private @Nullable Integer newLevel;
	@Setter private @Nullable Float newProgress;
	
	public PlayerExperienceChangeEvent(EntityHuman player, int oldLevel, float oldProgress, @Nullable Integer newLevel, @Nullable Float newProgress) {
		this(player, Cause.UNKNOWN, oldLevel, oldProgress, newLevel, newProgress);
	}
	
	public PlayerExperienceChangeEvent(EntityHuman player, Cause cause, int oldLevel, float oldProgress, @Nullable Integer newLevel, @Nullable Float newProgress) {
		super(player);
		this.oldLevel = oldLevel;
		this.oldProgress = oldProgress;
		this.newLevel = newLevel;
		this.newProgress = newProgress;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPlayerExperienceChange(this);
	}
	
	public enum Cause {
		ANVIL_USE,
		ENCHANTING_TABLE_USE,
		EXPERIENCE_ORB_PICKUP,
		DEATH,
		UNKNOWN
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}