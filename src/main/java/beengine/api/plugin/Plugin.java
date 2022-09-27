package beengine.api.plugin;

import beengine.api.util.Version;
import dev.ghostlov3r.log.Logger;

import java.nio.file.Path;

public interface Plugin {

	String name ();

	Version version ();

	String description ();

	String author ();

	String website ();

	boolean isEnabled ();

	Logger logger ();

	Path dataPath ();

	default void onLoad () {};

	default void onEnable () {};

	default void onDisable () {};
}
