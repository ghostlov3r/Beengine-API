package beengine.api.event;

import beengine.api.event.block.*;
import beengine.api.event.entity.*;
import beengine.api.event.inventory.*;
import beengine.api.event.player.*;
import beengine.api.event.plugin.*;
import beengine.api.event.world.*;
import beengine.api.plugin.Plugin;

/**
 * Classes implementing this interface can be registered to receive called Events.
 * @see EventManager#register(Plugin, EventListener)
 *
 * Listeners can have annotations.
 * @see Priority
 * @see AllowCancelled
 */
public interface EventListener {

	default void onAnvilDamage (AnvilDamageEvent event) {}
	
	default void onBlockBreak (BlockBreakEvent event) {}
	
	default void onBlockBurn (BlockBurnEvent event) {}
	
	default void onBlockForm (BlockFormEvent event) {}
	
	default void onBlockGrow (BlockGrowEvent event) {}
	
	default void onBlockPlace (BlockPlaceEvent event) {}
	
	default void onBlockSpread (BlockSpreadEvent event) {}
	
	default void onBlockTeleport (BlockTeleportEvent event) {}
	
	default void onBlockUpdate (BlockUpdateEvent event) {}
	
	default void onChunkLoad (ChunkLoadEvent event) {}
	
	default void onChunkAsyncLoad (ChunkAsyncLoadEvent event) {}
	
	default void onChunkPopulate (ChunkPopulateEvent event) {}
	
	default void onChunkUnload (ChunkUnloadEvent event) {}
	
	//default void onCommand (CommandEvent event) {}
	
	default void onCraftItem (CraftItemEvent event) {}
	
	default void onEntityBlockChange (EntityBlockChangeEvent event) {}
	
	default void onEntityCombust (EntityCombustEvent event) {}
	
	default void onEntityCombustByBlock (EntityCombustByBlockEvent event) {}
	
	default void onEntityCombustByEntity (EntityCombustByEntityEvent event) {}
	
	default void onEntityDamage (EntityDamageEvent event) {}
	
	default void onEntityDamageByBlock (EntityDamageByBlockEvent event) {}
	
	default void onEntityDamageByChildEntity (EntityDamageByChildEntityEvent event) {}
	
	default void onEntityDamageByEntity (EntityDamageByEntityEvent event) {}
	
	default void onEntityDeath (EntityDeathEvent event) {}
	
	default void onEntityDespawn (EntityDespawnEvent event) {}
	
	default void onEntityEffectAdd (EntityEffectAddEvent event) {}
	
	default void onEntityEffectRemove (EntityEffectRemoveEvent event) {}
	
	default void onEntityExplode (EntityExplodeEvent event) {}
	
	default void onEntityMotion (EntityMotionEvent event) {}
	
	default void onEntityMoveToUnloadedChunk (EntityMoveToUnloadedChunkEvent event) {}
	
	default void onEntityRegainHealth (EntityRegainHealthEvent event) {}
	
	default void onEntityShootBow (EntityShootBowEvent event) {}
	
	default void onEntitySpawn (EntitySpawnEvent event) {}
	
	default void onEntityTargetChange (EntityTargetChangeEvent event) {}
	
	default void onEntityTeleport (EntityTeleportEvent event) {}
	
	default void onExplosionPrime (ExplosionPrimeEvent event) {}
	
	default void onEntityVehicleEnter (EntityVehicleEnterEvent event) {}
	
	default void onEntityVehicleExit (EntityVehicleExitEvent event) {}
	
	//default void onFurnaceBurn (FurnaceBurnEvent event) {}
	
	//default void onFurnaceSmelt (FurnaceSmeltEvent event) {}
	
	default void onInventoryClose (InventoryCloseEvent event) {}
	
	default void onInventoryOpen (InventoryOpenEvent event) {}
	
	//default void onInventoryPickupArrow (InventoryPickupArrowEvent event) {}
	
	default void onInventoryPickupItem (InventoryPickupItemEvent event) {}
	
	default void onInventoryTransaction (InventoryTransactionEvent event) {}
	
	default void onItemDespawn (ItemDespawnEvent event) {}
	
	//default void onItemEnchant (EnchantItemEvent event) {}
	
	default void onItemSpawn (ItemSpawnEvent event) {}
	
	//default void onItemRepair (RepairItemEvent event) {}
	
	default void onLeavesDecay (LeavesDecayEvent event) {}
	
	//default void onLowMemory (LowMemoryEvent event) {}
	
	//default void onPacketReceive (PacketReceiveAsyncEvent event) {}
	
	default void onPlayerBedEnter (PlayerBedEnterEvent event) {}
	
	default void onPlayerBedLeave (PlayerBedLeaveEvent event) {}
	
	default void onPlayerBlockPick (PlayerBlockPickEvent event) {}
	
	default void onPlayerBucket (PlayerBucketEvent event) {}
	
	default void onPlayerBucketEmpty (PlayerBucketEmptyEvent event) {}
	
	default void onPlayerBucketFill (PlayerBucketFillEvent event) {}
	
	//default void onPlayerChangeSkin (PlayerChangeSkinEvent event) {}
	
	default void onPlayerChat (PlayerChatEvent event) {}
	
	default void onPlayerCommandPreprocess (PlayerCommandPreprocessEvent event) {}
	
	//default void onPlayerCreation (PlayerCreationEvent event) {}
	
	default void onPlayerDataDeletion(PlayerDataDeletionEvent event) {}
	
	default void onPlayerDataSave (PlayerDataSaveEvent event) {}
	
	default void onPlayerDeath (PlayerDeathEvent event) {}
	
	default void onPlayerDisplayNameChange (PlayerDisplayNameChangeEvent event) {}
	
	default void onPlayerDropItem (PlayerDropItemEvent event) {}
	
	//default void onPlayerDuplicateLogin (PlayerDuplicateLoginEvent event) {}
	
	//default void onPlayerEditBook (PlayerEditBookEvent event) {}
	
	default void onPlayerExhaust (PlayerExhaustEvent event) {}
	
	default void onPlayerExperienceChange (PlayerExperienceChangeEvent event) {}
	
	default void onPlayerFish (PlayerFishEvent event) {}
	
	default void onPlayerGameModeChange (PlayerGameModeChangeEvent event) {}
	
	default void onPlayerInteract (PlayerInteractEvent event) {}
	
	default void onPlayerInteractBlock (PlayerInteractBlockEvent event) {}
	
	default void onPlayerInteractEntity (PlayerInteractEntityEvent event) {}
	
	default void onPlayerItemConsume (PlayerItemConsumeEvent event) {}
	
	default void onPlayerItemHeld (PlayerItemHeldEvent event) {}
	
	default void onPlayerItemUse (PlayerItemUseEvent event) {}
	
	default void onPlayerJoin (PlayerJoinEvent event) {}
	
	default void onPlayerJump (PlayerJumpEvent event) {}
	
	default void onPlayerKick (PlayerKickEvent event) {}
	
	default void onPlayerLogin (PlayerLoginEvent event) {}
	
	default void onPlayerMove (PlayerMoveEvent event) {}
	
	//default void onPlayerPreLogin (PlayerPreLoginEvent event) {}
	
	//default void onPlayerServerSettingsRequest (PlayerServerSettingsRequestEvent event) {}
	
	default void onPlayerQuit (PlayerQuitEvent event) {}
	
	default void onPlayerRespawn (PlayerRespawnEvent event) {}
	
	default void onPlayerToggleFlight (PlayerToggleFlightEvent event) {}
	
	default void onPlayerToggleSneak (PlayerToggleSneakEvent event) {}
	
	default void onPlayerToggleSprint (PlayerToggleSprintEvent event) {}
	
	default void onPlayerTransfer (PlayerTransferEvent event) {}
	
	default void onPluginDisable (PluginDisableEvent event) {}
	
	default void onPluginEnable (PluginEnableEvent event) {}
	
	default void onProjectileHit (ProjectileHitEvent event) {}
	
	default void onProjectileHitBlock (ProjectileHitBlockEvent event) {}
	
	default void onProjectileHitEntity (ProjectileHitEntityEvent event) {}
	
	default void onProjectileLaunch (ProjectileLaunchEvent event) {}
	
	//default void onQueryRegenerate (QueryRegenerateEvent event) {}
	
	//default void onSignChange (SignChangeEvent event) {}
	
	default void onSpawnChange (SpawnChangeEvent event) {}
	
	default void onWorldInit (WorldInitEvent event) {}
	
	default void onWorldLoad (WorldLoadEvent event) {}
	
	default void onWorldSave (WorldSaveEvent event) {}
	
	default void onWorldUnload (WorldUnloadEvent event) {}
	
}
