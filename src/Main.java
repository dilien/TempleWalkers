import base.Console;
import base.Player;

public class Main {
    public static void main(String[] args) {

        Player main = new Player();
        Temple temple = new Temple(main);

        while(true){
            Console.getInstance().dashboard(main);
            temple.tick();
        }
    }
}
