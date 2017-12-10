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
        } else if () {

        } else {
            System.out.println("Illegal Command, please type 'help' for help.");
        }
    }
}
