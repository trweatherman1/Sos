package Server;

import Common.MessageListener;
import Common.MessageSource;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class implements the server side logic for the SOS server
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 02/11/2015
 */
public class MulituserSosServer implements MessageListener {

    /**
     * The port the server lives on
     */
    private int port;

    /**
     * This constructor initializes the port for the server to live on
     *
     * @param port the desired port for the server
     */
    public MulituserSosServer(int port) {
        this.port = port;
    }

    /**
     * This method listens for a connection to be made over a socket
     */
    public void listen() {

        /**A ServerSocket that will listen for someone trying to make a connection */
        ServerSocket serverSocket;
        try {
            /** Assigning the ServerSocket to a port */
            serverSocket = new ServerSocket(this.port);

            /**While the ServerSocket isn't closed */
            while (!serverSocket.isClosed()) {
                /**A new socket that connects the client */
                Socket connectedClient = serverSocket.accept();

            }
            //close the serverSocket
            serverSocket.close();
        } catch (IOException e) {

        }
    }

    @Override
    public void messageReceived(String message, MessageSource source) {

    }

    @Override
    public void sourceClosed(MessageSource source) {

    }
}
