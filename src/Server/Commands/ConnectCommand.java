package Server.Commands;

import Common.MessageSource;
import Server.MultiuserSosServer;

/**
 * This class is the command associated with connecting a user to the game
 * server
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 06/11/2015
 */
public class ConnectCommand extends AbstractCommand {

    /**
     * This constructor takes in an array with the required inputs for
     * execution
     *
     * @param commandArgs the array of arguments for execution
     */
    public ConnectCommand(String[] commandArgs) {
        super(commandArgs);
    }

    /**
     * This method executes the connect command
     *
     * @param server the server to execute the command for
     * @param source the source of the command
     */
    @Override
    public void execute(MultiuserSosServer server, MessageSource source) {
        String desiredUsername = commandArgs[1];
        server.addConnectedClient(desiredUsername, source);
    }
}
