package beengine.api.event.plugin;

import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.plugin.Plugin;

public class PluginEnableEvent extends PluginEvent {

	public PluginEnableEvent(Plugin plugin) {
		super(plugin);
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPluginEnable(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}