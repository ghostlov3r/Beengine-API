package beengine.api.command;

public @interface Command {

	String[] name();
	String description() default "";
	String permission() default "";
	String permissionMessage() default "";
}
