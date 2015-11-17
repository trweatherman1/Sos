package Server;

import Common.MessageListener;
import Common.MessageSource;
import Common.NetworkInterface;
import Server.Commands.*;
import Server.Game.Game;
import com.sun.corba.se.spi.activation.Server;

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
 * @version 11/10/2015
 */
public class MultiuserSosServer implements MessageListener {

    /**
     * the list of connected players
     */
    private Map<String, NetworkInterface> connectedPlayers;

    /**
     * the list of potential players
     */
    private List<NetworkInterface> potentialPlayers;

    /**
     * This is the current game for the server
     */
    private Game currentGame;

    /**
     * The port the server lives on
     */
    private int port;

    /**size of the board*/
    private int size;

    /** This boolean determines if the game is going on or not */
    private boolean inGame;

    /**
     * This constructor initializes the port for the server to live on
     *
     * @param port the desired port for the server
     */
    public MultiuserSosServer(int port, int size) {
        this.potentialPlayers = new ArrayList<>();
        this.connectedPlayers = new HashMap<>();
        this.port = port;
        this.currentGame = new Game();
        this.size = size;
        this.inGame = false;
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
                        (connectedClient.getOutputStream(), connectedClient
                                .getInputStream());
                Thread clientThread = new Thread(clientInterface);
                potentialPlayers.add(clientInterface);
                clientInterface.addMessageListener(this);
                clientThread.start();
            }
            //close the serverSocket
            serverSocket.close();
        } catch (IOException ignore) {

        }
    }

    /**
     * This method parses the message to determine what command was received
     * and passes it onto the parseCommand method
     *
     * @param message the message received
     * @param source  the source of the message
     */
    @Override
    public void messageReceived(String message, MessageSource source) {
        parseCommand(message, source);
    }

    /**
     * This method is called when a source closes, and the server needs to
     * clean it up
     *
     * @param source the source that is closing
     */
    @Override
    public void sourceClosed(MessageSource source) {
        source.removeMessageListener(this);
        this.connectedPlayers.remove(source);
        this.potentialPlayers.remove(source);
        this.inGame = false;
        broadcast(getPlayerName(source) + " has disconnected");
    }

    /**
     * This method determines what command is being called in a message
     *
     * @param message the message that may be a command
     * @param source  the source of the message
     */
    public void parseCommand(String message, MessageSource source) {
        AbstractCommand command;
        String[] parsedCommand = message.split("\\s+");
        if (parsedCommand.length > 0) {
            switch (parsedCommand[0]) {
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
            if(parsedCommand.length >= command.getNumArgs()) {
                command.execute(this, source);
            } else {
                privateMessage(parsedCommand[0] + "requires " + command.getNumArgs() + "arguments",
                        source);
            }
        } else {
            privateMessage("Invalid Command", source);
        }
    }

    /**
     * This method adds a client to the connectedPlayers HashMap if the name
     * does not already exist, and the MessageSource is not already in the list
     *
     * @param username the desired username for the player
     * @param source   the source of the connection
     */
    public void addConnectedClient(String username, MessageSource source) {
        String message = "";
        if (connectedPlayers.containsKey(username)) {
            message += username + " is already in use";
        }
        if (connectedPlayers.containsValue(source)) {
            message += " This source is already in use";
        }
        if (!connectedPlayers.containsKey(username) && !connectedPlayers
                .containsValue(source) && source instanceof NetworkInterface) {
            this.connectedPlayers.put(username, (NetworkInterface) source);
            broadcast(username + " has connected");
        } else {
            privateMessage(message, source);
        }
    }

    /**
     * This method returns the number of connected players
     *
     * @return the number of connected players
     */
    public int numConnectedClients() {
        return connectedPlayers.size();
    }

    /**
     * This method broadcasts to all connectedPlayers, ones that have called
     * the connect command successfully
     *
     * @param message the message to broadcast
     */
    public void broadcast(String message) {
        for (NetworkInterface player : connectedPlayers.values()) {
            player.sendMessage("Server: " + message);
        }
        System.out.println("Server: " + message);
    }

    /**
     * This method sends a private message to the source in the parameters
     *
     * @param message the message to send privately
     * @param source  the source to send the message to
     */
    public void privateMessage(String message, MessageSource source) {
        ((NetworkInterface) source).sendMessage("Server: " + message);
    }

    /**
     * This method sets up the SOS game
     */
    public void setupGame() {
        currentGame.startGame(size, connectedPlayers.keySet().toArray(new String[connectedPlayers
                .size()]));
        displayBoard();
        displayCurrentPlayer();
        this.inGame = true;
    }

    /**
     * A method that calls the move method from the Game class, this will check if the position if the user wants to
     * place an icon at is invalid, valid or if it's not the user's turn.
     * Displays the score and the board
     *
     * @param row
     * @param col
     * @param icon
     * @param player
     */
    public void move(int row, int col, char icon, String player) {
        int returnCode = currentGame.move(row, col, icon, player);
        if (returnCode == 1) {
            broadcast("Invalid Move");
        }
        else if(returnCode == 2) {
            privateMessage("Not your turn",connectedPlayers.get(player));
        } else {
            showScore();
            displayBoard();
            if(returnCode == 3) {
                this.inGame = false;
                broadcast("GAME OVER");
            } else {
                displayCurrentPlayer();
            }
        }
    }

    public void showScore() {
        broadcast("\n" + currentGame.displayScore());
    }

    public void displayBoard() {
        broadcast("\n" + currentGame.toString());
    }

    public String getPlayerName(MessageSource messageSource) {
        String playerName = "Player Not Found";
        for(String name : connectedPlayers.keySet().toArray(new String[connectedPlayers.size()])) {
            if(connectedPlayers.get(name).equals(messageSource)) {
                playerName = name;
            }
        }
        return playerName;
    }

    public void displayCurrentPlayer() {
        broadcast(currentGame.getCurrentPlayer() + "'s turn.");
    }

    public boolean isInGame() {
        return inGame;
    }
}
