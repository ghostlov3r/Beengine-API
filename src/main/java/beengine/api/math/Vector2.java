package beengine.api.math;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Vector2 {
	
	public static final Vector2 ZERO = new Vector2(0, 0);
	public static final Vector2 ONE = new Vector2(1, 1);
	
	public final float x;
	public final float y;
	
	public Vector2 (double x, double y) {
		this((float) x, (float) y);
	}
	
	public int floorX() { return FMath.floor(x); }
	public int floorY() { return FMath.floor(y); }

	public Vector2 add(Vector2 vec) { return new  Vector2(x + vec.x, y + vec.y); }

	public Vector2 add(float x, float y) { return new  Vector2(this.x + x, this.y + y); }

	public Vector2 subtract(Vector2 vec) { return new  Vector2(x - vec.x, y - vec.y); }

	public Vector2 subtract(float x, float y) { return new Vector2(this.x - x, this.y - y); }

	public Vector2 ceil() { return new Vector2(FMath.ceil(x), FMath.ceil(y)); }

	public Vector2 floor() { return new Vector2(FMath.floor(x), FMath.floor(y)); }

	public Vector2 round() { return new Vector2(Math.round(x), Math.round(y)); }

	public Vector2 abs() { return new Vector2(Math.abs(x), Math.abs(y)); }

	public Vector2 multiply(float number) { return new  Vector2(x * number, y * number); }

	public Vector2 divide(float number) { return new  Vector2(x / number, y / number); }

	public float distance(Vector2 vec) { return (float) Math.sqrt(this.distanceSquared(vec.x, vec.y)); }

	public float distance(float x, float y) { return (float) Math.sqrt(this.distanceSquared(x, y)); }

	public float distanceSquared(Vector2 vec) { return distanceSquared(vec.x, vec.y); }

	public float distanceSquared(float x, float y) { return FMath.square(this.x - x) + FMath.square(this.y - y); }

	public float length() { return (float) Math.sqrt(lengthSquared()); }

	public float lengthSquared() { return x * x + y * y; }

	public float dot(Vector2 v) { return x * v.x + y * v.y; }

	public Vector2 normalize() {
		float len = lengthSquared();

		if (len > 0)
			return divide((float) Math.sqrt(len));
		return ZERO;
	}
}