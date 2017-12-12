import java.util.Scanner;
import java.net.Socket;

public class localToRemote extends Thread {

    private static final int NORMAL_CASE = 0, 
            SHOOTED_CASE = 1, WIN_CASE = 2;
    
    private listenerAndSender connector;
    private map localMap;
    private Scanner s;

    public localToRemote(Socket connection, map inputMap) {
        try {
            this.connector = new listenerAndSender(connection);
            connector.run();
            this.localMap = inputMap;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        s = new Scanner(System.in);
        int result;
        try {
            while(true) {
                String command = s.nextLine();
                result = commandSwitcher(command.toLowerCase());
                if(result == NORMAL_CASE) {
                    continue;
                } else if(result == SHOOTED_CASE) {
                    break;
                } else if(result == WIN_CASE) {
                    System.out.println("Exiting session...");
                    System.exit(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int commandSwitcher(String str) {
        if (str.equals("help")) {
            helpMethod();
            return NORMAL_CASE;
        } else if (str.equals("shoot")) {
            return shootMethod(str);
        } else if (str.equals("display")) {
            localMap.printMap(true);
            return NORMAL_CASE;
        } else if (str.equals("displayshooted")) {
            localMap.printMap(false);
            return NORMAL_CASE;
        } else {
            System.out.println("Illegal Command, please type 'help' for help.");
            return NORMAL_CASE;
        }
    }

    private void helpMethod() {
        System.out.println("Now Displaying the in game helper...\n\n" +
                "Here are the commands\n\n" +
                "To display this help message, type 'help'\n" +
                "To shoot your opponent, type 'shoot'\n" +
                "To display your map, type 'display'\n" +
                "To display your shooted map, type 'displayshooted'\n"
        );
    }

    private int shootMethod(String shoot) {
        try {
            connector.sender(shoot);
            System.out.println(connector.receiver());
            String indexNum, received;
            while (true) {
                System.out.println("Please Input Your Index as 'x,y'");
                indexNum = s.nextLine();
                try {
                    localMap.shoot(indexNum.charAt(0) - '0', indexNum.charAt(2) - '0', true);
                } catch (Exception f) {
                    f.printStackTrace();
                    System.out.println("Please Try again\n");
                    continue;
                }
                break;
            }
            connector.sender(indexNum);
            received = connector.receiver();
            if(received.equals("Yes")) {
                System.out.println("You Have successfully hitted " + indexNum);
                localMap.shootOnOtherMaps((indexNum.charAt(0) - '0'), (indexNum.charAt(2) - '0'), true);
            } else if (received.equals("No")) {
                System.out.println("There is nothing on " + indexNum);
                localMap.shootOnOtherMaps((indexNum.charAt(0) - '0'), (indexNum.charAt(2) - '0'), false);
            } else if (received.equals("Win")) {
                System.out.println("You Have successfully hitted "
                        + (indexNum.charAt(0) - '0') + ", " + (indexNum.charAt(2) - '0'));
                System.out.println("Your opponent's every single ship was sunk.\n YOU WIN!");
                return WIN_CASE;
            } else {
                System.out.println("There is some error with the program, please check");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SHOOTED_CASE;
    }
}
