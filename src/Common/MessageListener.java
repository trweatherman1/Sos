package Common;

/**
 * This is an interface implemented by objects that receive messages.
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 1.0
 */
public interface MessageListener {
    /**
     * This method will handle messages being sent to the implementer of this
     * interface
     *
     * @param message the message received
     * @param source the source of the message
     */
    public void messageReceived(String message, MessageSource source);

    /**
     * This method handles a message source closing and the clean up that is
     * needed
     *
     * @param source the source that is closing
     */
    public void sourceClosed(MessageSource source);
}
