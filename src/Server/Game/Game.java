package Server.Game;

import Common.MessageListener;

import java.util.Scanner;

/**
 * Models a game of SOS
 *
 * @author Trent Weatherman
 * @author Daniel Powell
 * @version 11/10/2015
 */

public class Game {
    /** Current player. From 0 to n*/
    private int player;

    /**Hold the scores of all the players */
    private int[] scores;

    /** The sos board*/
    private SosBoard board;

    /** For input from the user*/
    private Scanner scanIn;

    /** Current allowed number of player */
    private int numPlayers;

    /**
     * Initialize everything for a new game of SOS. Player 0 always goes first.
     */
    public Game() {
        player = 0;
        scanIn = new Scanner(System.in);


        //System.out.println("Enter the number of players");
        //numPlayers = scanIn.nextInt();
        scores = new int[numPlayers];
        //System.out.println("Enter the size of the board");
        //int size = scanIn.nextInt();

        //board = new SosBoard(size);
        //scanIn = new Scanner(System.in);
    }

    /**
     * The method where game play takes place until the board is full.
     */
    public void go(int size) {

        do {
            board = new SosBoard(size);
            toString();
            makeMove();
            changePlayer();
            displayScore();
        } while (!board.isFull());


        int highScore = 0;
        for (int i = 1; i < numPlayers; i++) {
            if (scores[i] > scores[highScore])
                highScore = i;
        }

        System.out.println("The winner is: Player " + highScore);
    }



    /**
     * Print the current score of all players to standard out.
     */
    public void displayScore() {
        for (int current = 0; current < numPlayers; current++) {
            System.out.println("Player " + current + " has a score of " + scores[current]);
        }
    }

    /**
     * Determines whos turn it is
     */
    public void changePlayer() {
        // QUESTION: What in the world is going on here?
        player = (player + 1) % numPlayers;
    }

    /**
     * Allow a player to make a valid move.
     */
    public void makeMove(int row, int col, char icon) {
        boolean done = false;
        //int row = -1;
        //int col = -1;
        String msg = "";
        do {

            //icon = scanIn.next().toUpperCase().charAt(0);
            done = board.setSpot(row, col, icon);
            msg = "Invalid move, try again\n";
        } while (!done);

        // If we get here the player made a valid move and we see if the got any points. 
        scores[player] += board.calculatePoints(row, col);
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

    /**
     * Entry point into this program.
     *
     * @param args Not used.

    public static void main(String[] args) {
        Game driver = new Game();
        size = Integer.parseInt(args[1]);
        driver.go(size);
    }
    */

}
