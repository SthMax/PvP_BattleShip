public class localToRemote extends Thread {
    private listenerAndSender receiver;
    private map localMap;

    public localToRemote(listenerAndSender input, map inputMap) {
        try {
            this.receiver = input;
            this.localMap = inputMap.getMap();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void run() {
        
    }
}
