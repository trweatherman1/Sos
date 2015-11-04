package Server;

import Common.ProgramConstants;

/**
 * This class runs the SOS server
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 02/11/2015
 */
public class MultiuserSosServerDriver {
    public static void main(String args[]) {
        MultiuserSosServer multiuserSosServer = new MultiuserSosServer
                (ProgramConstants.DEFAULTPORT);
        multiuserSosServer.listen();
    }
}
