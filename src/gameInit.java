import java.net.Socket;
import java.io.*;
import java.util.Scanner;

public class gameInit {
    private Socket connection;
    private boolean mapInitialized = false;
    private remoteToLocal remote;
    private localToRemote local;
    private listenerAndSender lAS;
    private map gameMap;

    public gameInit(Socket s) throws IOException{
        System.out.println("Connected, Starting Game.");
        connection = s;
        lAS = new listenerAndSender(s);
        initGameMethod();
    }

    private void initGameMethod() throws IOException{
        lAS.run();
        System.out.println("Please Enter the Command");
        System.out.println("Enter 'help' to get help");
        while(!mapInitialized) {
            Scanner s = new Scanner(System.in);
            String keyboardInput = s.nextLine();
            commandSwitcher(keyboardInput.toLowerCase());
        }
        System.out.println("Waiting for the other player...");
        try {
            lAS.sender("The Other Side is Ready\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String s = lAS.receiver();
        System.out.println("Game is ready, both maps are initialized");
        System.out.println("Enter 'help' for further help");
        remote = new remoteToLocal(lAS, gameMap);
        local = new localToRemote(lAS, gameMap);
        local.run();
        remote.run();


    }

    private void commandSwitcher(final String s) {
        if (s.equals("help")) {
            helpMethod();
        } else if (s.equals("init")) {
            mapInit();
        } else {
            System.out.println("Map is Uninitialized, you cannot do other things.");
        }
    }

    private void mapInit() {
        int value = 10;
        try {
            gameMap = new map(value,value);
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("A " + gameMap.getWidth() + "*" + gameMap.getHeight() + " map is initialized.");
        System.out.println("Please Enter Your ship's location using four numbers.");
        System.out.println("First two for the index of the head of the ship.");
        System.out.println("Next two for the index of the tail of the ship.");
        System.out.println("Please enter indexes such as 'x1,y1,x2,y2' or the command may not be accepted.");
        System.out.println("The placement of the ship needs to be either vertical or horziontal.");
        System.out.println("If there is a one-block ship, please enter as x1,y1,x1,y1");

        int i = gameMap.shipLimit;

        while (i > 0) {
            System.out.println("You need to set a " + i + " block ship now, please enter your index for it");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.length() != 7) {
                System.out.println("Your Total format is not correct, please enter again");
                continue;
            }
            try {
                gameMap.setShip(input.charAt(0) - '0', input.charAt(2) - '0',
                        input.charAt(4) - '0', input.charAt(6) - '0', i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            i--;
        }

        System.out.println("Your map is initialized\nHere Is Your Map");
        System.out.println("O stands for your blocks not being hit, X stands for your blocks being hit");
        this.mapInitialized = true;
        gameMap.printMap(true);
    }


    private void helpMethod() {
        System.out.println("Now Displaying the before game helper...\n\n" +
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
