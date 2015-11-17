package Common;

/**
 * This class simply holds all the constants of the program
 *
 * @author Daniel Powell
 * @version 03/11/2015
 */
public class ProgramConstants {

    /**
     * This is the default port for my server
     */
    public static int DEFAULTPORT = 5019;

    /**
     * This is the default size
     */
    public static int DEFAULTSIZE = 3;

    /** Return code for invalid move */
    public static int INVALIDMOVE = 1;

    /** Return code for player out of turn*/
    public static int WRONGPLAYER = 2;

    /** Return code for valid move */
    public static int VALIDMOVE = 0;

    /** Return code for full board */
    public static int FULLBOARD = 3;

    /** The number of arguments for a 4 tuple command */
    public static int FOURTUPLE = 4;

    /** The number of arguments for a 0 tuple command */
    public static int NOARGS = 4;

    /** The number of arguments for a 2 tuple command */
    public static int TWOTUPLE = 4;


}
