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

    /** This is the stream that sends messages out */
    private OutputStream send;

    /** This is the Buffered reader that will receive messages */
    private BufferedReader receive;

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
            while((message = this.receive.readLine()) != null) {
                notifyReceipt(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendMessage(String message)  {
        try {
            send.write((message+"\n").getBytes());
            send.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
