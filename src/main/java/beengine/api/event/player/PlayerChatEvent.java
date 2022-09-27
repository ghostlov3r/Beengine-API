package beengine.api.event.player;

import beengine.api.command.CommandSender;
import beengine.api.event.Cancellable;
import beengine.api.event.EventListener;
import beengine.api.event.RegisteredListener;
import beengine.api.player.Player;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Collection;

/**
 * Called when a player chats something
 */
@Accessors(fluent = true)
@Getter
public class PlayerChatEvent extends PlayerEvent implements Cancellable {
	
	public static final String NAME = "$NAME";
	public static final String MESSAGE = "$MESSAGE";
	
	public static final String DEFAULT_FORMAT = NAME+": "+MESSAGE;
	
	protected String message;
	protected Collection<CommandSender> recipients;
	protected String format;
	
	public PlayerChatEvent(
		Player player,
		String message,
		Collection<CommandSender> recipients
	) {
		this(player, message, recipients, DEFAULT_FORMAT);
	}
	
	public PlayerChatEvent(
		Player player,
		String message,
		Collection<CommandSender> recipients,
		String format
	) {
		super(player);
		this.message = message;
		this.format = format;
		this.recipients = recipients;
	}
	
	/**
	 * It is able to change the player that is sending the message
	 */
	public void setPlayer (Player player) {
		this.player = player;
	}
	
	public void setRecipients(Collection<CommandSender> recipients) {
		this.recipients = recipients;
	}
	
	public void setFormat(String format) {
		this.format = format;
	}
	
	@Override
	public void handle(EventListener handler, int depth) {
		handler.onPlayerChat(this);
	}
	
	private static RegisteredListener[] handlers = new RegisteredListener[0];
	@Override
	public RegisteredListener[] handlers() {
		return handlers;
	}
}