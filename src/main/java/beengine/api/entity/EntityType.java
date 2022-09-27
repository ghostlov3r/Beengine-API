package beengine.api.entity;

import beengine.api.util.Utils;

public enum EntityType {

	AGENT 					  (EntityIdsNormal.AGENT),
	AREA_EFFECT_CLOUD 		  (EntityIdsNormal.AREA_EFFECT_CLOUD),
	ARMOR_STAND 			  (EntityIdsNormal.ARMOR_STAND),
	ARROW 					  (EntityIdsNormal.ARROW),
	BALLOON 				  (EntityIdsNormal.BALLOON),
	BAT 					  (EntityIdsNormal.BAT),
	BEE 					  (EntityIdsNormal.BEE),
	BLAZE 					  (EntityIdsNormal.BLAZE),
	BOAT 					  (EntityIdsNormal.BOAT),
	CAT 					  (EntityIdsNormal.CAT),
	CAVE_SPIDER 			  (EntityIdsNormal.CAVE_SPIDER),
	CHEST_MINECART 		  (EntityIdsNormal.CHEST_MINECART),
	CHICKEN 				  (EntityIdsNormal.CHICKEN),
	COD 					  (EntityIdsNormal.COD),
	COMMAND_BLOCK_MINECART   (EntityIdsNormal.COMMAND_BLOCK_MINECART),
	COW  					  (EntityIdsNormal.COW),
	CREEPER 				  (EntityIdsNormal.CREEPER),
	DOLPHIN 				  (EntityIdsNormal.DOLPHIN),
	DONKEY 				  (EntityIdsNormal.DONKEY),
	DRAGON_FIREBALL 		  (EntityIdsNormal.DRAGON_FIREBALL),
	DROWNED  				  (EntityIdsNormal.DROWNED),
	EGG  					  (EntityIdsNormal.EGG),
	ELDER_GUARDIAN  		  (EntityIdsNormal.ELDER_GUARDIAN),
	ELDER_GUARDIAN_GHOST  	  (EntityIdsNormal.ELDER_GUARDIAN_GHOST),
	ENDER_CRYSTAL 			  (EntityIdsNormal.ENDER_CRYSTAL),
	ENDER_DRAGON 			  (EntityIdsNormal.ENDER_DRAGON),
	ENDER_PEARL 			  (EntityIdsNormal.ENDER_PEARL),
	ENDERMAN 				  (EntityIdsNormal.ENDERMAN),
	ENDERMITE 				  (EntityIdsNormal.ENDERMITE),
	EVOCATION_FANG 		  (EntityIdsNormal.EVOCATION_FANG),
	EVOCATION_ILLAGER 		  (EntityIdsNormal.EVOCATION_ILLAGER),
	EYE_OF_ENDER_SIGNAL 	  (EntityIdsNormal.EYE_OF_ENDER_SIGNAL),
	FALLING_BLOCK 			  (EntityIdsNormal.FALLING_BLOCK),
	FIREBALL 				  (EntityIdsNormal.FIREBALL),
	FIREWORKS_ROCKET 		  (EntityIdsNormal.FIREWORKS_ROCKET),
	FISHING_HOOK 			  (EntityIdsNormal.FISHING_HOOK),
	FOX 					  (EntityIdsNormal.FOX),
	GHAST 					  (EntityIdsNormal.GHAST),
	GUARDIAN 				  (EntityIdsNormal.GUARDIAN),
	HOGLIN 				  (EntityIdsNormal.HOGLIN),
	HOPPER_MINECART 		  (EntityIdsNormal.HOPPER_MINECART),
	HORSE 					  (EntityIdsNormal.HORSE),
	HUSK 					  (EntityIdsNormal.HUSK),
	ICE_BOMB 				  (EntityIdsNormal.ICE_BOMB),
	IRON_GOLEM 			  (EntityIdsNormal.IRON_GOLEM),
	ITEM 					  (EntityIdsNormal.ITEM),
	LEASH_KNOT 			  (EntityIdsNormal.LEASH_KNOT),
	LIGHTNING_BOLT 		  (EntityIdsNormal.LIGHTNING_BOLT),
	LINGERING_POTION 		  (EntityIdsNormal.LINGERING_POTION),
	LLAMA 					  (EntityIdsNormal.LLAMA),
	LLAMA_SPIT 			  (EntityIdsNormal.LLAMA_SPIT),
	MAGMA_CUBE 			  (EntityIdsNormal.MAGMA_CUBE),
	MINECART 				  (EntityIdsNormal.MINECART),
	MOOSHROOM 				  (EntityIdsNormal.MOOSHROOM),
	MULE 					  (EntityIdsNormal.MULE),
	NPC 					  (EntityIdsNormal.NPC),
	OCELOT 				  (EntityIdsNormal.OCELOT),
	PAINTING 				  (EntityIdsNormal.PAINTING), //these aren't accurate, but it doesn't matter since they aren't used (vanilla PC does something similar)
	PANDA 					  (EntityIdsNormal.PANDA),
	PARROT 				  (EntityIdsNormal.PARROT),
	PHANTOM 				  (EntityIdsNormal.PHANTOM),
	PIG 					  (EntityIdsNormal.PIG),
	PIGLIN 				  (EntityIdsNormal.PIGLIN),
	PILLAGER 				  (EntityIdsNormal.PILLAGER),
	PLAYER 				  (EntityIdsNormal.PLAYER),
	POLAR_BEAR 			  (EntityIdsNormal.POLAR_BEAR),
	PUFFERFISH 			  (EntityIdsNormal.PUFFERFISH),
	RABBIT 				  (EntityIdsNormal.RABBIT),
	RAVAGER 				  (EntityIdsNormal.RAVAGER),
	SALMON 				  (EntityIdsNormal.SALMON),
	SHEEP 					  (EntityIdsNormal.SHEEP),
	SHULKER 				  (EntityIdsNormal.SHULKER),
	SHULKER_BULLET 		  (EntityIdsNormal.SHULKER_BULLET),
	SILVERFISH 			  (EntityIdsNormal.SILVERFISH),
	SKELETON 				  (EntityIdsNormal.SKELETON),
	SKELETON_HORSE 		  (EntityIdsNormal.SKELETON_HORSE),
	SLIME 					  (EntityIdsNormal.SLIME), // todo ???
	SMALL_FIREBALL 		  (EntityIdsNormal.SMALL_FIREBALL),
	SNOW_GOLEM 			  (EntityIdsNormal.SNOW_GOLEM),
	SNOWBALL 				  (EntityIdsNormal.SNOWBALL),
	SPIDER 				  (EntityIdsNormal.SPIDER),
	SPLASH_POTION 			  (EntityIdsNormal.SPLASH_POTION),
	SQUID 					  (EntityIdsNormal.SQUID),
	STRAY 					  (EntityIdsNormal.STRAY),
	STRIDER 				  (EntityIdsNormal.STRIDER),
	THROWN_TRIDENT 		  (EntityIdsNormal.THROWN_TRIDENT),
	TNT 					  (EntityIdsNormal.TNT),
	TNT_MINECART 			  (EntityIdsNormal.TNT_MINECART),
	TRIPOD_CAMERA 			  (EntityIdsNormal.TRIPOD_CAMERA),
	TROPICALFISH 			  (EntityIdsNormal.TROPICALFISH),
	TURTLE 				  (EntityIdsNormal.TURTLE),
	VEX 					  (EntityIdsNormal.VEX),
	VILLAGER 				  (EntityIdsNormal.VILLAGER),
	VILLAGER_V2 			  (EntityIdsNormal.VILLAGER_V2),
	VINDICATOR 			  (EntityIdsNormal.VINDICATOR),
	WANDERING_TRADER 		  (EntityIdsNormal.WANDERING_TRADER),
	WITCH 					  (EntityIdsNormal.WITCH),
	WITHER 				  (EntityIdsNormal.WITHER),
	WITHER_SKELETON 		  (EntityIdsNormal.WITHER_SKELETON),
	WITHER_SKULL 			  (EntityIdsNormal.WITHER_SKULL),
	WITHER_SKULL_DANGEROUS   (EntityIdsNormal.WITHER_SKULL_DANGEROUS),
	WOLF 					  (EntityIdsNormal.WOLF),
	XP_BOTTLE 				  (EntityIdsNormal.XP_BOTTLE),
	XP_ORB 				  (EntityIdsNormal.XP_ORB),
	ZOGLIN 				  (EntityIdsNormal.ZOGLIN),
	ZOMBIE 				  (EntityIdsNormal.ZOMBIE),
	ZOMBIE_HORSE 			  (EntityIdsNormal.ZOMBIE_HORSE),
	ZOMBIE_PIGMAN 			  (EntityIdsNormal.ZOMBIE_PIGMAN),
	ZOMBIE_VILLAGER 		  (EntityIdsNormal.ZOMBIE_VILLAGER),
	ZOMBIE_VILLAGER_V2 	  (EntityIdsNormal.ZOMBIE_VILLAGER_V2);
	
	String networkId;
	String entityName;
	
	EntityType (String id) {
		networkId = id;
		entityName = Utils.camelName(this, " ");
	}
	
	public String entityName() {
		return entityName;
	}
	
	public String networkId() {
		return networkId;
	}
}