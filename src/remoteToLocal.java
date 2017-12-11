public class remoteToLocal extends Thread {
    private listenerAndSender receiver, sender;
    private map localMap;

    public remoteToLocal(listenerAndSender input, listenerAndSender outPut, map inputMap) {
        try {
            this.receiver = input;
            this.sender = outPut;
            this.localMap = inputMap;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            while(!localMap.getWinner()) {
                String commandReceived = receiver.receiver();
                commandSwitcher(commandReceived);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void commandSwitcher(String command) {
        if (command.equals("shoot")) {
            beingShootMethod();
        }
    }

    private void beingShootMethod() {
        try {
            receiver.sender("Where do You Want to Shoot?");
            String indexNum = receiver.receiver();
            boolean result = localMap.shoot(indexNum.charAt(0) - '0', indexNum.charAt(2) - '0');
            if(result) {
                receiver.sender("Yes");
            } else {
                receiver.sender("No");
            }
        } catch (Exception e) {
            try {
                receiver.sender(e.getMessage());
            } catch (Exception f) {
                f.printStackTrace();
            }
        }

    }



}
