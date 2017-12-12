import java.io.*;
import java.net.*;

public class client {
    private final int connection_time_out = 2000;

    public void initialize(java.util.Scanner s) {
        while (true) {
            System.out.println("Client Mode Initializing...");
            System.out.println("Please input host IP and host Port");
            System.out.println("Host IP: ");
            String hostIP = s.nextLine();
            System.out.println("Host Port: ");
            int inPortNum = s.nextInt();
            Socket clientSocket;
            try {
                System.out.println("Connecting to: " + hostIP + " Using Port: " + inPortNum);
                clientSocket = new Socket();
                clientSocket.connect(new InetSocketAddress(hostIP, inPortNum), connection_time_out);
                gameInit gameCilent = new gameInit(clientSocket, false);
                return;
            } catch (Exception e) {
                System.out.println("Client Mode Initialized Error: " + e.getMessage());
                System.out.println("Please Try Again.\n");
            }
        }

    }
}
