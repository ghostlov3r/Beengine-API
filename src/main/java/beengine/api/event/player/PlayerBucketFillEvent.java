package beengine.api.event.player;

import beengine.api.block.Block;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.item.Item;
import beengine.api.math.Facing;
import beengine.api.player.Player;

public class PlayerBucketFillEvent extends PlayerBucketEvent {

	public PlayerBucketFillEvent(Player who, Block blockClicked, Facing blockFace, Item bucket, Item itemInHand) {
		super(who, blockClicked, blockFace, bucket, itemInHand);
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		if (depth > 0) {
			super.handle(handler, depth - 1);
			return;
		}
		handler.onPlayerBucketFill(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}