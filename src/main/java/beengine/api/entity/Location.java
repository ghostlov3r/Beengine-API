package beengine.api.entity;

import beengine.api.math.Vector3;
import beengine.api.world.Position;
import beengine.api.world.World;

import static java.lang.Float.isInfinite;
import static java.lang.Float.isNaN;

public class Location extends Position {
	
	public float yaw;
	public float pitch;
	
	public Location(Vector3 pos, World world, float yaw, float pitch) {
		this(pos.x, pos.y, pos.z, world, yaw, pitch);
	}
	
	public Location(Vector3 pos, World world) {
		this(pos.x, pos.y, pos.z, world, 0.0f, 0.0f);
	}
	
	public Location(Position pos) {
		this(pos, 0, 0);
	}
	
	public Location(Position pos, float yaw, float pitch) {
		this(pos.x, pos.y, pos.z, pos.world());
	}
	
	public Location(float x, float y, float z, World world) {
		this(x, y, z, world, 0.0f, 0.0f);
	}
	
	public Location(Location location) {
		this(location.x, location.y, location.z, location.world, location.yaw, location.pitch);
	}
	
	public Location(float x, float y, float z, World world, float yaw, float pitch) {
		super(x, y, z, world);
		this.yaw = yaw;
		this.pitch = pitch;
		this.world = world;
	}
	
	public Location toLocation() {
		return new Location(x, y, z, world, yaw, pitch);
	}
	
	@Override
	public String toString() {
		return "Location(level=" + (isWorldValid() ? world.displayName() : "INVALID") +
			",x="+x+",y="+y+",z="+z+",yaw="+yaw+",pitch="+pitch+")";
	}
	
	@Override
	public boolean equals(Vector3 v) {
		if (v instanceof Location loc) {
			return super.equals(v)
								   && loc.yaw == this.yaw
								   && loc.pitch == this.pitch;
		}
		return super.equals(v);
	}
	
	public void setValuesFrom (Location location) {
		this.x = location.x;
		this.y = location.y;
		this.z = location.z;
		this.world = location.world;
		this.yaw = location.yaw;
		this.pitch = location.pitch;
	}
	
	public Location withVector (Vector3 vector) {
		return new Location(vector.x, vector.y, vector.z, world, yaw, pitch);
	}

	public Location withXYZ (float x, float y, float z) {
		return new Location(x, y, z, world, yaw, pitch);
	}

	@Override
	public Location withX (float x) {
		return new Location(x, this.y, this.z, world, yaw, pitch);
	}

	@Override
	public Location withY (float y) {
		return new Location(this.x, y, this.z, world, yaw, pitch);
	}

	@Override
	public Location withZ (float z) {
		return new Location(this.x, this.y, z, world, yaw, pitch);
	}

	public Location withYaw (float yaw) {
		return new Location(this.x, this.y, this.z, world, yaw, pitch);
	}

	public Location withPitch (float pitch) {
		return new Location(this.x, this.y, this.z, world, yaw, pitch);
	}

	public Location withRotation (float yaw, float pitch) {
		return new Location(this.x, this.y, this.z, world, yaw, pitch);
	}
	
	public Location withWorld (World world) {
		return new Location(this.x, this.y, this.z, world, yaw, pitch);
	}

	public void ensureNotNanAndNotInfinite () {
		if (isNaN(this.x) || isInfinite(this.x) ||
			isNaN(this.y) || isInfinite(this.y) ||
			isNaN(this.z) || isInfinite(this.z)
		) {
			throw new IllegalArgumentException("Illegal location coordinates (nan or infinite): "+toString());
		}
	}
}
