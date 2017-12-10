import java.io.*;
import java.net.*;

public class client {
    private final int connection_time_out = 2000;

    public void initialize(java.util.Scanner s) throws IOException {
        while (true) {
            System.out.println("Client Mode Initializing...");
            System.out.println("Please input host IP and host port");
            System.out.println("Host IP: ");
            String hostIP = s.nextLine();
            System.out.println("Host Port: ");
            int portNum = java.lang.Integer.parseInt(s.nextLine());
            Socket client = null;
            try {
                System.out.println("Connecting to: " + hostIP + " Using port: " + portNum);
                client = new Socket();
                client.connect(new InetSocketAddress(hostIP, portNum), connection_time_out);
                gameMain gameServer = new gameMain(client);
                return;
            } catch (Exception e) {
                System.out.println("Client Mode Initialized Error: " + e.getMessage());
                System.out.println("Please Try Again.\n");
            }
        }

    }
}
