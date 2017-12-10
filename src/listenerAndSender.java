import java.net.*;
import java.io.*;
import java.nio.charset.Charset;

public class listenerAndSender extends Thread {
    private Socket connection;
    private BufferedReader inputStream;
    private DataOutputStream outputStream;


    public listenerAndSender(Socket s) throws IOException {
        try {
            connection = s;
            inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            outputStream = new DataOutputStream(connection.getOutputStream());
        } catch (Exception e) {
            System.out.println("listen&sender construct error" + e.getMessage());
        }
    }

    @Override
    public void run() {
        System.out.println("Receiver & Sender Established");
    }

    public synchronized String receiver() throws IOException {
        try {
            String order = inputStream.readLine();
            System.out.println("Order Received: " + order);
            return order;
        } catch (IOException e) {
            System.out.println("Receiver IOException" + e.getMessage());
        }
        throw new IOException();
    }

    public synchronized void sender(final String input) throws Exception {
        try {
            outputStream.write(input.getBytes(Charset.forName("UTF-8")));
            return;
        } catch (IOException e) {
            System.out.println("Sender IOException");
        }
    }





}
