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
     * @param send an output stream that will be sent from the client
     * @param receive an input stream that will be received by the server
     */
    public NetworkInterface(OutputStream send, InputStream receive) {
        this.send = send;
        this.receive = new BufferedReader(new InputStreamReader(receive));
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
            while ((message = this.receive.readLine()) != null && connected) {
                notifyReceipt(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    public void setConnected(boolean isConnected) {
        this.connected = isConnected;
    }

}
