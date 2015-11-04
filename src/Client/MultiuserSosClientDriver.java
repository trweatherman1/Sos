package Client;

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
                    "<host ip> |port|");
        } else {
            MultiuserSosClient multiuserSosClient = new MultiuserSosClient
                    (args[0], Integer.parseInt(args[1]));
            multiuserSosClient.playGame();
            Scanner scanner = new Scanner(System.in);
            System.out.print("> ");
            String command = scanner.nextLine();
            multiuserSosClient.sendMessage(command);
        }

    }
}
