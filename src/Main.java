import base.Player;

public class Main {
    public static void main(String[] args) {

        Player main = new Player();
        Temple temple = new Temple(main);

        main.start();
    }
}
