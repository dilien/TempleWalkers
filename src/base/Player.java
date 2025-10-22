package base;

import rooms.Room;

import java.util.Scanner;

public class Player {
    private Player(){};
    private static Player instance;
    public static Player getInstance(){
        if(instance == null){
            instance = new Player();
        }
        return instance;
    }

    public Room room;
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