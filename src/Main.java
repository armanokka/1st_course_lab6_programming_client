import commands.Clear;
import handlers.*;
import interfaces.Command;
import network.Client;
import network.Request;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class Main{
    private static final String host = "localhost";
    private static final int port = 1051;

    public static void main(String [] args) throws Exception {
        var client = new Client(host, port, 5000, 5);
        var reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("shell>>");
            var line = reader.readLine();

            CommandHandler.process(line, client, reader);
        }
    }
}