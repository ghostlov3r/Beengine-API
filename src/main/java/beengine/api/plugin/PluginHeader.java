package beengine.api.plugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PluginHeader {
	String name();
	int version();
	int patch() default 0;
	int[] api() default {};
	String author() default "unknown";
	String website() default "unknown";
}
