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
 * @version 02/11/2015
 */
public class MultiuserSosClient extends MessageSource implements
        MessageListener {

    /** This is the ip of the server */
    private String ip;

    /** This is the port of the server */
    private int port;

    /** This is the NetworkInterface for the client */
    private NetworkInterface networkInterface;

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
     * @param ip the IP address of the server
     * @param port the port of the server
     */
    public MultiuserSosClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void messageReceived(String message, MessageSource source) {
        System.out.println(message);
    }

    @Override
    public void sourceClosed(MessageSource source) {

    }

    public void sendMessage(String message) {
        networkInterface.sendMessage(message);
    }

    public void playGame() {
        try {
            Socket socket = new Socket(InetAddress.getByName(this.ip), this.port);
            this.networkInterface = new NetworkInterface(socket
                    .getOutputStream(), socket.getInputStream());
            Thread clientThread = new Thread(this.networkInterface);
            clientThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
