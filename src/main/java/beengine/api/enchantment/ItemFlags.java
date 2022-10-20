package beengine.api.enchantment;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemFlags {
	public int
	
	NONE			 = 0x0,
	ALL  			 = 0xffff,
	HEAD 			 = 0x1,
	TORSO 			 = 0x2,
	LEGS 			 = 0x4,
	FEET 			 = 0x8,
	SWORD 			 = 0x10,
	BOW 			 = 0x20,
	HOE 			 = 0x40,
	SHEARS			 = 0x80,
	FLINT_AND_STEEL  = 0x100,
	AXE			     = 0x200,
	PICKAXE		     = 0x400,
	SHOVEL			 = 0x800,
	FISHING_ROD	     = 0x1000,
	CARROT_STICK	 = 0x2000,
	ELYTRA			 = 0x4000,
	TRIDENT		     = 0x8000,
	WEARABLE = 0x16000,
	SHIELD = 0x32000,
	CROSSBOW = 0x64000,
	
	ARMOR     = HEAD | TORSO | LEGS | FEET,
	TOOL = HOE | SHEARS | FLINT_AND_STEEL,
	DIG  = AXE | PICKAXE | SHOVEL;
	
}
