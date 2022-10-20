package beengine.api.entity.util;

import beengine.api.event.player.PlayerExhaustEvent;

public interface HungerManager {
	float food();

	void setFood(float value);

	float maxFood();

	void addFood(float amount);

	boolean isHungry();

	float saturation();

	void setSaturation(float saturation);

	void addSaturation(float amount);

	float exhaustion();

	void setExhaustion(float exhaustion);

	float exhaust(float amount);

	float exhaust(float amount, PlayerExhaustEvent.Cause cause);

	int getFoodTickTimer();

	void setFoodTickTimer(int foodTickTimer);

	boolean isEnabled();

	void setEnabled(boolean enabled);
}
