import java.net.Socket;
import java.io.*;
import java.util.Scanner;

public class gameMain {
    private Socket connection;
    private static boolean mapInitialized = false;
    private listenerAndSender lAS;
    private map gameMap;

    public gameMain(Socket s) throws IOException{
        System.out.println("Connected, Starting Game.");
        connection = s;
        lAS = new listenerAndSender(s);
        mainGameMethod();
    }

    private void mainGameMethod() throws IOException{
        System.out.println("Please Enter the Command");
        System.out.println("Enter 'Help' to get help");
        lAS.run();
        while(true) {
            Scanner s = new Scanner(System.in);
            String keyboardInput = s.nextLine();
            commandSwitcher(keyboardInput.toLowerCase());

        }
    }

    private void commandSwitcher(final String s) {
        if (s.equals("help")) {
            helpMethod();
            return;
        } else if (s.equals("init")) {
            mapInit();
            return;
        }
    }

    private void mapInit() {
        if (!mapInitialized) {
            System.out.println("Map has been already initialized!");
        }

        System.out.println("A 10*10 map is ");


    }


    private void helpMethod() {
        System.out.println("Now Displaying the game helper...\n\n" +
                "For Battleship Game info, you can check Wiki.\n" +
                "First you need to build a game map which is 10x10 and some ships.\n" +
                "Then Shoot your opponent's ship\n" +
                "Here are the commands\n\n" +
                "To display help message, type 'help'\n" +
                "To setup a board, type 'init'\n"
        );
    }

    //private void


}
