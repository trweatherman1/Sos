package Server.Commands;

import Common.MessageSource;
import Common.ProgramConstants;
import Server.MultiuserSosServer;

/**
 * This command is used to respond to players who have entered an invalid
 * command
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 07/11/2015
 */
public class InvalidCommand extends AbstractCommand implements
        CommandExecutable {
    /**
     * This constructor takes in an array of String arguments which can be
     * variable in size, for all commands
     *
     * @param commandArgs the String commands for execution
     */
    public InvalidCommand(String[] commandArgs) {
        super(commandArgs,
                ProgramConstants.NOARGS);
    }

    /**
     * This method executes a command on behalf of a server
     *
     * @param server        the server to execute the command for
     * @param messageSource the source of the command
     */
    @Override
    public void execute(MultiuserSosServer server, MessageSource messageSource) {
        String message = String.join(" ", commandArgs) + " is not a valid " +
                "command.";
        server.broadcast(message);
    }
}
