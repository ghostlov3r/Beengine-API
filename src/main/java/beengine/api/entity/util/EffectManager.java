package beengine.api.entity.util;

import beengine.api.effect.Effect;
import beengine.api.effect.EffectType;
import beengine.api.util.Color;

import javax.annotation.Nullable;

public interface EffectManager {
	void clear();

	Effect[] all();

	void remove(EffectType effectType);

	@Nullable
	Effect get(EffectType effect);

	boolean has(EffectType effect);

	boolean add(Effect effect);

	Color bubbleColor();
}
