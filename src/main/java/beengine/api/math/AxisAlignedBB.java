package beengine.api.math;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;

import javax.annotation.Nullable;

import static beengine.api.util.Utils.inRange;
import static beengine.api.util.Utils.insideRange;


@ToString
@NoArgsConstructor
public final class AxisAlignedBB implements Cloneable {
	
	public static final AxisAlignedBB[] EMPTY_ARRAY = new AxisAlignedBB[0];

	public float minX, minY, minZ;
	public float maxX, maxY, maxZ;
	
	/**
	 * @return A 1x1x1 bounding box starting at grid position 0,0,0.
	 */
	public static AxisAlignedBB one() {
		AxisAlignedBB bb = new AxisAlignedBB();
		bb.maxX = 1;
		bb.maxY = 1;
		bb.maxZ = 1;
		return bb;
	}

	public AxisAlignedBB (float minX, float minY, float minZ,
						  float maxX, float maxY, float maxZ) {
		
		setBounds(minX, minY, minZ, maxX, maxY, maxZ);
	}
	
	public void setBounds (float minX, float minY, float minZ,
						   float maxX, float maxY, float maxZ) {
		
		if (minX > maxX) throw new IllegalArgumentException("minX "+minX+ "is larger than maxX "+maxX);
		if (minY > maxY) throw new IllegalArgumentException("minY "+minY+ "is larger than maxY "+maxY);
		if (minZ > maxZ) throw new IllegalArgumentException("minZ "+minZ+ "is larger than maxZ "+maxZ);
		
		this.minX = minX;
		this.minY = minY;
		this.minZ = minZ;
		
		this.maxX = maxX;
		this.maxY = maxY;
		this.maxZ = maxZ;
	}
	
	@Override
	@SneakyThrows
	public AxisAlignedBB clone() {
		return (AxisAlignedBB) super.clone();
	}

	public float xLength () { return maxX - minX; }

	public float yLength () { return maxY - minY; }

	public float zLength () { return maxZ - minZ; }
	
	/**
	 * The mean average of the AABB's X, Y and Z lengths.
	 */
	public float averageEdgeLength() {
		return (maxX - minX + maxY - minY + maxZ - minZ) / 3;
	}
	
	/**
	 * The interior volume of the AABB.
	 */
	public float volume () {
		return  (maxX - minX) * (maxY - minY) * (maxZ - minZ);
	}

	/**
	 * Returns a new AxisAlignedBB extended by the specified X, Y and Z.
	 * If each of X, Y and Z are positive, the relevant max bound will be increased.
	 * If negative, the relevant min bound will be decreased.
	 */
	public AxisAlignedBB addCoord(float x, float y, float z) {
		float minX = this.minX;
		float minY = this.minY;
		float minZ = this.minZ;
		float maxX = this.maxX;
		float maxY = this.maxY;
		float maxZ = this.maxZ;
		
		if(x < 0){
			minX += x;
		}else if(x > 0){
			maxX += x;
		}
		
		if(y < 0){
			minY += y;
		}else if(y > 0){
			maxY += y;
		}
		
		if(z < 0){
			minZ += z;
		}else if(z > 0){
			maxZ += z;
		}
		
		return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
	}
	
	/**
	 * Returns an expanded clone of this AxisAlignedBB.
	 */
	public AxisAlignedBB expandedCopy (float x, float y, float z) {
		return clone().expand(x, y, z);
	}

	/**
	 * Outsets the bounds of this AxisAlignedBB by the specified X, Y and Z, then returns itself
	 */
	public AxisAlignedBB expand(float x, float y, float z) {
		minX -= x;
		minY -= y;
		minZ -= z;
		maxX += x;
		maxY += y;
		maxZ += z;
		return this;
	}

	/**
	 * Returns an offset clone of this AxisAlignedBB.
	 */
	public AxisAlignedBB offsetCopy (float x, float y, float z) {
		return clone().offset(x, y, z);
	}

	/**
	 * Shifts this AxisAlignedBB by the given X, Y and Z, then returns itself
	 */
	public AxisAlignedBB offset (float x, float y, float z) {
		minX += x;
		minY += y;
		minZ += z;
		maxX += x;
		maxY += y;
		maxZ += z;
		return this;
	}
	
	/**
	 * Shifts this AxisAlignedBB by the given X, then returns itself
	 */
	public AxisAlignedBB offsetX (float x) {
		minX += x;
		maxX += x;
		return this;
	}
	
	/**
	 * Shifts this AxisAlignedBB by the given Y, then returns itself
	 */
	public AxisAlignedBB offsetY (float y) {
		minY += y;
		maxY += y;
		return this;
	}
	
	/**
	 * Shifts this AxisAlignedBB by the given Z, then returns itself
	 */
	public AxisAlignedBB offsetZ (float z) {
		minZ += z;
		maxZ += z;
		return this;
	}
	
	/**
	 * Returns a contracted clone of this AxisAlignedBB.
	 */
	public AxisAlignedBB contractedCopy(float x, float y, float z) {
		return clone().contract(x, y, z);
	}

	/**
	 * Insets the bounds of this AxisAlignedBB
	 * by the specified X, Y and Z, then returns itself
	 */
	public AxisAlignedBB contract(float x, float y, float z) {
		minX += x;
		minY += y;
		minZ += z;
		maxX -= x;
		maxY -= y;
		maxZ -= z;
		return this;
	}

	/**
	 * Returns an extended clone of this bounding box.
	 * @see AxisAlignedBB#extend
	 */
	public AxisAlignedBB extendedCopy (Facing face, float distance) {
		return clone().extend(face, distance);
	}

	/**
	 * Extends the AABB in the given direction, then returns itself
	 * @param distance Negative values pull the face in, positive values push out.
	 */
	public AxisAlignedBB extend(Facing face, float distance) {
		switch (face) {
			case DOWN  -> minY -= distance;
			case UP    -> maxY += distance;
			case NORTH -> minZ -= distance;
			case SOUTH -> maxZ += distance;
			case WEST  -> minX -= distance;
			case EAST  -> maxX += distance;
		}
		return this;
	}

	/**
	 * Returns a trimmed clone of this bounding box.
	 * @see AxisAlignedBB#trim
	 */
	public AxisAlignedBB trimmedCopy(Facing face, float distance) {
		return extendedCopy(face, -distance);
	}

	/**
	 * Inverse of extend().
	 * @see AxisAlignedBB#extend
	 * @param distance Positive values pull the face in, negative values push out.
	 */
	public AxisAlignedBB trim(Facing face, float distance) {
		return extend(face, -distance);
	}

	/**
	 * Returns a stretched copy of this bounding box.
	 * @see AxisAlignedBB#stretch
	 */
	public AxisAlignedBB stretchedCopy(Axis axis, float distance) {
		return clone().stretch(axis, distance);
	}

	/**
	 * Increases the dimension of the AABB along the given axis, then returns itself
	 * @param axis one of the Axis.* static finalants
	 * @param distance Negative values reduce width, positive values increase width.
	 */
	public AxisAlignedBB stretch(Axis axis, float distance) {
		switch (axis) {
			case Y -> { minY -= distance;
					    maxY += distance;
			}
			case Z -> { minZ -= distance;
					    maxZ += distance;
			}
			case X -> { minX -= distance;
						maxX += distance;
			}
		}
		return this;
	}

	/**
	 * Returns a squashed copy of this bounding box.
	 * @see AxisAlignedBB#squash
	 */
	public AxisAlignedBB squashedCopy (Axis axis, float distance) {
		return stretchedCopy(axis, -distance);
	}

	/**
	 * Reduces the dimension of the AABB on the given axis, then returns itself. Inverse of stretch().
	 * @see AxisAlignedBB#stretch
	 */
	public AxisAlignedBB squash (Axis axis, float distance) {
		return stretch(axis, -distance);
	}
	
	/**
	 * Returns whether any part of the specified AABB is inside (intersects with) this one.
	 */
	public boolean intersectsWith(AxisAlignedBB bb) {
		return intersectsWith(bb, 0.00001f);
	}
	
	/**
	 * Returns whether any part of the specified AABB is inside (intersects with) this one.
	 */
	public boolean intersectsWith (AxisAlignedBB bb, float epsilon) {
		if (bb.maxX - minX > epsilon && maxX - bb.minX > epsilon) {
			if (bb.maxY - minY > epsilon && maxY - bb.minY > epsilon) {
				return bb.maxZ - minZ > epsilon && maxZ - bb.minZ > epsilon;
			}
		}
		return false;
	}

	/**
	 * Returns whether the specified vector is within the bounds of this AABB on all axes.
	 */
	public boolean isVectorInside (Vector3 vector) {
		return insideRange(vector.x, minX, maxX) &&
			   insideRange(vector.y, minY, maxY) &&
			   insideRange(vector.z, minZ, maxZ);
	}
	
	public boolean isCube() {
		return isCube(0.000001f);
	}
	
	public boolean isCube (float epsilon) {
		float yLen = yLength();
		return Math.abs( xLength() - yLen ) < epsilon &&
			   Math.abs( yLen - zLength() ) < epsilon;
	}

	/**
	 * Returns whether the specified vector is within the Y and Z bounds of this AABB.
	 */
	public boolean isVectorInYZ (Vector3 vector) {
		return inRange(vector.y, minY, maxY) &&
			   inRange(vector.z, minZ, maxZ);
	}
	
	/**
	 * Returns whether the specified vector is within the X and Z bounds of this AABB.
	 */
	public boolean isVectorInXZ (Vector3 vector) {
		return inRange(vector.x, minX, maxX) &&
			   inRange(vector.z, minZ, maxZ);
	}
	
	/**
	 * Returns whether the specified vector is within the X and Y bounds of this AABB.
	 */
	public boolean isVectorInXY (Vector3 vector) {
		return inRange(vector.x, minX, maxX) &&
			   inRange(vector.y, minY, maxY);
	}
	
	public float calculateXOffset(AxisAlignedBB bb, float x) {
		if (bb.maxY <= minY || bb.minY >= maxY) return x;
		if (bb.maxZ <= minZ || bb.minZ >= maxZ) return x;
		
		if (x > 0 && bb.maxX <= minX) {
			float x1 = minX - bb.maxX;
			if (x1 < x)
				return x1;
		} else if (x < 0 && bb.minX >= maxX) {
			float x2 = maxX - bb.minX;
			if (x2 > x)
				return x2;
		}
		return x;
	}
	
	public float calculateYOffset(AxisAlignedBB bb, float y) {
		if (bb.maxX <= minX || bb.minX >= maxX) return y;
		if (bb.maxZ <= minZ || bb.minZ >= maxZ) return y;
		
		if (y > 0 && bb.maxY <= minY) {
			float y1 = minY - bb.maxY;
			if (y1 < y)
				return y1;
		} else if (y < 0 && bb.minY >= maxY) {
			float y2 = maxY - bb.minY;
			if (y2 > y)
				return y2;
		}
		return y;
	}
	
	public float calculateZOffset (AxisAlignedBB bb, float z) {
		if (bb.maxX <= minX || bb.minX >= maxX) return z;
		if (bb.maxY <= minY || bb.minY >= maxY) return z;
		
		if (z > 0 && bb.maxZ <= minZ) {
			float z1 = minZ - bb.maxZ;
			if (z1 < z)
				return z1;
		} else if (z < 0 && bb.minZ >= maxZ) {
			float z2 = maxZ - bb.minZ;
			if (z2 > z)
				return z2;
		}
		return z;
	}
	
	/**
	 * Performs a ray-trace and calculates the point on the AABB's edge nearest the start position that the ray-trace
	 * collided with. Returns a RayTraceResult with colliding vector closest to the start position.
	 * Returns null if no colliding point was found.
	 */
	/*public @Nullable
	RayTraceResult calculateIntercept(Vector3 pos1, Vector3 pos2) {
		Vector3 v1, v2, v3, v4, v5, v6;
		
		v1 = pos1.getIntermediateWithXValue(pos2, minX);
		v2 = pos1.getIntermediateWithXValue(pos2, maxX);
		v3 = pos1.getIntermediateWithYValue(pos2, minY);
		v4 = pos1.getIntermediateWithYValue(pos2, maxY);
		v5 = pos1.getIntermediateWithZValue(pos2, minZ);
		v6 = pos1.getIntermediateWithZValue(pos2, maxZ);
		
		if (v1 != null && !isVectorInYZ(v1)) v1 = null;
		if (v2 != null && !isVectorInYZ(v2)) v2 = null;
		if (v3 != null && !isVectorInXZ(v3)) v3 = null;
		if (v4 != null && !isVectorInXZ(v4)) v4 = null;
		if (v5 != null && !isVectorInXY(v5)) v5 = null;
		if (v6 != null && !isVectorInXY(v6)) v6 = null;
		
		Vector3 vector = null;
		float distance = Float.MAX_VALUE;

		for (Vector3 v : new Vector3[] {v1, v2, v3, v4, v5, v6}) {
			if (v != null) {
				float d = pos1.distanceSquared(v);
				if (d < distance) {
					vector = v;
					distance = d;
				}
			}
		}
		
		if (vector == null)
			return null;
		
		Facing face = null;
		
		if (vector == v1) face = WEST;  else
		if (vector == v2) face = EAST;  else
		if (vector == v3) face = DOWN;  else
		if (vector == v4) face = UP;    else
		if (vector == v5) face = NORTH; else
		if (vector == v6) face = SOUTH;
		
		assert face != null;
		
		return new RayTraceResult(this, face, vector);
	}

	public void forEach (IntTriConsumer action) {
		int minX = FMath.floor(this.minX);
		int minY = FMath.floor(this.minY);
		int minZ = FMath.floor(this.minZ);
		
		int maxX = FMath.floor(this.maxX);
		int maxY = FMath.floor(this.maxY);
		int maxZ = FMath.floor(this.maxZ);
		
		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				for (int z = minZ; z <= maxZ; z++) {
					action.accept(x, y, z);
				}
			}
		}
	}*/
}