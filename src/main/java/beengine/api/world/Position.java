package beengine.api.world;

import beengine.api.math.Vector3;

public class Position extends Vector3 {
    
    protected World world;
    
    public Position(Position pos) {
        this(pos, pos.world());
    }
    
    public Position(Vector3 vector, World world) {
        this(vector.x, vector.y, vector.z, world);
    }
    
    public Position(float x, float y, float z, World world) {
        super(x, y, z);
        this.world = world;
    }
    
    public World world () {
        return world;
    }
    
    public void setWorld (World world) {
        this.world = world;
    }
    
    /**
     * Checks if this object has a valid reference to a loaded world
     */
    public boolean isWorldValid() {
        World w = this.world;
        
        if (w == null || w.isClosed()) {
            this.world = null;
            return false;
        }
        return true;
    }
    
    public Position toPosition () {
        return new Position(x, y, z, world);
    }
    
    /*
    @Override
    public Position getSide (Facing side, int step) {
        if (!isWorldValid()) {
            throw new AssumptionFailedError("BlockPosition World is not valid");
        }
        return Position.from(super.getSide(side, step), world);
    }*/
    
    @Override
    public String toString() {
        return "BlockPosition(level=" + (isWorldValid() ? world.displayName() : "INVALID") +
                ",x="+x+",y="+y+",z="+z+")";
    }

    @Override
    public boolean equals (Vector3 v) {
        if (v instanceof Position bpos) {
            return super.equals(v) && bpos.world == this.world;
        }
        return super.equals(v);
    }

}