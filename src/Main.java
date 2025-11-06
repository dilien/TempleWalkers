import base.Console;
import base.Player;
import Temple.Temple;

//Run this code to run the program
public class Main {
    public static void main(String[] args) {

        Player main = new Player();
        Temple temple = Temple.getInstance();
        temple.testInit(main);

        while(true){
            Console.getInstance().dashboard(main);
            //temple.tick();
        }
    }
}
