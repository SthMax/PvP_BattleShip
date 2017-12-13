import java.net.Socket;

public class remoteToLocal extends Thread {

    private static final int NORMAL_CASE = 0,
            SHOOTED_CASE = 1, WIN_CASE = 2;

    private listenerAndSender connector;
    private map localMap;

    public remoteToLocal(Socket connection, map inputMap) {
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
        try {
            while(true) {
                String commandReceived = connector.receiver();
                int result = commandSwitcher(commandReceived);
                if (result == WIN_CASE) {
                    System.out.println("Exiting session...");
                    System.exit(1);
                } else if (result == SHOOTED_CASE) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int commandSwitcher(String command) throws Exception{
        if (command.equals("shoot")) {
            return beingShootMethod();
        } else if (command.equals("concede")) {
            System.out.println("Your opponent was scared by your amazing tactics and ran away");
            System.out.println("You Win!");
            System.out.println(":D");
            return WIN_CASE;
        }
        return NORMAL_CASE;
    }

    private int beingShootMethod() throws Exception {
        try {
            connector.sender("Where do You Want to Shoot?");
            String indexNum = connector.receiver();
            boolean result = localMap.shoot(indexNum.charAt(0) - '0', indexNum.charAt(2) - '0', false);
            if (localMap.getWinner()) {
                connector.sender("Win");
                System.out.println("Your opponent has a hit to your ship in: " + indexNum + "\n");
                System.out.println("All Your Ship Was Gone");
                System.out.println("YOU LOSE");
                return WIN_CASE;
            } else if(result) {
                connector.sender("Yes");
                System.out.println("Your opponent has a hit to your ship in: " + indexNum + "\n");
                return SHOOTED_CASE;
            } else {
                connector.sender("No");
                System.out.println("Your opponent has a hit in: " + indexNum + "\n");
                System.out.println("But there's nothing.");
                return SHOOTED_CASE;
            }
        } catch (Exception e) {
            try {
                connector.sender(e.getMessage());
            } catch (Exception f) {
                f.printStackTrace();
            }
        }
        throw new Exception("Something Wrong with the beingshootmethod, check it.");
    }



}
