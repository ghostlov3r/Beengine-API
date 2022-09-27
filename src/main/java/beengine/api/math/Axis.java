package beengine.api.math;

public enum Axis {
    
    Y,
    Z,
    X;
    
    public final int value = ordinal();
    
    final Facing[] around = new Facing[4];
    
    public static final int FLAG_AXIS_POSITIVE = 1;

    static final Axis[] VALUES = values();
    
    /**
     * Returns the axis of the given direction.
     */
    public static Axis fromDirection(Facing face) {
        // Shift off positive/negative bit
        return VALUES[face.id() >> 1];
    }
    
    static {
        for (Axis axis : VALUES) {
            int i = 0;
            for (Facing dir : Facing.VALUES) {
                if (dir.axis() != axis) axis.around[i++] = dir;
            }
        }
    }
}