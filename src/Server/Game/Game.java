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
    /**
     * Current player. From 0 to n
     */
    private int currentPlayer;

    /**
     * Hold the scores of all the players
     */
    private int[] scores;

    /**
     * The sos board
     */
    private SosBoard board;

    /**
     * Current allowed number of player
     */
    private int numPlayers;

    /**
     * The String array that the players usernames are stored in
     */
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
     */
    public void startGame(int size, String[] players) {
        this.numPlayers = players.length;
        this.players = players;
        board = new SosBoard(size);
        this.scores = new int[numPlayers];
    }

    /**
     * A method that will determine the movement of the player.
     * This will calculate the position on the board, the icon the user wants to use
     * and calulate the points the user will be getting (if any).
     *
     * @param row    the row that the move is to
     * @param col    the column that the move is on
     * @param icon   the character used
     * @param player the player that made the move
     * @return the number of points received for the turn
     */
    public int move(int row, int col, char icon, String player) {
        int message;
        if (player.equals(players[currentPlayer])) {
            if (board.setSpot(row, col, icon)) {
                scores[currentPlayer] += board.calculatePoints(row, col);
                changePlayer();
                message = board.isFull() ? ProgramConstants.FULLBOARD : ProgramConstants.VALIDMOVE;
            } else {
                message = ProgramConstants.INVALIDMOVE;
            }
        } else {
            message = ProgramConstants.WRONGPLAYER;
        }
        return message;
    }

    /**
     * Print the current score of all players out.
     * When the board is full it will print the high score of the players and determine a winner.
     *
     * @return return the player and the score of the player
     */
    public String displayScore() {
        String message = "";
        for (int current = 0; current < numPlayers; current++) {
            message += "Player " + players[current] + " has a score of " + scores[current] + "\n";
        }

        return message;
    }

    public String highScore() {
        String message = "";
        if (board.isFull()) {
            int highScore = 0;
            for (int i = 1; i < numPlayers; i++) {
                if (scores[i] > scores[highScore])
                    highScore = i;
                message = "Player " + players[i] + " wins with " + scores[highScore] + " points";
            }
        }
        return message;
    }

    /**
     * Determines which player's turn it is
     */
    public void changePlayer() {
        currentPlayer = (currentPlayer + 1) % numPlayers;
    }

    /**
     * A method that will print out the board we want
     *
     * @return text that will be designated for the board
     */
    public String toString() {
        String text = "";
        text += board;
        return text;
    }

    public String getCurrentPlayer() {
        return players[currentPlayer];
    }

}
