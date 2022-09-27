package beengine.api.event.entity;

import beengine.api.entity.Entity;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.math.Vector3;
import lombok.Getter;

@Getter
public class EntityMotionEvent extends EntityEvent<Entity> implements Cancellable {
	
	private final float motionX;
	private final float motionY;
	private final float motionZ;
	
	public EntityMotionEvent (Entity entity, Vector3 motion) {
		this(entity, motion.x, motion.y, motion.z);
	}
	
	public EntityMotionEvent (Entity entity, float motionX, float motionY, float motionZ) {
		super(entity);
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onEntityMotion(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}