import java.util.Scanner;

public class localToRemote extends Thread {
    private listenerAndSender sender;
    private map localMap;
    private Scanner s;

    public localToRemote(listenerAndSender input, map inputMap) {
        try {
            this.sender = input;
            this.localMap = inputMap;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        s = new Scanner(System.in);
        try {
            while(!localMap.getWinner()) {
                String command = s.nextLine();
                commandSwitcher(command.toLowerCase());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void commandSwitcher(String str) {
        if (str.equals("help")) {
            helpMethod();
        } else if (str.equals("shoot")) {
            shootMethod(str);
        } else if (str.equals("display")) {
            localMap.printMap(true);
        } else if (str.equals("displayshooted")) {
            localMap.printMap(false);
        } else {
            System.out.println("Illegal Command, please type 'help' for help.");
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

    private void shootMethod(String shoot) {
        try {
            sender.sender("shoot");
            System.out.println(sender.receiver());
            String indexNum, received;
            while (true) {
                System.out.println("Please Input Your Index as 'x,y'");
                indexNum = s.nextLine();
                try {
                    localMap.shoot(indexNum.charAt(0) - '0', indexNum.charAt(2) - '0');
                } catch (Exception f) {
                    f.printStackTrace();
                    System.out.println("Please Try again\n");
                    continue;
                }
                break;
            }
            sender.sender(indexNum);
            received = sender.receiver();
            if(received.equals("Yes")) {
                System.out.println("You Have successfully hitted "
                        + (indexNum.charAt(0) - '0') + ", " + (indexNum.charAt(2) - '0'));
            } else {
                System.out.println("There is nothing on "
                        + (indexNum.charAt(0) - '0') + ", " + (indexNum.charAt(2) - '0'));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return;
    }
}
