import java.io.*;
import java.net.*;

public class server {
    public void initialize(java.util.Scanner s) throws IOException {
        while (true) {
            System.out.println("Server Mode Initializing...");
            System.out.println("Please input your desired port number");
            int portNum = s.nextInt();
            try {
                System.out.println("Your IP Address Is: " + Inet4Address.getLocalHost());
                System.out.println("Your Port Number Is: " + portNum);
                System.out.println("Waiting for Connection...");
                ServerSocket server = new ServerSocket(portNum);
                Socket serversocket = server.accept();
                gameMain gameServer = new gameMain(serversocket);
                return;
            } catch (Exception e) {
                System.out.println("Server Mode Initialized Error: " + e.getMessage());
                System.out.println("Please Try Again.\n");
            }
        }

    }
}
