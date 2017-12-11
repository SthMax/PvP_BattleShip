import java.io.*;
import java.net.*;

public class server {
    public void initialize(java.util.Scanner s) throws IOException {
        while (true) {
            System.out.println("Server Mode Initializing...");
            System.out.println("Please input your desired port number for Receive and Send");
            int portNumIn = s.nextInt();
            int portNumOut = s.nextInt();
            try {
                System.out.println("Your IP Address Is: " + Inet4Address.getLocalHost());
                System.out.println("Your Receive Port Number Is: " + portNumIn);
                System.out.println("Your Send Port Number Is: " + portNumOut);
                System.out.println("Waiting for Connection...");
                ServerSocket serverIn = new ServerSocket(portNumIn);
                ServerSocket serverOut = new ServerSocket(portNumOut);
                Socket serverI = serverIn.accept();
                Socket serverO = serverOut.accept();
                gameInit gameServer = new gameInit(serverI, serverO);
                return;
            } catch (Exception e) {
                System.out.println("Server Mode Initialized Error: " + e.getMessage());
                System.out.println("Please Try Again.\n");
            }
        }

    }
}
