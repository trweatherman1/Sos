package Client;

import Common.MessageListener;
import Common.MessageSource;
import Common.NetworkInterface;
import Common.ProgramConstants;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * This class is the client side of the SOS application
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 11/10/2015
 */
public class MultiuserSosClient extends MessageSource implements
        MessageListener {

    /**
     * This is the ip of the server
     */
    private String ip;

    /**
     * This is the port of the server
     */
    private int port;

    /**
     * This is the NetworkInterface for the client
     */
    private NetworkInterface networkInterface;

    /**
     * This is the boolean for if the client is still connected
     */
    private boolean active;

    /**
     * This is the default constructor with a specified IP address
     *
     * @param ip this is the ip address for the server
     */
    public MultiuserSosClient(String ip) {
        new MultiuserSosClient(ip, ProgramConstants.DEFAULTPORT);
    }

    /**
     * This is the constructor for the client that specifies the port and ip
     * address
     *
     * @param ip   the IP address of the server
     * @param port the port of the server
     */
    public MultiuserSosClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.active = true;
    }

    /**
     * This method will handle messages being sent to the implementer of this
     * interface
     *
     * @param message the message received
     * @param source  the source of the message
     */
    @Override
    public void messageReceived(String message, MessageSource source) {
        System.out.println(message);
    }

    /**
     * This method handles a message source closing and the clean up that is
     * needed
     *
     * @param source the source that is closing
     */
    @Override
    public void sourceClosed(MessageSource source) {
        this.active = false;
    }

    /**
     * This method sends out a message to its NetworkInterface between it and
     * the server
     *
     * @param message the message to send
     */
    public void sendMessage(String message) {
        networkInterface.sendMessage(message);
    }

    /**
     * This method starts up a socket and creates a NetworkInterface to act
     * between it and the server
     */
    public void playGame() {
        try {
            Socket socket = new Socket(InetAddress.getByName(this.ip),
                    this.port);
            this.networkInterface = new NetworkInterface(socket
                    .getOutputStream(), socket.getInputStream());
            networkInterface.addMessageListener(this);
            Thread clientThread = new Thread(this.networkInterface);
            clientThread.start();
            this.active = true;
        } catch (IOException e) {
            this.active = false;
        }
    }

    /**
     * This method returns the boolean active that is true while the client is connected to the
     * server
     *
     * @return the boolean value for active
     */
    public boolean isActive() {
        return active;
    }


    public void cleanUp() {
        this.active = false;
        networkInterface.sendMessage("/quit");
        networkInterface.setConnected(false);
    }

}
