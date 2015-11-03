package Common;


import Client.MultiuserSosClient;
import Server.MultiuserSosServer;

import java.io.*;
import java.net.Socket;

/**
 * This class is responsible for sending/receiving messages over the network
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 02/11/2015
 */
public class NetworkInterface extends MessageSource implements Runnable {

    /** This is the socket for the client */
    private Socket clientSocket;

    /** This is the username for the client */
    private String name;

    /** This is the BufferedReader to read from the client */
    private BufferedReader fromClient;

    /** This is the OutputStream to write to the client*/
    private OutputStream toClient;

    public NetworkInterface(Socket socket, MultiuserSosServer server) {
        this.clientSocket = socket;
        try {
            this.fromClient = new BufferedReader(new
                    InputStreamReader(clientSocket.getInputStream()));
            this.toClient = clientSocket.getOutputStream();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.name = "";
        this.addMessageListener(server);
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
            String msg;
        try {
            while((msg = fromClient.readLine()) != null) {
                this.name = msg;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void tell(String message) {
        try {
            toClient.write(message.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
