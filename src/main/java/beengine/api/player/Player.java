package beengine.api.player;

import beengine.api.entity.Entity;
import beengine.api.entity.EntityHuman;
import beengine.api.item.Item;
import beengine.api.math.Vector3;

public interface Player extends EntityHuman {

	void setAllowFlight(boolean value);

	void setFlying(boolean value);

	boolean isFlightAllowed();

	void setDisplayName(String name);

	boolean canSee(Player player);

	void hidePlayer(Player player);

	void showPlayer(Player player);

	void showAllPlayers();

	boolean isOnline();

	boolean isFlying();

	boolean isUsingItem();

	boolean hasItemCooldown(Item item);

	void resetItemCooldown(Item item);

	void resetItemCooldown(Item item, int ticks);

	int getItemCooldownExpiry(Item item);

	boolean isSleeping();

	boolean selectHotbarSlot(int hotbarSlot);

	boolean sleepOn(Vector3 pos);

	void stopSleep();

	boolean chat(String message);

	boolean useHeldItem();

	boolean consumeHeldItem();

	boolean releaseHeldItem();

	boolean attackEntity(Entity entity);

	boolean toggleSprint(boolean sprint);

	boolean toggleSneak(boolean sneak);

	boolean toggleFlight(boolean fly);

	void dropItem(Item item);

	void sendTitle(String title);

	void sendTitle(String title, String subtitle);

	void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut);

	void sendSubTitle(String subtitle);

	void sendActionBarMessage(String message);

	void removeTitles();

	void resetTitles();

	void setTitleDuration(int fadeIn, int stay, int fadeOut);

	void sendPopup(String message);

	void sendTip(String message);

	boolean transfer(String address);

	boolean transfer(String address, int port);

	boolean transfer(String address, int port, String message);

	boolean kick();

	boolean kick(String reason);

	boolean kick(String reason, Object quitMessage);

	String displayName();

	int viewDistance();
}
