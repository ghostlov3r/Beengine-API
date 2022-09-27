package beengine.api.event;

import beengine.api.plugin.Plugin;

public interface EventManager {

	<T extends Event> void register(Plugin plugin, EventListener listener);

	void registerAll(Plugin plugin, Package source);

	void registerAll(Plugin plugin, String source);

	void unregister(Plugin plugin);

	void unregister(EventListener listener);

	void unregisterAll();

	void callEvent (Event event);
}
