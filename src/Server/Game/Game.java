package Server.Game;

import Common.ProgramConstants;

/**
 * Models a game of SOS
 *
 * @author Trent Weatherman
 * @author Daniel Powell
 * @version 11/10/2015
 */

public class Game {
    /** Current player. From 0 to n*/
    private int currentPlayer;

    /**Hold the scores of all the players */
    private int[] scores;

    /** The sos board*/
    private SosBoard board;

    /** Current allowed number of player */
    private int numPlayers;

    /** The String array that the players usernames are stored in*/
    private String[] players;

    /**
     * Initialize everything for a new game of SOS. Player 0 always goes first.
     */
    public Game() {
        this.currentPlayer = 0;
        scores = new int[numPlayers];
    }

    /**
     * This method creates a new SOS board of the specified size and then prints the board to the
     * screen
     *
     * @param size size of one side of the board
     * @return the String representation of the board
     */
    public void startGame(int size, String[] players) {
        this.numPlayers = players.length;
        this.players = players;
        board = new SosBoard(size);
    }

    public int move(int row, int col, char icon,String player) {
        int message;
        if(player.equals(players[currentPlayer])) {
            if(board.setSpot(row,col,icon)) {
                message = ProgramConstants.VALIDMOVE;
                changePlayer();
            } else {
                message = ProgramConstants.INVALIDMOVE;
            }
        } else {
            message = ProgramConstants.WRONGPLAYER;
        }
        return message;
    }

    /**
     * Print the current score of all players to standard out.
     */
    public String displayScore() {
        String message = "";
        for (int current = 0; current < numPlayers; current++) {
            scores[current] = current;
           message += "Player " + current + " has a score of " + scores[current] + "\n";
        }

        int highScore = 0;
        for (int i = 1; i < numPlayers; i++) {
            if (scores[i] > scores[highScore])
                highScore = i;
        }
        return message;
    }

    /**
     * Determines whos turn it is
     */
    public void changePlayer() {
        currentPlayer = (currentPlayer + 1) % numPlayers;
    }

    /**
     * A method that will print out the board we want
     * @return text that will be designated for the board
     */
    public String toString(){
        String text = "";
        text += board;
        return text;
    }

}
