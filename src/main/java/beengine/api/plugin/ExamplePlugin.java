package beengine.api.plugin;

import beengine.api.Server;
import beengine.api.command.Command;
import beengine.api.command.Default;
import beengine.api.command.Variants;
import beengine.api.event.EventListener;
import beengine.api.event.world.WorldLoadEvent;
import beengine.api.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PluginHeader(
		name = "MyFirstPlugin",
		version = 1,
		author = "ghostlov3r",
		website = "colaria.ru")
public class ExamplePlugin extends AbstractPlugin implements EventListener {

	@ConfigParameter
	int param = 0;

	@ConfigParameter
	Map<String, List<String>> kits = new HashMap<>();

	@Command(name = {"kit create", "kit add"},
			permission = "command.kit.create",
			permissionMessage = "Сначала купи админку")
	public void createKit (
			Player player,
			String kitName,
			@Default("diamond_sword apple") List<String> items
	) {
		for (String item : items) {
			// валидация итемов
		}
		kits.put(kitName, items);
	}

	@Command(name = "kit",
			permission = "command.kit.get",
			permissionMessage = "Сначала купи випку")
	public void getKit (
			Player player,
			@Variants({"start", "vip"}) String kitName
	) {
		List<String> items = kits.get(kitName);
		for (String item : items) {
			// ...
		}
	}

	@Override
	public void onEnable() {
		logger().info("Plugin %s enabled with parameter %s", name(), param);

		Server.scheduler().repeat(5, () -> {

		});
	}

	@Override
	public void onDisable() {
		logger().info("Plugin %s disabled", name());
	}

	@Override
	public void onWorldLoad(WorldLoadEvent event) {

	}
}
