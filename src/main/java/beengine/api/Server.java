package beengine.api;

import beengine.api.event.EventManager;
import beengine.api.plugin.PluginManager;
import beengine.api.scheduler.Scheduler;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
public class Server {

	@Getter
	public static EventManager eventManager;
	@Getter
	public static Scheduler scheduler;

	@Getter
	public static PluginManager pluginManager;

}
