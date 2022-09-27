package beengine.api.event.plugin;

import beengine.api.event.Event;
import beengine.api.plugin.Plugin;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class PluginEvent extends Event {

	private final Plugin plugin;

}