package Server.Game;

import java.util.Scanner;

/**
 * Models and SOS board
 * @author Trent Weatherman 
 * @author Daniel Powell
 * @version 2015
 */

public class SosBoard {

    /**A 2d array that will be the game board*/
    private char board[][];
    /**an integer value that will be empty*/
    private int empty;


    public SosBoard(int size) {
        board = new char [size][size];
        empty = 0;
        initBoard();
    }


    private void initBoard() {
        // WRITE ME
        Scanner input = new Scanner(System.in);
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board.length; j++)
            {
                board[i][j] = ' ';
            }
        }
    }


    /**
     *
     * A method that sets the spot of the game board with either an S or an O
     *
     * @param row
     * @param col
     * @param icon
     * @return
     */
    public boolean setSpot(int row, int col, char icon) {
        boolean result = false;
        icon = Character.toUpperCase(icon);
        if ((icon == 'S' || icon == 'O' ) &&
                row >= 0 && row < board.length && col >= 0 && col < board.length) {
            if (board[row][col] == ' ') {
                board[row][col] = icon;
                result = true;
                empty++;
            }
        }
        return result;
    }


    /**
     * A boolean method that checks to see if the board is full
     *
     * @return
     */
    public boolean isFull() {

        int count = 0;
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board.length; j++)
            {
                if(board[i][j] == (' '))
                {
                    count++;
                }
            }

        }

        if(count > 0)
        {
            return false;
        }
        else{
            return true;
        }
    }

    /**
     *
     * A method that calculates the number of points given if a player makes sos
     *
     * @param row
     * @param col
     * @return
     */
    public int calculatePoints(int row, int col) {
        return checkCompass(row, col) + checkDiag(row, col);
    }


    /**
     * A boolean method that makes sure that the game is played in bounds of the board
     *
     * @param num
     * @return
     */
    public boolean inBounds(int num) {
        if(num >= 0 && num < board.length)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public int checkCompass(int row, int col) {
        int points = 0;

        if(board[row][col] == 'O' || board[row][col] == 'o')
        {
            if(inBounds(row+1) && inBounds(row - 1))
            {
                if(board[row + 1][col] == 'S' && board[row - 1][col] == 'S')
                {
                    points++;
                }
            }
            if(inBounds(col + 1) && inBounds(col - 1))
            {
                if(board[row][col - 1] == 'S' && board[row][col + 1] == 'S')
                {
                    points++;
                }
            }
        }
        if(board[row][col] == 'S' || board[row][col] == 's')
        {

            if(inBounds(row + 2))
            {
                if(board[row + 1][col] == 'O' && board[row +  2][col] == 'S')
                {
                    points++;
                }
            }
            if(inBounds(row - 2))
            {
                if(board[row - 1][col] == 'O' && board[row -  2][col] == 'S')
                {
                    points++;
                }

            }
            if(inBounds(col + 2))
            {
                if(board[row][col + 1] == 'O' && board[row][col + 2] == 'S')
                {
                    points++;
                }
            }
            if(inBounds(col - 2))
            {
                if(board[row][col - 1] == 'O' && board[row][col - 2] == 'S')
                {
                    points++;
                }
            }

        }
        return points;
    }


    public int checkDiag(int row, int col) {
        int points = 0;
        /*
        if(board[row][col] == 'O' || board[row][col] == 'o')
        {
            if(inBounds(row+1) && inBounds(row - 1))
            {
                if(board[row + 1][col] == 'S' && board[row - 1][col] == 'S')
                {
                    points++;
                }
            }
            if(inBounds(col + 1) && inBounds(col - 1))
            {
                if(board[row][col - 1] == 'S' && board[row][col + 1] == 'S')
                {
                    points++;
                }
            }
        }
        if(board[row][col] == 'S' || board[row][col] == 's')
        {

            if(inBounds(row + 2))
            {
                if(board[row + 1][col + 1] == 'O' && board[row +  2][col + 2] == 'S')
                {
                    points++;
                }
            }
            if(inBounds(row - 2))
            {
                if(board[row - 1][col -1] == 'O' && board[row -  2][col - 2] == 'S')
                {
                    points++;
                }

            }
            if(inBounds(col + 2))
            {
                if(board[row + 1][col + 1] == 'O' && board[row + 2][col + 2] == 'S')
                {
                    points++;
                }
            }
            if(inBounds(col - 2))
            {
                if(board[row - 1][col - 1] == 'O' && board[row - 2][col - 2] == 'S')
                {
                    points++;
                }
            }

        }
        */
        return points;
    }

/*##########################################################################################
  # Do not modify under this divider                                                       #
  ##########################################################################################*/

    public String toString() {
        StringBuilder result = new StringBuilder(getEdge());
        result.append(getDivider());
        for (int row = 0; row < board.length; row++) {
            result.append(row + " |");
            for (int col = 0; col < board[row].length; col++) {
                result.append(" " + board[row][col] + " +");
            }
            // This line is kind of a hack. #sorrynotsorry
            result.append("\b|\n" + getDivider());
        }
        return result.toString();
    }

    /**
     * Prints out the first and/or last line in the sos board.
     * @return the first/last line of a matrix.
     */
    private String getDivider() {
        StringBuilder result = new StringBuilder("  +");
        for (int row = 0; row < board.length; row++) {
            result.append("---+");
        }
        result.append("\n");
        return result.toString();
    }

    /**
     * Prints out divider lines between playing spaces.
     * @return divider lines between rows of values.
     */
    private String getEdge() {
        StringBuilder result = new StringBuilder("   ");
        for (int row = 0; row < board.length; row++) {
            result.append(" " + row + "  ");
        }
        result.append("\n");
        return result.toString();
    }
}
