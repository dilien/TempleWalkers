import java.util.Scanner;

public class Main {
    private Main() {

    }

    public static void main(String[] args) {
        //create exactly one instance of main
        //so I dont have to spam static everywhere
        //called a singleton
        Main main = new Main();

    }

    Scanner scanner;
    public void start(){
        scanner = new Scanner(System.in);

        while(true){
            dashboard();
            command();
        }
    }

    void dashboard(){
        System.out.println("hello!");
    }

    void command(){
        String input = scanner.nextLine();
        String[] arr = input.split(" ");

    }
}