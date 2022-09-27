package beengine.api.event.world;

import beengine.api.entity.Entity;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.tile.Tile;
import beengine.api.world.Chunk;
import beengine.api.world.World;

import java.util.Collection;

public class ChunkAsyncLoadEvent extends ChunkEvent {
	
	Collection<Entity> entities;
	Collection<Tile> tiles;
	private final boolean isNewChunk;
	
	public ChunkAsyncLoadEvent(World world, Chunk chunk, Collection<Entity> entities, Collection<Tile> tiles, boolean isNewChunk) {
		super(world, chunk);
		this.entities = entities;
		this.tiles = tiles;
		this.isNewChunk = isNewChunk;
	}
	
	public Collection<Entity> entities() {
		return entities;
	}
	
	public Collection<Tile> tiles() {
		return tiles;
	}
	
	public boolean isNewChunk() {
		return isNewChunk;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onChunkAsyncLoad(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}
