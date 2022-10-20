package beengine.api.block.util;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public enum SkullType {
	
	SKELETON("Skeleton Skull"),
	WITHER_SKELETON("Wither Skeleton Skull"),
	ZOMBIE("Zombie Head"),
	PLAYER("Player Head"),
	CREEPER("Creeper Head"),
	DRAGON("Dragon Head");
	
	private final String displayName;

	SkullType (String displayName) {
		this.displayName = displayName;
	}
}