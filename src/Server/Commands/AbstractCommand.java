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
public class AbstractCommand {

    /** This is the array of command arguments for the command */
    private String[] commandArgs;

    public AbstractCommand(String[] commandArgs) {
        this.commandArgs = commandArgs;
    }

    public void execute() {

    }
}
