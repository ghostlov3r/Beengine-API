package beengine.api.effect;

import beengine.api.util.Color;
import beengine.api.util.Utils;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

/**
 * @property isVisible Whether this effect will produce some visible effect, such as bubbles or particles.
 *
 * @property isAmbient Whether the effect originated copyOf the ambient environment.
 * 					   Ambient effects can originate copyOf things such as a Beacon's area of effect radius.
 * 					   If this flag is set, the amount of visible particles will be reduced by a factor of 5.
 *
 * @property color	   The particle colour of this effect instance.
 * 					   This can be overridden on a per-EffectInstance basis,
 * 					   so it is not reflective of the default colour of the effect.
 */
@Accessors(fluent = true)
@Getter

public class Effect implements Cloneable {

	public static final Effect[] EMPTY_ARRAY = new Effect[0];
	
	private final EffectType type;
	private int duration;
	private int amplifier;
	private boolean isVisible;
	private boolean isAmbient;
	private Color color;
	
	public Effect(
		EffectType type
	) {
		this(type, type.defaultDuration());
	}
	
	public Effect(
		EffectType type,
		int duration // = type.defaultDuration,
	) {
		this(type, duration, 0);
	}
	
	public Effect(
		EffectType type,
		int duration, // = type.defaultDuration,
		int amplifier // = 0,
	) {
		this(type, duration, amplifier, true);
	}
	
	public Effect(
		EffectType type,
		int duration, // = type.defaultDuration,
		int amplifier, // = 0,
		boolean isVisible // = true,
	) {
		this(type, duration, amplifier, isVisible, false);
	}
	
	public Effect(
		EffectType type,
		int duration, // = type.defaultDuration,
		int amplifier, // = 0,
		boolean isVisible, // = true,
		boolean isAmbient // = false,
	) {
		this(type, duration, amplifier, isVisible, isAmbient, type.color());
	}
	
	public Effect(
		EffectType type,
		int duration, // = type.defaultDuration,
		int amplifier, // = 0,
		boolean isVisible, // = true,
		boolean isAmbient, // = false,
		Color color // = type.color
	) {
		validateDuration(duration);
		validateAmplifier(amplifier);
		
		this.type = type;
		this.duration = duration;
		this.amplifier = amplifier;
		this.isVisible = isVisible;
		this.isAmbient = isAmbient;
		this.color = color;
	}
	
	public Effect setVisible(boolean visible) {
		isVisible = visible;
		return this;
	}
	
	public Effect setAmbient(boolean ambient) {
		isAmbient = ambient;
		return this;
	}
	
	public Effect setColor(Color color) {
		this.color = color;
		return this;
	}
	
	/**
	 * The number of ticks remaining until the effect expires
	 */
	public Effect setDuration(int duration) {
		this.duration = validateDuration(duration);
		return this;
	}

	public void resetDuration () {
		setDuration(type.defaultDuration());
	}

	/**
	 * Decreases the duration by the given number of ticks, without dropping below zero.
	 */
	public void decreaseDuration (int ticks) {
		duration = Math.max(0, this.duration - ticks);
	}

	/**
	 * Returns whether the duration has run out.
	 */
	public boolean hasExpired () {
		return duration <= 0;
	}

	/**
	 * Returns the level of this effect, which is always one higher than the amplifier.
	 */
	public int effectLevel () {
		return amplifier + 1;
	}
	
	public Effect setAmplifier(int amplifier) {
		this.amplifier = validateAmplifier(amplifier);
		return this;
	}

	/**
	 * Resets the colour of this EffectInstance to the default specified by its type.
	 */
	public void resetColor () {
		this.setColor(type.color());
	}

	private static int validateDuration(int value) {
		if (value <= 0) {
			throw new IllegalArgumentException("duration should be positive");
		}
		return value;
	}

	private static int validateAmplifier(int value) {
		if (!Utils.inRange(value, 0, 255)) {
			throw new IllegalArgumentException("Amplifier must be in range 0 - 255, got "+value);
		}
		return value;
	}
	
	@SneakyThrows
	@Override
	public Effect clone() {
		return (Effect) super.clone();
	}
}