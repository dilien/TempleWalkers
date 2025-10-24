import base.Player;

public class Main {
    public static void main(String[] args) {

        Player main = Player.getInstance();

        Temple temple = new Temple();
        main.start();
    }
}
