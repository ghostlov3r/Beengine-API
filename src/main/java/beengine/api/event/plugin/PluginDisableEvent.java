package beengine.api.event.plugin;

import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.plugin.Plugin;

public class PluginDisableEvent extends PluginEvent {

	public PluginDisableEvent(Plugin plugin) {
		super(plugin);
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPluginDisable(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}