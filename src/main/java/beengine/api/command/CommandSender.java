package beengine.api.command;

public interface CommandSender /*extends Permissible*/ {

	// TODO uncomment
	/*Language language ();

	void sendMessage(Translation message);*/

	void sendMessage(String message);
	
	String name();

	/**
	 * The line height of the command-sender's screen. Used for determining sizes for command output pagination
	 * such as in the /help command.
	 */
	int screenLineHeight ();
	
	void setScreenLineHeight (int value);
}