package Server.Commands;

import Common.MessageSource;
import Server.MultiuserSosServer;

/**
 * This interface lets the user know that anything implementing it can
 * execute a command
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 06/11/2015
 */
public interface CommandExecutable {
    /**
     * This method executes a command on behalf of a server
     * @param server the server to execute the command for
     */
    void execute(MultiuserSosServer server, MessageSource messageSource);
}
