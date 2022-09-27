package beengine.api.math;

import lombok.Getter;
import lombok.experimental.Accessors;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

@Accessors(fluent = true)
public enum Facing {
	/* most significant 2 bits = axis, least significant bit = is positive direction */
	DOWN,
	UP,
	NORTH,
	SOUTH,
	WEST,
	EAST;

	@Getter private final int id = ordinal();
	
	// Не можем использовать так как этот енам еще не инизиализирован, а Axis запросит неготовые данные отсюда
	// @Getter private final Axis axis = Axis.fromDirection(this);
	// todo вообще это можно сделать без енамоебли а тубо с числами
	
	public Axis axis() {
		return Axis.fromDirection(this);
	}

	/** The opposite Facing of this Facing  */
	@Getter private Facing opposite;

	/** Whether the direction is facing the positive of its axis.  */
	@Getter private final boolean isPositive = (id & Axis.FLAG_AXIS_POSITIVE) == Axis.FLAG_AXIS_POSITIVE;

	private @Nullable
	Facing clockwiseY;
	private @Nullable
	Facing clockwiseZ;
	private @Nullable
	Facing clockwiseX;

	/**
	 * Rotates the given direction around the axis.
	 * @throws IllegalStateException if not possible to rotate direction around axis
	 */
	public Facing rotate(Axis axis, boolean clockwise) {
		return switch (axis) {
			case Y -> rotateY(clockwise);
			case Z -> rotateZ(clockwise);
			case X -> rotateX(clockwise);
		};
	}

	/** @throws IllegalStateException if not possible to rotate direction around Y */
	public Facing rotateY (boolean clockwise) {
		if (clockwiseY == null)
			throw new IllegalArgumentException("Cannot rotate direction "+name()+" around axis Y");
		return clockwise
			? clockwiseY
			: clockwiseY.opposite;
	}

	/** @throws IllegalStateException if not possible to rotate direction around Z */
	public Facing rotateZ (boolean clockwise) {
		if (clockwiseZ == null)
			throw new IllegalArgumentException("Cannot rotate direction "+name()+" around axis Z");
		return clockwise
			? clockwiseZ
			: clockwiseZ.opposite;
	}

	/** @throws IllegalStateException if not possible to rotate direction around X */
	public Facing rotateX (boolean clockwise) {
		if (clockwiseX == null)
			throw new IllegalArgumentException("Cannot rotate direction "+name()+" around axis X");
		return clockwise
			? clockwiseX
			: clockwiseX.opposite;
	}

	public static Facing fromValueWithFix (int value) {
		return VALUES[Math.abs(value % VALUES.length)];
	}
	
	public static final Facing[] VALUES = values();
	
	public static final List<Facing> VALUES_LIST = Arrays.asList(values());

	public static final Facing[] HORIZONTALS = new Facing[] {NORTH, SOUTH, WEST, EAST};

	public static final List<Facing> HORIZONTALS_LIST = Arrays.asList(HORIZONTALS);

	public static int oppositeRaw (int rawFace) {
		return rawFace ^ Axis.FLAG_AXIS_POSITIVE;
	}
	
	static {
		NORTH.clockwiseY = EAST;
		EAST.clockwiseY = SOUTH;
		SOUTH.clockwiseY = WEST;
		WEST.clockwiseY = NORTH;

		UP.clockwiseZ = EAST;
		EAST.clockwiseZ = DOWN;
		DOWN.clockwiseZ = WEST;
		WEST.clockwiseZ = UP;
		
		UP.clockwiseX = NORTH;
		NORTH.clockwiseX = DOWN;
		DOWN.clockwiseX = SOUTH;
		SOUTH.clockwiseX = UP;
		
		for (Facing dir : VALUES) {
			dir.opposite = VALUES[dir.id ^ Axis.FLAG_AXIS_POSITIVE];
		}
	}
}