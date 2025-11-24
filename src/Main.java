import base.Console;
import base.Player;
import temple.Temple;
import base.DifficultyManager;

//Run this code to run the program
public class Main {
    public static void main(String[] args) {

        DifficultyManager.setDifficulty();

        Player main = new Player();
        Temple temple = Temple.getInstance();
        for (int i = 0; i < 10000; i++){
            temple.testInit(main);
        }

        while(!main.end){
            Console.getInstance().dashboard(main);
            //temple.tick();
        }

        main.summarise();
    }
}
