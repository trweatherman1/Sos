package Common;


import java.io.*;

/**
 * This class is responsible for sending/receiving messages over the network
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 02/11/2015
 */
public class NetworkInterface extends MessageSource implements Runnable {

    /**
     * This is the stream that sends messages out
     */
    private OutputStream send;

    /**
     * This is the Buffered reader that will receive messages
     */
    private BufferedReader receive;

    /**
     * This is a boolean for the Network Interface being connected
     */
    private boolean connected;

    /**
     * A constructor for the NetworkInterface Class takes in parameters send and receive
     *
     * @param send    an output stream that will be sent from the client
     * @param receive an input stream that will be received by the server
     */
    public NetworkInterface(OutputStream send, InputStream receive) {
        this.send = send;
        this.receive = new BufferedReader(new InputStreamReader(receive));
        connected = true;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        String message;
        try {
            while (connected && (message = this.receive.readLine()) != null) {
                notifyReceipt(message);
            }
        } catch (IOException e) {
            connected = false;
            closeMessageSource();
        }
    }


    /**
     * This method sends a message out via the Output Stream to the class
     * using this interface
     *
     * @param message the message to send
     */
    public void sendMessage(String message) {
        try {
            send.write((message + "\n").getBytes());
            send.flush();
        } catch (IOException e) {
            connected = false;
        }
    }

    /**
     * A method that takes in a boolean to see if a user is connected
     *
     * @param isConnected a boolean to see if the user is connected to the server
     */
    public void setConnected(boolean isConnected) {
        this.connected = isConnected;
    }


}
