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
     * Constructor that builds the Sos Board
     *
     * @param size the size of the board for the number of rows and number of columns
     */
    public SosBoard(int size) {
        board = new char[size][size];
        initBoard();
    }


    /**
     * A method that checks to see if the position is in the board
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
     * @param row  the horizontal spaces on the board
     * @param col  the vertical spaces on the board
     * @param icon the character that will take up the space on the board/s
     * @return false if a result has nothing in it, else return true if there is result has something in it
     */
    public boolean setSpot(int row, int col, char icon) {
        boolean result = false;
        icon = Character.toUpperCase(icon);
        if ((icon == 'S' || icon == 'O') &&
                row >= 0 && row < board.length && col >= 0 && col < board.length) {
            if (board[row][col] == ' ') {
                board[row][col] = icon;
                result = true;
            }
        }
        return result;
    }


    /**
     * A boolean method that checks to see if the board is full
     *
     * @return false if count is greater than 0 and true if count is otherwise
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
        return !(count > 0);
    }

    /**
     * A method that calculates the number of points given if a player makes sos
     *
     * @param row the horizontal spaces on the board
     * @param col the vertical spaces on the board
     * @return the methods checkCompass(row, col) and checkDiag(row, col) to calculate points vertically, horizontally
     * and diagonally
     */
    public int calculatePoints(int row, int col) {

        return checkCompass(row, col) + checkDiag(row, col);
    }


    /**
     * A boolean method that makes sure that the game is played in bounds of the board
     *
     * @param num for the number of spaces on the board
     * @return true if the number is greater than 0 and less than the length of the board or else it's false
     */
    public boolean inBounds(int num) {
        return (num >= 0 && num < board.length);
    }


    /**
     * A method that checks vertically and horizontally if the board spells sos
     *
     * @param row the horizontal spaces on the board
     * @param col the vertical spaces on the board
     * @return the points that have been gained by making SOS on the board vertically or horizontally
     */
    public int checkCompass(int row, int col) {
        int points = 0;
        char letter = Character.toLowerCase(board[row][col]);
        if (letter == 'o') {
            points += checkOCompass(row, col, letter);
        }
        if (letter == 's') {
            points += checkSCompass(row, col, letter);
        }
        return points;
    }


    /**
     * A method that checks diagonally if the board spells sos
     *
     * @param row the horizontal spaces on the board
     * @param col the vertical spaces on the board
     * @return the points that have been gained by making SOS on the board diagonally
     */
    public int checkDiag(int row, int col) {
        int points = 0;
        char letter = Character.toLowerCase(board[row][col]);
        if (letter == 'o') {
            points += checkODiagCompass(row, col, letter);
        }
        if (letter == 's') {
            points += checkSDiagCompass(row, col, letter);
        }
        return points;
    }


    /**
     * A method that checks to see if the board has icons that go to the southeast, northwest, southwest and northeast
     *
     * @param row
     * @param letter
     * @return
     * @param row the horizontal spaces on the game board
     * @param letter the letter that will be entered in a space on the board
     * @return the letter if it's in bound by 1 or else if it's in bound by 2
     */
    private int checkODiagCompass(int row, int col, char letter) {
        int points = 0;
        if (hasSE(row, col, letter) && hasNW(row, col, letter)) {
            if (hasO(board[row - 1][col - 1], board[row + 1][col + 1])) {
                points++;
            }
        }
        if (hasSW(row, col, letter) && hasNE(row, col, letter)) {
            if (hasO(board[row + 1][col - 1], board[row - 1][col + 1])) {
                points++;
            }
        }

        return points;
    }

    /**
     * Checks the if there is a letter S and if the there is an O and an S to the northwest, southwest,
     * northeast and southeast
     *
     * @param row  the horizontal spaces on the board
     * @param col  the vertical spaces on the board
     * @param letter the character that will take up the space on the board/s
     * @return return points is there is an SOS made
     */
    private int checkSDiagCompass(int row, int col, char letter) {
        int points = 0;
        if (hasNW(row, col, letter)) {
            if (hasS(board[row - 1][col - 1], board[row - 2][col - 2])) {
                points++;
            }
        }
        if (hasNE(row, col, letter)) {
            if (hasS(board[row - 1][col + 1], board[row - 2][col + 2])) {
                points++;
            }
        }
        if (hasSE(row, col, letter)) {
            if (hasS(board[row + 1][col + 1], board[row + 2][col + 2])) {
                points++;
            }
        }
        if (hasSW(row, col, letter)) {
            if (hasS(board[row + 1][col - 1], board[row + 2][col - 2])) {
                points++;
            }
        }
        return points;
    }

    /**
     * Checks the if there is a letter S and if the there is an O and an S to the north, south, east and west
     *
     * @param row  the horizontal spaces on the board
     * @param col  the vertical spaces on the board
     * @param letter the character that will take up the space on the board/s
     * @return return points is there is an SOS made
     */
    private int checkSCompass(int row, int col, char letter) {
        int points = 0;
        if (hasSouth(row, letter)) {
            if (hasS(board[row + 1][col], board[row + 2][col])) {
                points++;
            }
        }
        if (hasNorth(row, letter)) {
            if (hasS(board[row - 1][col], board[row - 2][col])) {
                points++;
            }
        }
        if (hasEast(col, letter)) {
            if (hasS(board[row][col + 1], board[row][col + 2])) {
                points++;
            }
        }
        if (hasWest(col, letter)) {
            if (hasS(board[row][col - 1], board[row][col - 2])) {
                points++;
            }
        }
        return points;
    }

    /**
     * Checks the if there is a letter O and if the there is an s to the north, south, east and west
     *
     * @param row  the horizontal spaces on the board
     * @param col  the vertical spaces on the board
     * @param letter the character that will take up the space on the board/s
     * @return return points is there is an SOS made
     */
    private int checkOCompass(int row, int col, char letter) {
        int points = 0;
        if (hasNorth(row, letter) && hasSouth(row, letter)) {
            if (hasO(board[row + 1][col], board[row - 1][col])) {
                points++;
            }
        }
        if (hasEast(col, letter) && hasWest(col, letter)) {
            if (hasO(board[row][col - 1], board[row][col + 1])) {
                points++;
            }
        }
        return points;
    }

    /**
     * A method that checks to see if the user can move south on the board
     *
     * @param row    the horizontal spaces on the game board
     * @param letter the letter that will be entered in a space on the board
     * @return the letter if it's in bound by 1 or else if it's in bound by 2
     */
    private boolean hasSouth(int row, char letter) {
        return Character.toLowerCase(letter) == 'o' ? inBounds(row + 1) : inBounds(row + 2);
    }

    /**
     * A method that checks to see if the user can move north on the board
     *
     * @param row    the horizontal spaces on the game board
     * @param letter the letter that will be entered in a space on the board
     * @return the letter if it's in bound by 1 or else if it's in bound by 2
     */
    private boolean hasNorth(int row, char letter) {
        return Character.toLowerCase(letter) == 'o' ? inBounds(row - 1) : inBounds(row - 2);
    }

    /**
     * A method that checks to see if the user can move west on the board
     *
     * @param col    the vertical spaces on the game board
     * @param letter the letter that will be entered in a space on the board
     * @return the letter if it's in bound by 1 or else if it's in bound by 2
     */
    private boolean hasWest(int col, char letter) {
        return Character.toLowerCase(letter) == 'o' ? inBounds(col - 1) : inBounds(col - 2);
    }

    /**
     * A method that checks to see if the user can move east on the board
     *
     * @param col    the vertical spaces on the game board
     * @param letter the letter that will be entered in a space on the board
     * @return the letter if it's in bound by 1 or else if it's in bound by 2
     */
    private boolean hasEast(int col, char letter) {
        return Character.toLowerCase(letter) == 'o' ? inBounds(col + 1) : inBounds(col + 2);
    }

    /**
     * A method that checks to see if the user can move northwest on the board
     *
     * @param row    the horizontal spaces on the game board
     * @param col    the vertical spaces on the game board
     * @param letter the letter that will be entered in a space on the board
     * @return the hasNorth method and the hasWest method to combine directions for the diagonals
     */
    private boolean hasNW(int row, int col, char letter) {
        return hasNorth(row, letter) && hasWest(col, letter);
    }

    /**
     * A method that checks to see if the user can move southwest on the board
     *
     * @param row    the horizontal spaces on the game board
     * @param col    the vertical spaces on the game board
     * @param letter the letter that will be entered in a space on the board
     * @return the hasSouth method and the hasWest method to combine directions for the diagonals
     */
    private boolean hasSW(int row, int col, char letter) {
        return hasSouth(row, letter) && hasWest(col, letter);
    }

    /**
     * A method that checks to see if the user can move northeast on the board
     *
     * @param row    the horizontal spaces on the game board
     * @param col    the vertical spaces on the game board
     * @param letter the letter that will be entered in a space on the board
     * @return the hasNorth method and the hasEast method to combine directions for the diagonals
     */
    private boolean hasNE(int row, int col, char letter) {
        return hasNorth(row, letter) && hasEast(col, letter);
    }

    /**
     * A method that checks to see if the user can move southeast on the board
     *
     * @param row    the horizontal spaces on the game board
     * @param col    the vertical spaces on the game board
     * @param letter the letter that will be entered in a space on the board
     * @return the hasSouth method and the hasEast method to combine directions for the diagonals
     */
    private boolean hasSE(int row, int col, char letter) {
        return hasSouth(row, letter) && hasEast(col, letter);
    }

    /**
     * A method that checks if the current letter is 'o' by checking letter1 and letter2
     *
     * @param letter1 the letter to the left of the o
     * @param letter2 the letter to the right of the o
     * @return if both letters are s so an SOS is formed
     */
    private boolean hasO(char letter1, char letter2) {
        return Character.toLowerCase(letter1) == 's' && Character.toLowerCase(letter2) == 's';
    }

    /**
     * A method that checks if the current letter is 's' or 'o' by checking letter1 and letter2
     *
     * @param letter1 is the first letter to check for if there is a neighbor
     * @param letter2 is the second letter to check for it there is neighbor
     * @return return the letters that have been made into lower case
     */
    private boolean hasS(char letter1, char letter2) {
        return Character.toLowerCase(letter1) == 'o' && Character.toLowerCase(letter2) == 's';
    }

/*##########################################################################################
  # Do not modify under this divider                                                       #
  ##########################################################################################*/

    /**
     * A method that will print out a string for the boarder around the rows and columns
     *
     * @return StringBuilder result that will display the outline of the board
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
