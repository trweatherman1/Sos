package Server;

import Common.MessageListener;
import Common.MessageSource;
import Common.NetworkInterface;
import Server.Commands.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements the server side logic for the SOS server
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 02/11/2015
 */
public class MultiuserSosServer implements MessageListener {

    /** the list of connected players */
    private Map<String, NetworkInterface> connectedPlayers;

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
        this.potentialPlayers = new ArrayList<NetworkInterface>();
        this.connectedPlayers = new HashMap<String, NetworkInterface>();
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
                potentialPlayers.add(clientInterface);
                clientInterface.addMessageListener(this);
                clientThread.start();
            }
            //close the serverSocket
            serverSocket.close();
        } catch (IOException e) {

        }
    }

    /**
     * This method parses the message to determine what command was received
     * and passes it onto the parseCommand method
     *
     * @param message the message received
     * @param source the source of the message
     */
    @Override
    public void messageReceived(String message, MessageSource source) {
        parseCommand(message,source);
        for(NetworkInterface player: potentialPlayers) {
            player.sendMessage("Server: " + message);
        }
    }

    /**
     * This method is called when a source closes, and the server needs to
     * clean it up
     *
     * @param source the source that is closing
     */
    @Override
    public void sourceClosed(MessageSource source) {

    }

    /**
     * This method determines what command is being called in a message
     *
     * @param message the message that may be a command
     * @param source the source of the message
     */
    public void parseCommand(String message, MessageSource source) {
        AbstractCommand command;
        String[] parsedCommand = message.split("\\w+");
        switch(parsedCommand[0].toLowerCase()) {
            case "/connect":
                command = new ConnectCommand(parsedCommand);
                break;
            case "/play":
                command = new PlayCommand(parsedCommand);
                break;
            case "/move":
                command = new MoveCommand(parsedCommand);
                break;
            case "/quit":
                command = new QuitCommand(parsedCommand);
                break;
            default:
                command = new InvalidCommand(parsedCommand);
        }
        command.execute(this,source);
    }

    /**
     * This method adds a client to the connectedPlayers HashMap if the name
     * does not already exist, and the MessageSource is not already in the list
     *
     * @param username the desired username for the player
     * @param source the source of the connection
     *
     * @return a boolean that is true if the client was added
     */
    public boolean addConnectedClient(String username, MessageSource source) {
        boolean added = false;
        if(!connectedPlayers.containsKey(username) && !connectedPlayers
                .containsValue(source)) {
            this.connectedPlayers.put(username, (NetworkInterface) source);
            added = true;
        }
        return added;
    }

    /**
     * This method returns the number of connected players
     *
     * @return the number of connected players
     */
    public int numConnectedClients() {
        return connectedPlayers.size();
    }
}
