package beengine.api.math;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FMath {
	public static final float PI = (float) Math.PI;
	public static final float PI_2 = (float) (Math.PI / 2);
	public static final float SQRT3 = (float) Math.sqrt(3);
	public static final float SQRT2 = (float) Math.sqrt(2);
	
	public static final double PI_2d = Math.PI / 2;
	
	private static final float[] sinCosLookup = new float[65536];
	
	static {
		for(int i = 0; i < sinCosLookup.length; ++i) {
			sinCosLookup[i] = (float)Math.sin((double)i * 3.141592653589793D * 2.0D / 65536.0D);
		}
	}
	
	public static float sin(float var0) {
		return sinCosLookup[(int)(var0 * 10430.378F) & '\uffff'];
	}
	
	public static float cos(float var0) {
		return sinCosLookup[(int)(var0 * 10430.378F + 16384.0F) & '\uffff'];
	}
	
	public int floor(float v) {
		int i = (int) v;
		return (v >= i) ? i : i - 1;
	}
	
	public int ceil(float v) {
		int truncated = (int) v;
		return v > truncated ? truncated + 1 : truncated;
	}
	
	public boolean isEven(int v) {
		return (v % 2) == 0;
	}
	
	public boolean isEven(long v) {
		return (v % 2L) == 0L;
	}
	
	public boolean isOdd(int v) {
		return (v % 2) != 0;
	}
	
	public boolean isOdd(long v) {
		return (v % 2L) != 0L;
	}
	
	public int ensureEven(int v, String what) {
		if ((v % 2) != 0) {
			throw new IllegalArgumentException(what + " must be even, " + v + " given");
		}
		return v;
	}
	
	public long ensureEven(long v, String what) {
		if ((v % 2L) != 0L) {
			throw new IllegalArgumentException(what + " must be even, " + v + " given");
		}
		return v;
	}
	
	public int ensureOdd(int v, String what) {
		if ((v % 2) == 0) {
			throw new IllegalArgumentException(what + " must be odd, " + v + " given");
		}
		return v;
	}
	
	public long ensureOdd(long v, String what) {
		if ((v % 2L) == 0L) {
			throw new IllegalArgumentException(what + " must be odd, " + v + " given");
		}
		return v;
	}
	
	public static int ensurePositive(int v, String what) {
		if (v < 0) {
			throw new IllegalArgumentException(what + " must be positive, " + v + " given");
		}
		return v;
	}
	
	public static int ensureGreaterThanZero(int v, String what) {
		if (v <= 0) {
			throw new IllegalArgumentException(what + " must be greater than 0, " + v + " given");
		}
		return v;
	}
	
	public static long ensurePositive(long v, String what) {
		if (v < 0) {
			throw new IllegalArgumentException(what + " must be positive, " + v + " given");
		}
		return v;
	}
	
	public long ensureGreaterThanZero(long v, String what) {
		if (v <= 0L) {
			throw new IllegalArgumentException(what + " must be greater than 0L, " + v + " given");
		}
		return v;
	}
	
	public float ensurePositive(float v, String what) {
		if (v < 0.0f) {
			throw new IllegalArgumentException(what + " must be positive, " + v + " given");
		}
		return v;
	}
	
	public float ensureGreaterThanZero(float v, String what) {
		if (v <= 0.0f) {
			throw new IllegalArgumentException(what + " must be greater than 0, " + v + " given");
		}
		return v;
	}
	
	public int square(int v) {
		return v * v;
	}
	
	public long square(long v) {
		return v * v;
	}
	
	public float square(float v) {
		return v * v;
	}
	
	public double square(double v) {
		return v * v;
	}
	
	public static double round(double v, int precision) {
		if (precision == 0) {
			return Math.round(v);
		}
		int num = 1;
		for (int i = 0; i < precision; ++i) {
			num *= 10;
		}
		return Math.round(v * num) / (double) num;
	}
	
	public static float round(float v, int precision) {
		if (precision == 0) {
			return Math.round(v);
		}
		int num = 1;
		for (int i = 0; i < precision; ++i) {
			num *= 10;
		}
		return Math.round(v * num) / (float) num;
	}
	
	/**
	 * Solves a quadratic equation with the given coefficients and returns an array of up to two solutions.
	 */
	public static float[] solveQuadratic(float a, float b, float c) {
		float discriminant = b * b - 4 * a * c;
		if (discriminant > 0.0f) { //2 real roots
			float sqrtDiscriminant = (float) Math.sqrt(discriminant);
			return new float[]{
				(-b + sqrtDiscriminant) / (2 * a),
				(-b - sqrtDiscriminant) / (2 * a)
			};
		}
		if (discriminant == 0.0f) { //1 real root
			return new float[]{
				-b / (2 * a)
			};
		}
		//No real roots
		return new float[0];
	}
	
	public static float average (float[] values, int from, int cnt) {
		if (cnt == 0)
			return 0;
		float result = 0;
		for (int i = from; i < cnt; i++) {
			result += values[i];
		}
		return result / cnt;
	}
	
	public static float average (float[] values) {
		if (values.length == 0)
			return 0;
		float result = 0;
		for (float value : values) {
			result += value;
		}
		return result / values.length;
	}
	
	public int max (int ...ints) {
		int max = 0;
		for (int i : ints) {
			max = Math.max(max, i);
		}
		return max;
	}
	
	public float clamp (float value, float min, float max) {
		return Math.max(min, Math.min(max, value));
	}
}