package base;

import rooms.Room;

import java.util.Scanner;

public class Player {
    private Player(){
        scanner = new Scanner(System.in);
        inventory = new Inventory(5);
    };
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

        while(true){
            dashboard();
            command();
            tick();
        }
    }

    String output = "";

    /**
     * Adds text to the output that will be displayed in the next display tick.
     * @param text
     */
    public void displayText(String text){
        output += "\n" + text;
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

        System.out.println(output );
        output = "";
    }

    void command(){
        String input = scanner.nextLine();
        String[] arr = input.split(" ");

    }
}