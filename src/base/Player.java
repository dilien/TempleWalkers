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
    public Inventory inventory;
    Scanner scanner;
    public void start(){
        scanner = new Scanner(System.in);
        inventory = new Inventory(5);

        while(true){
            dashboard();
            command();
            tick();
        }
    }

    public void tick(){
        inventory.tick();
    }

    public void enterRoom(Room room){
        this.room = room;
        //trigger any room-specific effects
        room.enterRoom();
    }

    void dashboard(){
        int index = this.room.render(1);
        this.inventory.render(index);
    }

    void command(){
        String input = scanner.nextLine();
        String[] arr = input.split(" ");

    }
}