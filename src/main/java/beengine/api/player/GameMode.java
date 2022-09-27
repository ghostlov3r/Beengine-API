package beengine.api.player;

import beengine.api.util.Utils;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum GameMode { // todo fix
	
	SURVIVAL  ( "gameMode.survival",  "s"		  ),
	CREATIVE  ( "gameMode.creative",  "c"		  ),
	ADVENTURE ( "gameMode.adventure", "a"		  ),
	SPECTATOR ( "gameMode.spectator", "v", "view");
	
	private final String translationKey;
	private final String[] aliases;
	private final String englishName;
	
	GameMode(String translationKey, String ...aliases) {
		this.translationKey = translationKey;
		this.aliases = aliases;
		this.englishName = Utils.capitalize(name().toLowerCase());
	}
	
	public String getTranslationKey () {
		return /*"%" + */this.translationKey;
	}
	
	protected static Map<String, GameMode> aliasMap = new HashMap<>();
	static {
		for (GameMode mode : values()) {
			for (String alias : mode.aliases) {
				aliasMap.put(alias, mode);
			}
			aliasMap.put(String.valueOf(mode.ordinal()), mode);
		}
	}
	
	public static GameMode fromString (String str) {
		GameMode mode = aliasMap.get(str);
		if (mode == null) throw new IllegalArgumentException("No GameMode enum member matches alias " + str);
		return mode;
	}
	
	/**
	 * @throws IllegalArgumentException if Illegal number
	 */
	public static GameMode fromNumber (int n) {
		if (Utils.isValidIndex(n, values())) return values()[n];
		throw new IllegalArgumentException("No GameMode enum member matches number " + n);
	}
	
	//TODO: ability sets per gamemode
}