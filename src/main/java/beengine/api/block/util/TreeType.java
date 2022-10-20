package beengine.api.block.util;

import beengine.api.util.Utils;
import lombok.Getter;

public enum TreeType {

	OAK,
	SPRUCE,
	BIRCH,
	JUNGLE,
	ACACIA,
	DARK_OAK;
	
	@Getter private final String displayName = Utils.camelName(this, " ");
	
}