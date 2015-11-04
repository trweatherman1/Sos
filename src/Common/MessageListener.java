package Common;

/**
 * This is an interface implemented by objects that receive messages.
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 1.0
 */
public interface MessageListener {
    public void messageReceived(String message, MessageSource source);
    public void sourceClosed(MessageSource source);
}
