import java.io.*;

public class main {
    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        System.out.println("Do You want to start a game or join a game?");
        System.out.println("Start a game: Type s.");
        System.out.println("Join a game: Type j.");
        System.out.println("End session: Type anything else.");
        java.util.Scanner s = new java.util.Scanner(System.in);
        String command = s.nextLine();
        if (command.equals("s")) {
            server gameServer = new server();
            try {
                gameServer.initialize(s);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (command.equals("j")) {
            client gameClient = new client();
            try {
                gameClient.initialize(s);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Session Ended, Bye.");

    }
}
