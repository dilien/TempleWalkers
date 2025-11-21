import base.Console;
import base.Player;
import temple.Temple;

//Run this code to run the program
public class Main {
    public static void main(String[] args) {

        Player main = new Player();
        Temple temple = Temple.getInstance();
        temple.testInit(main);

        while(!main.end){
            Console.getInstance().dashboard(main);
            //temple.tick();
        }

        main.summarise();
    }
}
