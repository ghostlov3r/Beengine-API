package beengine.api.event.entity;

import beengine.api.entity.Entity;
import beengine.api.event.Event;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public abstract class EntityEvent<T extends Entity> extends Event {

	protected T entity;

	public EntityEvent (T entity) {
		this.entity = entity;
	}
}