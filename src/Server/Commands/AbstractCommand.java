package Server.Commands;

/**
 * This is the parent class that has a constructor taking in an array with
 * the required parameters for the command and has a single method to execute
 * the command
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 05/11/2015
 */
public abstract class AbstractCommand implements CommandExecutable {

    /**
     * This is the array of command arguments for the command
     */
    protected String[] commandArgs;

    /**
     * This is the required size of the commandArgs array
     */
    protected int numArgs;

    /**
     * This constructor takes in an array of String arguments which can be
     * variable in size, for all commands
     *
     * @param commandArgs the String commands for execution
     */
    public AbstractCommand(String[] commandArgs, int numArgs) {
        this.commandArgs = commandArgs;
        this.numArgs = numArgs;
    }

    /**
     * A method that will get the number of arguments the command line will accept when a given
     * command is called
     * @return numArgs, number of arguments from the command line
     */
    public int getNumArgs() {
        return numArgs;
    }
}
