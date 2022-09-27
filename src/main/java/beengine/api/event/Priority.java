package beengine.api.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})

public @interface Priority {
	
	/**
	 * The priority at which an event listener will receive events.
	 */
	EventPriority value() default EventPriority.NORMAL;
}
