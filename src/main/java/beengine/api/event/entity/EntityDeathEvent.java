package beengine.api.event.entity;

import beengine.api.entity.EntityLiving;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.item.Item;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Setter
public class EntityDeathEvent extends EntityEvent<EntityLiving> {

    private Collection<Item> drops;
    private int xpDropAmount;
    
    public EntityDeathEvent(EntityLiving entity) {
        this(entity, new ArrayList<>());
    }

    public EntityDeathEvent(EntityLiving entity, Collection<Item> drops) {
        this(entity, drops, 0);
    }

    public EntityDeathEvent(EntityLiving entity, Collection<Item> drops, int xpDropAmount) {
        super(entity);
        this.drops = drops;
        this.xpDropAmount = xpDropAmount;
    }
    
    @Override
    public void handle(EventListener handler, int depth) {
        handler.onEntityDeath(this);
    }
    
    public Collection<Item> drops() {
        return this.drops;
    }
    
    public int xpDropAmount() {
        return this.xpDropAmount;
    }
    
    private static RegisteredListener[] handlers = new RegisteredListener[0];
    @Override
    public RegisteredListener[] handlers() {
        return handlers;
    }
}