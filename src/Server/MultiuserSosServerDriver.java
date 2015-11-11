package Server;

import Common.ProgramConstants;

/**
 * This class runs the SOS server
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 11/10/2015
 */
public class MultiuserSosServerDriver {
    public static void main(String args[]) {
        MultiuserSosServer multiuserSosServer = new MultiuserSosServer
                (Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        multiuserSosServer.listen();
    }
}
