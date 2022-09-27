package beengine.api.event.world;

import beengine.api.event.Event;
import beengine.api.world.World;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Accessors(fluent = true)
@Getter
public abstract class WorldEvent extends Event {

	protected final World world;

}