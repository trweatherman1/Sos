package Server.Commands;

import Common.MessageSource;
import Common.ProgramConstants;
import Server.MultiuserSosServer;

/**
 * This class executes the play command so players can start a game of SOS
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 06/11/2015
 */
public class PlayCommand extends AbstractCommand {
    /**
     * This constructor takes in an array of String arguments which can be
     * variable in size, for all commands
     *
     * @param commandArgs the String commands for execution
     */
    public PlayCommand(String[] commandArgs) {
        super(commandArgs,
                ProgramConstants.NOARGS);
    }

    /**
     * This method executes a command on behalf of a server
     *
     * @param server        the server to execute the command for
     * @param messageSource the source the command comes from
     */
    @Override
    public void execute(MultiuserSosServer server, MessageSource messageSource) {
        if (server.numConnectedClients() < 2) {
            server.broadcast("Not enough players for a game");
        } else if (server.isInGame()) {
            server.broadcast("The game is already running");
        } else {
            server.setupGame();
        }
    }
}
