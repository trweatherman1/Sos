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
        if (args.length < 2) {
            System.out.println("Usage: java MultiUserSosClientDriver " +
                    "<host ip> <port> <nickname>");
        } else {
            MultiuserSosClient multiuserSosClient = new MultiuserSosClient
                    (args[0], args.length == 2 ? Integer.parseInt(args[1]) :
                            ProgramConstants.DEFAULTPORT);
            multiuserSosClient.playGame();
            Scanner scanner = new Scanner(System.in);
            String command = "";
            multiuserSosClient.sendMessage("/connect " + args[2]);
            while (multiuserSosClient.isActive() && !command.toLowerCase().contains("quit")) {
                command = scanner.nextLine();
                multiuserSosClient.sendMessage(command);
            }
            multiuserSosClient.cleanUp();
            System.out.println("Connection Ended");
        }

    }
}
