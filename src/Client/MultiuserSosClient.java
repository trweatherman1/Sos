package Client;

import Common.MessageListener;
import Common.MessageSource;

/**
 * This class is the client side of the SOS application
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 02/11/2015
 */
public class MultiuserSosClient extends MessageSource implements
        MessageListener {

    @Override
    public void messageReceived(String message, MessageSource source) {

    }

    @Override
    public void sourceClosed(MessageSource source) {

    }
}
