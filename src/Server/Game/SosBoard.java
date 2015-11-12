package Server.Game;


/**
 * Models and SOS board
 *
 * @author Trent Weatherman
 * @author Daniel Powell
 * @version 2015
 */

public class SosBoard {

    /**
     * A 2d array that will be the game board
     */
    private char board[][];
    /**
     * an integer value that will be empty
     */
    private int empty;


    /**
     * Constructor that builds the Sos Board
     *
     * @param size the size of the board for the number of rows and number of columns
     */
    public SosBoard(int size) {
        board = new char[size][size];
        empty = 0;
        initBoard();
    }


    /**
     * A method that checks to see if the position is in the board
     *
     */
    private void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = ' ';
            }
        }
    }


    /**
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
        if ((icon == 'S' || icon == 'O') &&
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
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == (' ')) {
                    count++;
                }
            }

        }

        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
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
        if (num >= 0 && num < board.length) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * A method that checks vertically and horizontally if the board spells sos
     *
     * @param row
     * @param col
     * @return
     */
    public int checkCompass(int row, int col) {
        int points = 0;
        char letter = Character.toLowerCase(board[row][col]);
        if (letter == 'o') {
            if (hasNorth(row,letter) && hasSouth(row,letter)) {
                if (hasO(board[row + 1][col],board[row - 1][col])) {
                    points++;
                }
            }
            if (hasEast(col,letter) && hasWest(col,letter)) {
                if (hasO(board[row][col - 1],board[row][col + 1])) {
                    points++;
                }
            }
        }
        if (letter == 's') {
            if (hasSouth(row, letter)) {
                if (hasS(board[row + 1][col],board[row + 2][col])) {
                    points++;
                }
            }
            if (hasNorth(row,letter)) {
                if (hasS(board[row - 1][col],board[row - 2][col])) {
                    points++;
                }
            }
            if (hasEast(col,letter)) {
                if (hasS(board[row][col + 1],board[row][col + 2])) {
                    points++;
                }
            }
            if (hasWest(col,letter)) {
                if (hasS(board[row][col - 1],board[row][col - 2])) {
                    points++;
                }
            }
        }
        return points;
    }


    /**
     * A method that checks diagonally if the board spells sos
     *
     * @param row
     * @param col
     * @return
     */
    public int checkDiag(int row, int col) {
        int points = 0;
        char letter = Character.toLowerCase(board[row][col]);
        if (letter == 'o') {
            if (hasSE(row,col,letter) && hasNW(row,col,letter)) {
                if (hasO(board[row + 1][col],board[row - 1][col]) ) {
                    points++;
                }
            } else if (hasSW(row,col,letter) && hasNE(row,col,letter)) {
                if (hasO(board[row][col - 1],board[row][col + 1])) {
                    points++;
                }
            }
        }
        if (letter == 's') {
            if (hasNW(row,col,letter)) {
                if (hasS(board[row - 1][col - 1],board[row - 2][col - 2])){
                    points++;
                }
            }
            if (hasNE(row,col,letter)) {
                if (hasS(board[row - 1][col + 1],board[row - 2][col + 2])) {
                    points++;
                }
            }
            if (hasSE(row,col,letter)) {
                if (hasS(board[row+1][col+1],board[row+2][col+2])) {
                    points++;
                }
            }
            if (hasSW(row,col,letter)) {
                if (hasS(board[row+1][col-1],board[row+2][col-2])) {
                    points++;
                }
            }
        }
        return points;
    }

    /**
     * A method that checks to see if the user can move south on the board
     *
     * @param row
     * @param letter
     * @return
     */
    private boolean hasSouth(int row, char letter) {
        return Character.toLowerCase(letter) == 'o'?inBounds(row + 1) :inBounds(row+2);
    }

    /**
     * A method that checks to see if the user can move north on the board
     *
     * @param row
     * @param letter
     * @return
     */
    private boolean hasNorth(int row, char letter) {
        return Character.toLowerCase(letter) == 'o'?inBounds(row - 1):inBounds(row-2);
    }

    /**
     * A method that checks to see if the user can move west on the board
     *
     * @param col
     * @param letter
     * @return
     */
    private boolean hasWest(int col, char letter) {
        return Character.toLowerCase(letter) == 'o'?inBounds(col-1):inBounds(col-2);
    }

    /**
     * A method that checks to see if the user can move east on the board
     *
     * @param col
     * @param letter
     * @return
     */
    private boolean hasEast(int col, char letter) {
        return Character.toLowerCase(letter) == 'o'?inBounds(col + 1):inBounds(col+2);
    }

    /**
     * A method that checks to see if the user can move northwest on the board
     *
     * @param row
     * @param col
     * @param letter
     * @return
     */
    private boolean hasNW(int row, int col, char letter) {
        return hasNorth(row,letter) && hasWest(col, letter);
    }

    /**
     * A method that checks to see if the user can move southwest on the board
     *
     * @param row
     * @param col
     * @param letter
     * @return
     */
    private boolean hasSW(int row, int col, char letter) {
        return hasSouth(row,letter) && hasWest(col, letter);
    }

    /**
     * A method that checks to see if the user can move northeast on the board
     *
     * @param row
     * @param col
     * @param letter
     * @return
     */
    private boolean hasNE(int row, int col, char letter) {
        return hasNorth(row,letter) && hasEast(col,letter);
    }

    /**
     * A method that checks to see if the user can move southeast on the board
     *
     * @param row
     * @param col
     * @param letter
     * @return
     */
    private boolean hasSE(int row, int col, char letter) {
        return hasSouth(row,letter) && hasEast(col,letter);
    }

    /**
     * A method that checks if the current letter is 'o' by checking letter1 and letter2
     *
     * @param letter1
     * @param letter2
     * @return
     */
    private boolean hasO(char letter1, char letter2) {
        return Character.toLowerCase(letter1) == 's' && Character.toLowerCase(letter2) == 's';
    }

    /**
     * A method that checks if the current letter is 's' or 'o' by checking letter1 and letter2
     *
     * @param letter1
     * @param letter2
     * @return
     */
    private boolean hasS(char letter1, char letter2) {
        return Character.toLowerCase(letter1) == 'o' && Character.toLowerCase(letter2) == 's';
    }

/*##########################################################################################
  # Do not modify under this divider                                                       #
  ##########################################################################################*/

    /**
     * A method that will print out a string for the boarder around the rows and columns
     * @return
     */
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
     *
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
     *
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
