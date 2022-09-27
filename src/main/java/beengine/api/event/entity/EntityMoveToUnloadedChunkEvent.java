package beengine.api.event.entity;

import beengine.api.entity.Entity;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;

public class EntityMoveToUnloadedChunkEvent extends EntityEvent<Entity> {
	
	int oldX;
	int oldZ;
	Solution solution = Solution.FORCE_LOAD;
	
	public enum Solution {
		FORCE_LOAD,
		DESPAWN
	}
	
	public EntityMoveToUnloadedChunkEvent(Entity entity, int oldX, int oldZ) {
		super(entity);
	}
	
	public int oldChunkX () {
		return oldX;
	}
	
	public int oldChunkZ () {
		return oldZ;
	}
	
	public void setSolution(Solution solution) {
		this.solution = solution;
	}
	
	public Solution solution() {
		return solution;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onEntityMoveToUnloadedChunk(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}
