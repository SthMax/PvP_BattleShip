import java.util.Scanner;

public class localToRemote extends Thread {
    private listenerAndSender sender;
    private map localMap;

    public localToRemote(listenerAndSender input, map inputMap) {
        try {
            this.sender = input;
            this.localMap = inputMap;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void run() {
        Scanner s = new Scanner(System.in);
        try {
            while(!localMap.getWinner()) {
                String command = s.nextLine();
                commandSwitcher(command.toLowerCase());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void commandSwitcher(String s) {
        if (s.equals("help")) {
            helpMethod();
        } else if (s.equals("shoot")) {
            shootMethod();
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

    private void shootMethod() {
        return;
    }
}
