package Server.Commands;

import Common.MessageSource;
import Common.ProgramConstants;
import Server.MultiuserSosServer;

/**
 * This command allows players to quit the server and consequently the game
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 11/10/2015
 */
public class QuitCommand extends AbstractCommand {
    /**
     * This constructor takes in an array of String arguments which can be
     * variable in size, for all commands
     *
     * @param commandArgs the String commands for execution
     */
    public QuitCommand(String[] commandArgs) {
        super(commandArgs,
                ProgramConstants.NOARGS);
    }

    /**
     * This method executes a command on behalf of a server
     *
     * @param server        the server to execute the command for
     * @param messageSource the source that the command comes from
     */
    @Override
    public void execute(MultiuserSosServer server, MessageSource messageSource) {
        server.privateMessage("Goodbye", messageSource);
        server.sourceClosed(messageSource);
    }
}