package handlers;

import entities.Route;
import interfaces.Command;
import network.Client;
import network.Request;

import java.io.BufferedReader;
import java.io.IOException;

public class CommandHandler {
    /**
     * Processes the given command line using the provided CollectionHandler.
     *
     * @param line the command line to process
     */
    public static void process(String line, Client client, BufferedReader reader){
        if (line == null) {
            System.exit(0);
            return;
        }
        if (line.isBlank()) {
            System.out.println("Command cannot be empty!");
            return;
        }

        var args = line.split(" ");

        Command command = PackageParser.getCommand(args[0].strip());
        if (command == null) {
            System.out.println("Unknown command");
            return;
        }

        if (command.getName().equals("insert")) {
            if (args.length != 2) {
                System.out.println("invalid number of arguments");
                return;
            }
            var key = Integer.parseInt(args[1]);
            var r = new Route(key, reader);
            try {
                System.out.println(client.sendRequest(new Request(command, r)).getResult());
            } catch (Exception e) {
                System.out.println(e);
            }
            return;
        } else if (command.getName().equals("exit")) {
            System.exit(0);
            return;
        }

        try {
            System.out.println(client.sendRequest(new Request(command, args)).getResult());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
