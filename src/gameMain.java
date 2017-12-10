import java.net.Socket;
import java.io.*;
import java.util.Scanner;

public class gameMain {
    private Socket connection;
    private static boolean mapInitialized = false;
    private listenerAndSender lAS;

    public gameMain(Socket s) throws IOException{
        System.out.println("Connected, Starting Game.");
        connection = s;
        lAS = new listenerAndSender(s);
        mainGameMethod();
    }

    private void mainGameMethod() throws IOException{
        System.out.println("Please Enter the Commmand");
        System.out.println("Enter 'Help' to get help");
        while(true) {
            lAS.run();


            Scanner s = new Scanner(System.in);

            /*
            String keyboardInput = s.nextLine();
            String webInput = lAS.receiver();
            commandSwitcher(keyboardInput.toLowerCase());
            */
        }
    }

    private void commandSwitcher(final String s) {
        if (s.equals("help")) {
            helpMethod();
            return;
        }
    }

    private void mapInit() {

    }


    private void helpMethod() {
        System.out.println("Now Displaying the game helper...\n\n" +
                "For Battleship Game info, you can check Wiki.\n" +
                "First you need to build a game map which is 10x10 and some ships.\n" +
                "Then Shoot your opponent's ship\n" +
                "Here are the commands\n\n" +
                "To display help message, type 'help'\n" +
                ""
        );
        return;
    }

    //private void


}
