import java.io.*;
import java.net.*;

public class client {
    private final int connection_time_out = 2000;

    public void initialize(java.util.Scanner s) throws IOException {
        while (true) {
            System.out.println("Client Mode Initializing...");
            System.out.println("Please input host IP and host port for in and out");
            System.out.println("Host IP: ");
            String hostIP = s.nextLine();
            System.out.println("Host Input Port: ");
            int inPortNum = s.nextInt();
            System.out.println("Host outPut Port: ");
            int outPortNum = s.nextInt();
            Socket clientSocketIn, clientSocketOut;
            try {
                System.out.println("Connecting to: " + hostIP
                        + " Using inPort: " + inPortNum + " outPort: " + outPortNum);
                clientSocketOut = new Socket();
                clientSocketOut.connect(new InetSocketAddress(hostIP, inPortNum), connection_time_out);
                clientSocketIn = new Socket(hostIP, outPortNum);
                gameInit gameCilent = new gameInit(clientSocketOut, clientSocketIn);
                return;
            } catch (Exception e) {
                System.out.println("Client Mode Initialized Error: " + e.getMessage());
                System.out.println("Please Try Again.\n");
            }
        }

    }
}
