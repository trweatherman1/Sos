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
     * This constructor takes in an array of String arguments which can be
     * variable in size, for all commands
     *
     * @param commandArgs the String commands for execution
     */
    public AbstractCommand(String[] commandArgs) {
        this.commandArgs = commandArgs;
    }
}
