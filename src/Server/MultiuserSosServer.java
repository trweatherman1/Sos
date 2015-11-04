package Server;

import Common.MessageListener;
import Common.MessageSource;
import Common.NetworkInterface;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the server side logic for the SOS server
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 02/11/2015
 */
public class MultiuserSosServer implements MessageListener {

    /** the list of connected players */
    private List<NetworkInterface> players;

    /** the list of potential players */
    private List<NetworkInterface> potentialPlayers;

    /**
     * The port the server lives on
     */
    private int port;

    /**
     * This constructor initializes the port for the server to live on
     *
     * @param port the desired port for the server
     */
    public MultiuserSosServer(int port) {
        this.players = new ArrayList<NetworkInterface>();
        this.port = port;
    }

    /**
     * This method listens for a connection to be made over a socket
     */
    public void listen() {

        /*A ServerSocket that will listen for someone trying
          to make a connection
        */
        ServerSocket serverSocket;
        try {
            /* Assigning the ServerSocket to a port */
            serverSocket = new ServerSocket(this.port);

            /* While the ServerSocket isn't closed */
            while (!serverSocket.isClosed()) {
                /* A new socket that connects the client */
                Socket connectedClient = serverSocket.accept();
                NetworkInterface clientInterface = new NetworkInterface
                        (connectedClient.getOutputStream(),connectedClient
                                .getInputStream());
                Thread clientThread = new Thread(clientInterface);
                players.add(clientInterface);
                clientThread.start();
            }
            //close the serverSocket
            serverSocket.close();
        } catch (IOException e) {

        }
    }

    @Override
    public void messageReceived(String message, MessageSource source) {
        for(NetworkInterface player: players) {
            player.sendMessage(message);
            System.out.println("Sending Players " + message);
        }
    }

    @Override
    public void sourceClosed(MessageSource source) {

    }
}
