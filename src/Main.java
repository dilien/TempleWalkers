import base.Console;
import base.Player;

public class Main {
    public static void main(String[] args) {

        Player main = new Player();
        Temple temple = Temple.getInstance();
        temple.testInit(main);

        while(true){
            boolean timeTaken = Console.getInstance().dashboard(main);
            if(timeTaken){
                temple.tick();
            }
        }
    }
}
