package beengine.api.math;

import beengine.api.util.function.IntTriConsumer;
import beengine.api.util.function.IntTriPredicate;
import beengine.api.world.Chunk;
import beengine.api.world.World;
import lombok.ToString;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static beengine.api.math.Facing.*;

@ToString
public class Vector3 {
	
	public float x;
	public float y;
	public float z;
	
	public Vector3() {}
	
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3(double x, double y, double z) {
		this.x = (float) x;
		this.y = (float) y;
		this.z = (float) z;
	}

	public int floorX() { return FMath.floor(x); }
	public int floorY() { return FMath.floor(y); }
	public int floorZ() { return FMath.floor(z); }
	
	public int chunkX() { return floorX() >> 4; }
	public int chunkZ() { return floorZ() >> 4; }
	
	public Vector3 toVector () {
		return new Vector3(x, y, z);
	}

	public long blockHash () {
		return World.blockHash(floorX(), floorY(), floorZ());
	}

	public long chunkHash () {
		return Chunk.hash(chunkX(), chunkZ());
	}
	
	/* ------------------------------------------------------- */
	
	public Vector3 setXYZ (double x, double y, double z) {
		return setXYZ((float) x, (float) y, (float) z);
	}
	
	public Vector3 setXYZFrom (Vector3 vector) {
		return setXYZ(vector.x, vector.y, vector.z);
	}
	
	public Vector3 setXYZ (float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;

		return this;
	}
	
	public Vector3 round (int precision) {
		return new Vector3(FMath.round(x, precision), FMath.round(y, precision), FMath.round(z, precision));
	}
	
	public Vector3 floor () {
		return new Vector3(floorX(), floorY(), floorZ());
	}
	
	public Vector3 add (float x, float y, float z) {
		return new Vector3(this.x + x, this.y + y, this.z + z);
	}
	
	public Vector3 add (Vector3 vector) {
		return new Vector3(this.x + vector.x, this.y + vector.y, this.z + vector.z);
	}
	
	public Vector3 subtract (float x, float y, float z) {
		return new Vector3(this.x - x, this.y - y, this.z - z);
	}
	
	public Vector3 subtract (Vector3 vector) {
		return new Vector3(this.x - vector.x, this.y - vector.y, this.z - vector.z);
	}
	
	public Vector3 multiply (float multiplier) {
		return new Vector3(this.x * multiplier, this.y * multiplier, this.z * multiplier);
	}
	
	public Vector3 divide (float divider) {
		return new Vector3(this.x / divider, this.y / divider, this.z / divider);
	}

	public Vector3 abs() {
		return new Vector3(
			Math.abs(this.x),
			Math.abs(this.y),
			Math.abs(this.z)
		);
	}
	
	public float distanceSquared (Vector3 pos) {
		return distanceSquared(pos.x, pos.y, pos.z);
	}
	
	public float distanceSquared (float x, float y, float z) {
		return distanceSquared(this.x, this.y, this.z, x, y, z);
	}
	
	public static float distanceSquared (float fromX, float fromY, float fromZ,
										 float toX, float toY, float toZ) {
		float x = fromX - toX;
		float y = fromY - toY;
		float z = fromZ - toZ;
		
		return x * x + y * y + z * z;
	}
	
	public float distance (Vector3 pos) {
		return (float) Math.sqrt(this.distanceSquared(pos));
	}
	
	public float distance (float xx, float yy, float zz) {
		return (float) Math.sqrt(this.distanceSquared(xx, yy, zz));
	}
	
	public static float distance (float fromX, float fromY, float fromZ,
								  float toX, float toY, float toZ) {
		
		return (float) Math.sqrt(distanceSquared(fromX, fromY, fromZ, toX, toY, toZ));
	}

	public float maxPlainDistance(Vector3 vector) {
		return this.maxPlainDistance(vector.x, vector.z);
	}

	public float maxPlainDistance(Vector2 vector) {
		return this.maxPlainDistance(vector.x, vector.y);
	}

	public float maxPlainDistance(float x) {
		return maxPlainDistance(x, 0.0f);
	}

	public float maxPlainDistance(float x, float z) {
		return Math.max(Math.abs(this.x - x), Math.abs(this.z - z));
	}

	public float length() {
		return (float) Math.sqrt(lengthSquared());
	}

	public float lengthSquared() {
		return x * x + y * y + z * z;
	}
	
	public Vector3 normalize() {
		float length = lengthSquared();
		return length > 0 ? divide((float) Math.sqrt(length)) : new Vector3();
	}

	public float dot (Vector3 v) {
		return x * v.x + y * v.y + z * v.z;
	}

	public Vector3 cross(Vector3 v) {
		return new Vector3(
				y * v.z - z * v.y,
				z * v.x - x * v.z,
				x * v.y - y * v.x
		);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}
	
	@Override
	public boolean equals (Object o) {
		return o instanceof Vector3 v && equals(v);
	}
	
	public boolean equals (Vector3 v) {
		return x == v.x && y == v.y && z == v.z;
	}

	/**
	 * Returns a new vector with x value equal to the second parameter, along the line between this vector and the
	 * passed in vector, or null if not possible.
	 */
	public @Nullable
	Vector3 getIntermediateWithXValue(Vector3 v, float x) {
		float xDiff = v.x - this.x;
		if (xDiff * xDiff < 0.0000001) {
			return null;
		}
		float f = (x - this.x) / xDiff;
		if (f < 0 || f > 1)
			return null;
		return new Vector3(x, y + (v.y - y) * f, z + (v.z - z) * f);
	}

	/**
	 * Returns a new vector with y value equal to the second parameter, along the line between this vector and the
	 * passed in vector, or null if not possible.
	 */
	public @Nullable
	Vector3 getIntermediateWithYValue(Vector3 v, float y) {
		float yDiff = v.y - this.y;
		if (yDiff * yDiff < 0.0000001) {
			return null;
		}
		float f = (y - this.y) / yDiff;
		if (f < 0 || f > 1)
			return null;
		return new Vector3(x + (v.x - x) * f, y, z + (v.z - z) * f);
	}

	/**
	 * Returns a new vector with z value equal to the second parameter, along the line between this vector and the
	 * passed in vector, or null if not possible.
	 */
	public @Nullable
	Vector3 getIntermediateWithZValue(Vector3 v, float z) {
		float zDiff = v.z - this.z;
		if (zDiff * zDiff < 0.0000001) { // todo CHECK JAVA FLOAT
			return null;
		}
		float f = (z - this.z) / zDiff;
		if (f < 0 || f > 1)
			return null;
		return new Vector3(x + (v.x - x) * f, y + (v.y - y) * f, z);
	}
	
	public Vector3 withX (float x) {
		return new Vector3(x, this.y, this.z);
	}
	
	public Vector3 withY (float y) {
		return new Vector3(this.x, y, this.z);
	}
	
	public Vector3 withZ (float z) {
		return new Vector3(this.x, this.y, z);
	}
	
	public Vector3 addX (float x) {
		return new Vector3(this.x + x, this.y, this.z);
	}
	
	public Vector3 addY (float y) {
		return new Vector3(this.x, this.y + y, this.z);
	}
	
	public Vector3 addZ (float z) {
		return new Vector3(this.x, this.y, this.z + z);
	}
	
	public Vector3 subtractX (float x) {
		return new Vector3(this.x - x, this.y, this.z);
	}
	
	public Vector3 subtractY (float y) {
		return new Vector3(this.x, this.y - y, this.z);
	}
	
	public Vector3 subtractZ (float z) {
		return new Vector3(this.x, this.y, this.z - z);
	}
	
	public Vector3 getSide(Facing side) {
		return getSide(side, 1);
	}
	
	public Vector3 getSide (Facing side, int step) {
		return switch (side) {
			case DOWN  -> new Vector3(x, y - step, z);
			case UP    -> new Vector3(x, y + step, z);
			case NORTH -> new Vector3(x, y, z - step);
			case SOUTH -> new Vector3(x, y, z + step);
			case WEST  -> new Vector3(x - step, y, z);
			case EAST  -> new Vector3(x + step, y, z);
		};
	}
	
	public Vector3 down (int step) { return getSide(DOWN,  step); }
	public Vector3 up   (int step) { return getSide(UP,    step); }
	public Vector3 north(int step) { return getSide(NORTH, step); }
	public Vector3 south(int step) { return getSide(SOUTH, step); }
	public Vector3 west (int step) { return getSide(WEST,  step); }
	public Vector3 east (int step) { return getSide(EAST,  step); }
	
	public Vector3 down () { return getSide(DOWN,  1); }
	public Vector3 up   () { return getSide(UP,    1); }
	public Vector3 north() { return getSide(NORTH, 1); }
	public Vector3 south() { return getSide(SOUTH, 1); }
	public Vector3 west () { return getSide(WEST,  1); }
	public Vector3 east () { return getSide(EAST,  1); }
	
	public Iterable<Map.Entry<Facing, Vector3>> sidesAroundAxis (Axis axis) {
		return prepareSides(axis.around, 1);
	}
	
	/**
	 * Yields vectors stepped out from this one in directions except those on the given axis.
	 *
	 * @param axis Facing directions on this axis will be excluded
	 */
	public Iterable<Map.Entry<Facing, Vector3>> sidesAroundAxis (Axis axis, int step) {
		return prepareSides(axis.around, step);
	}
	
	public Iterable<Map.Entry<Facing, Vector3>> sides () {
		return prepareSides(Facing.VALUES, 1);
	}
	
	/**
	 * Yields vectors stepped out from this one in all directions.
	 *
	 * @param step Distance in each direction to shift the vector
	 */
	public Iterable<Map.Entry<Facing, Vector3>> sides (int step) {
		return prepareSides(Facing.VALUES, step);
	}
	
	private Iterable<Map.Entry<Facing, Vector3>> prepareSides (Facing[] directions, int step) {
		return new Iterable<>() {
			@Override
			public Iterator<Map.Entry<Facing, Vector3>> iterator() {
				return new Iterator<>() {
					// final Pair<Facing, Vector3> pair = new ReferenceReferenceMutablePair<>(null, null);
					int i = 0;
					final int limit = directions.length;
					
					@Override
					public boolean hasNext() {
						return i < limit;
					}
					
					@Override
					public Map.Entry<Facing, Vector3> next() {
						if (i >= limit)
							throw new NoSuchElementException();
						Facing d = directions[i++];
						
						return Map.entry(d, getSide(d, step));
						
						// return pair.key(d).value(getSide(d, step));
					}
				};
			}
			
			@Override
			public void forEach(Consumer<? super Map.Entry<Facing, Vector3>> action) {
				// final Pair<Facing, Vector3> pair = new ReferenceReferenceMutablePair<>(null, null);
				for (Facing d : directions) {
					action.accept(Map.entry(d, getSide(d, step)));
					//action.accept(pair.key(d).value(getSide(d, step)));
				}
			}
		};
	}
	
	public void forEachSide (BiConsumer<Facing, Vector3> action) {
		sides().forEach(entry -> action.accept(entry.getKey(), entry.getValue()));
	}
	
	/**
	 * Same as sides() but returns a pre-populated array instead of lazy sequence.
	 */
	public Vector3[] sidesArray() {
		return sidesArray(1);
	}
	
	/**
	 * Same as sides() but returns a pre-populated array instead of lazy sequence.
	 */
	public Vector3[] sidesArray (int step) {
		Facing[] directions = Facing.VALUES;
		Vector3[] sides = new Vector3[directions.length];
		for (int i = 0; i < directions.length; i++) {
			sides[i] = getSide(directions[i], step);
		}
		return sides;
	}
	
	/**
	 * Returns a new Vector3 taking the maximum of each component in the input vectors.
	 */
	public static Vector3 maxComponents(Vector3 vector, Vector3... vectors) {
		float x = vector.x;
		float y = vector.y;
		float z = vector.z;
		
		for (Vector3 vec : vectors) {
			x = Math.max(x, vec.x);
			y = Math.max(y, vec.y);
			z = Math.max(z, vec.z);
		}
		return new Vector3(x, y, z);
	}
	
	/**
	 * Returns a new Vector3 taking the minimum of each component in the input vectors.
	 */
	public Vector3 minComponents(Vector3 vector, Vector3... vectors) {
		float x = vector.x;
		float y = vector.y;
		float z = vector.z;
		
		for (Vector3 vec : vectors) {
			x = Math.min(x, vec.x);
			y = Math.min(y, vec.y);
			z = Math.min(z, vec.z);
		}
		return new Vector3(x, y, z);
	}
	
	public static Vector3 sum (List<Vector3> vectors) {
		if (vectors.isEmpty())
			return new Vector3();
		
		int x = 0, y = 0, z = 0;
		
		for (Vector3 vector : vectors) {
			x += vector.x;
			y += vector.y;
			z += vector.z;
		}
		return new Vector3(x, y, z);
	}
	
	public static void forEachSide (int x, int y, int z, IntTriConsumer action) {
		forEachSide(x, y, z, action, 1);
	}
	
	public static void forEachSide (int x, int y, int z, IntTriConsumer action, int step) {
		action.accept(x + step, y, z);
		action.accept(x - step, y, z);
		action.accept(x, y + step, z);
		action.accept(x, y - step, z);
		action.accept(x, y, z + step);
		action.accept(x, y, z - step);
	}
	
	public static void forEachSide (int x, int y, int z, IntTriPredicate action) {
		forEachSide(x, y, z, action, 1);
	}
	
	public static void forEachSide (int x, int y, int z, IntTriPredicate action, int step) {
		if (!action.accept(x + step, y, z))    return;
		if (!action.accept(x - step, y, z))    return;
		if (!action.accept(x, y + step, z)) return;
		if (!action.accept(x, y - step, z)) return;
		if (!action.accept(x, y, z + step))   return;
		action.accept(x, y, z - step);
	}
}