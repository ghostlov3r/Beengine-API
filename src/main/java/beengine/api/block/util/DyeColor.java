package beengine.api.block.util;

import beengine.api.util.Color;
import beengine.api.util.Utils;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter

public enum DyeColor {

	WHITE		(0xf0, 0xf0, 0xf0),
	ORANGE		(0xf9, 0x80, 0x1d),
	MAGENTA		(0xc7, 0x4e, 0xbd),
	LIGHT_BLUE	(0x3a, 0xb3, 0xda),
	YELLOW		(0xfe, 0xd8, 0x3d),
	LIME		(0x80, 0xc7, 0x1f),
	PINK		(0xf3, 0x8b, 0xaa),
	GRAY		(0x47, 0x4f, 0x52),
	LIGHT_GRAY	(0x9d, 0x9d, 0x97),
	CYAN		(0x16, 0x9c, 0x9c),
	PURPLE		(0x89, 0x32, 0xb8),
	BLUE		(0x3c, 0x44, 0xaa),
	BROWN		(0x83, 0x54, 0x32),
	GREEN		(0x5e, 0x7c, 0x16),
	RED			(0xb0, 0x2e, 0x26),
	BLACK		(0x1d, 0x1d, 0x21);
	
	DyeColor (int r, int g, int b) {
		this.rgbValue = new Color(r, g, b);
		this.displayName = Utils.camelName(this);
	}

	Color rgbValue;
	String displayName;

}