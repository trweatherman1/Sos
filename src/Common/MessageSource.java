package Common;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the parent of all classes that can receive a message.
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 1.0
 */
public abstract class MessageSource {

    /**
     * This is the <code>MessageListeners</code> waiting for messages
     */
    private List<MessageListener> messageListeners;

    /**
     * This is the constructor that initializes the <code>ArrayList</code>
     */
    public MessageSource() {
        this.messageListeners = new ArrayList<>();
    }

    /**
     * This method adds a <code>MessageListener</code> the the listeners
     */
    public void addMessageListener(MessageListener listener) {
        messageListeners.add(listener);
    }

    /**
     * This method removes a specified listener from the ArrayList
     */
    public void removeMessageListener(MessageListener listener) {
        messageListeners.remove(listener);
    }

    /**
     * This class notifies each listener in the ArrayList that a message was
     * received
     *
     * @param message the message received
     */
    protected void notifyReceipt(String message) {
        for (MessageListener listener : new ArrayList<>
                (messageListeners)) {
            /* We wrap this in a try/catch block so that just in case one of
            our observers screws up, we don't want to stop notifying other
            observers.
             */
            try {
                listener.messageReceived(message, this);
            } catch (RuntimeException ex) {
                System.out.println("RuntimeException: " + ex.getMessage());
            }
        }
    }

    /**
     * This method closes the listeners that need to be closed
     */
    protected void closeMessageSource() {
        /* Here we need to iterate over a *copy* of our messageListeners list.
         The reason is because if the listener’s ’sourceClosed’ method
         removes that listener from this subject, we’d get a
         ConcurrentModificationException if we were iterating over the
         original list.
        */
        for (MessageListener listener : new
                ArrayList<>(messageListeners)) {
            try {
                listener.sourceClosed(this);
            } catch (RuntimeException ex) {
                /* Ignore any exceptions encountered when trying to close
                 a source. There’s a similar rationale here as we had
                 with ignoring exceptions when we tried to close streams.
                */
            }
        }
        messageListeners.clear();
    }
}
