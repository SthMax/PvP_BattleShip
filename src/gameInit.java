import java.net.Socket;
import java.io.*;
import java.util.Scanner;

public class gameInit {
    private Socket connection;
    private boolean mapInitialized = false;
    private remoteToLocal remote;
    private localToRemote local;
    private listenerAndSender lAS;
    private boolean serverFlag;
    private map gameMap;

    public gameInit(Socket socket, boolean ifServer) {
        System.out.println("Connected, Starting Game.");
        serverFlag = ifServer;
        connection = socket;
        lAS = new listenerAndSender(socket);
        initGameMethod();
    }

    private void initGameMethod() {
        lAS.run();
        System.out.println("Please Enter the Command");
        System.out.println("Enter 'help' to get help");
        while(!mapInitialized) {
            Scanner s = new Scanner(System.in);
            String keyboardInput = s.nextLine();
            initCommandSwitcher(keyboardInput.toLowerCase());
        }
        System.out.println("Waiting for the other player...");
        try {
            lAS.sender("The Other Side is Ready");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String s = lAS.receiver();
        lAS.interrupt();
        System.out.println("Game is ready, both maps are initialized");

        //local = new localToRemote(connection, gameMap);
        //local.run();
        mainGameMethod();

    }

    private void initCommandSwitcher(final String s) {
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
        Scanner scanner = new Scanner(System.in);

        while (i > 0) {
            System.out.println("You need to set a " + i + " block ship now, please enter your index for it");
            String input = scanner.nextLine();
            if (input.length() != 7) {
                System.out.println("Your Input format is not correct, please enter again");
                continue;
            }
            try {
                gameMap.setShip(input.charAt(0) - '0', input.charAt(2) - '0',
                        input.charAt(4) - '0', input.charAt(6) - '0', i);
                //this.wait(50);
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

    private void mainGameMethod() {
        if(serverFlag) {
            System.out.println("You Are Going First");
        } else {
            System.out.println("You Are Going Second, waiting for your opponent's operation");
        }

        try {
            while (true) {
                if(serverFlag) {
                    System.out.println("Now It's Your Turn");
                    local = new localToRemote(connection, gameMap);
                    local.run();
                    serverFlag = !serverFlag;
                    System.out.println("Your Turn is Ended\n");
                } else {
                    System.out.println("It's Your Opponent's Turn");
                    remote = new remoteToLocal(connection, gameMap);
                    remote.run();
                    serverFlag = !serverFlag;
                }
            }
        } catch (Exception e) {
            System.out.println("There are some problems with the in-game process!");
            e.printStackTrace();
        }

    }


}
