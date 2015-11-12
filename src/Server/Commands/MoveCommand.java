package Server.Commands;

import Common.MessageSource;
import Server.MultiuserSosServer;

/**
 * This class executes the move command so players can play put letters on
 * the SOS game board
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 06/11/2015
 */
public class MoveCommand extends AbstractCommand {
    /**
     * This constructor takes in an array of String arguments which can be
     * variable in size, for all commands
     *
     * @param commandArgs the String commands for execution
     */
    public MoveCommand(String[] commandArgs) {
        super(commandArgs);

    }

    /**
     * This method executes a command on behalf of a server
     *
     * @param server        the server to execute the command for
     * @param messageSource the source the command comes from
     */
    @Override
    public void execute(MultiuserSosServer server, MessageSource messageSource)
    {
        //server.move(commandArgs[0],commandArgs[1],commandArgs[2],messageSource);
    }
}
