package Server.Commands;

import Common.MessageSource;
import Common.ProgramConstants;
import Server.MultiuserSosServer;

import java.util.Arrays;

/**
 * This class allows chatting within the game, private or broadcast
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 1.0
 */
public class ChatCommand extends AbstractCommand implements CommandExecutable {
    /**
     * This constructor takes in an array of String arguments which can be
     * variable in size, for all commands
     *
     * @param commandArgs the String commands for execution
     */
    public ChatCommand(String[] commandArgs) {
        super(commandArgs,
                ProgramConstants.TWOTUPLE);
    }

    /**
     * This method executes a command on behalf of a server
     *
     * @param server        the server to execute the command for
     * @param messageSource the source of the command
     */
    @Override
    public void execute(MultiuserSosServer server, MessageSource messageSource) {

        String[] message = Arrays.copyOfRange(commandArgs, 1, commandArgs
                .length);
        server.chat(String.join(" ", message), messageSource);
    }
}
