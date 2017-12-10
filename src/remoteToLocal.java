public class remoteToLocal extends Thread {
    private listenerAndSender receiver;
    private map localMap;

    public remoteToLocal(listenerAndSender input, map inputMap) {
        try {
            this.receiver = input;
            this.localMap = inputMap;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void run() {

    }


}
