package beengine.api.plugin;

import lombok.experimental.Delegate;

public abstract class AbstractPlugin implements Plugin {

	@Delegate(types = Plugin.class)
	private Plugin delegate;
}
