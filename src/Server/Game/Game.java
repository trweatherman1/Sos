package Server.Game;

import java.util.Scanner;

/**
 * Models a game of SOS
 * @author Trent Weatherman
 * @author Daniel Powell
 * @version 2015
 */

public class Game {
    /** Current player. From 0 to n */
    private int player;

    /** Hold the scores of all the players */
    private int[] scores;

    /** The sos board */
    private SosBoard board;

    /** For input */
    private Scanner scanIn;

    /** Current allowed number of players */
    private static final int  PLAYERS = 2;

    /**
     * Initialize everything for a new game of SOS. Player 0 always goes first.
     */
    public Game() {
        player = 0;
        scores = new int[PLAYERS];
        board  = new SosBoard(3);
        scanIn = new Scanner(System.in);
    }

    /**
     * The method where gameplay takes place until the board is full.
     */
    public void go() {

        do {
            System.out.println(board);
            makeMove();
            changePlayer();
            displayScore();
        } while(!board.isFull());



        int highScore = 0;
        for (int i = 1; i < PLAYERS; i++) {
            if (scores[i] > scores[highScore])
                highScore = i;
        }

        System.out.println("The winner is: Player " + highScore);

    }

    /**
     * Print the current score of all players to standard out.
     */
    private void displayScore() {
        for (int current = 0; current < PLAYERS; current++) {
            System.out.println("Player " + current + " has a score of " + scores[current]);
        }
    }

    /**
     * Determines whos turn it is 
     */
    private void changePlayer() {
        // QUESTION: What in the world is going on here?
        player = (player + 1) % PLAYERS;
    }

    /**
     * Allow a player to make a valid move.
     */
    private void makeMove() {
        boolean done = false;
        int row = -1;
        int col = -1;
        String msg = "";
        do {
            System.out.print(msg);
            System.out.print("Player " + player + " choose a row and column >");
            row = scanIn.nextInt();
            col = scanIn.nextInt();

            System.out.print("\nWould you like to play 'S' or 'O' >");
            char icon = scanIn.next().toUpperCase().charAt(0);
            done = board.setSpot(row, col, icon);

            msg = "Invalid move, try again\n";
        } while(!done);

        // If we get here the player made a valid move and we see if the got any points. 
        scores[player] += board.calculatePoints(row, col);
    }

    /**
     * Entry point into this program.
     * @param args Not used.
     */
    public static void main(String[] args) {
        Game driver = new Game();
        driver.go();
    }

}
