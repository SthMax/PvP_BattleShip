public class remoteToLocal extends Thread {
    private listenerAndSender sender;
    private map localMap;

    public remoteToLocal(listenerAndSender input, map inputMap) {
        try {
            this.sender = input;
            this.localMap = inputMap.getMap();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void run() {

    }
}
