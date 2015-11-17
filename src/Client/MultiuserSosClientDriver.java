package Client;

import Common.ProgramConstants;

import java.util.Scanner;

/**
 * This class runs the Client for the SOS game
 *
 * @author Daniel Powell
 * @author Trent Weatherman
 * @version 02/11/2015
 */
public class MultiuserSosClientDriver {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java MultiUserSosClientDriver " +
                    "<host ip> <port>");
        } else {
            MultiuserSosClient multiuserSosClient = new MultiuserSosClient
                    (args[0], args.length == 2 ? Integer.parseInt(args[1]) :
                            ProgramConstants.DEFAULTPORT);
            multiuserSosClient.playGame();
            Scanner scanner = new Scanner(System.in);
            String command = "";
            while (!command.toLowerCase().contains("quit") && multiuserSosClient.isActive()) {
                command = scanner.nextLine();
                multiuserSosClient.sendMessage(command);
            }
            multiuserSosClient.cleanUp();
        }

    }
}
