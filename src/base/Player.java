package base;

import rooms.Room;

import java.util.Objects;
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
        int index = 1;
        Interactable[][] array = new Interactable[2][];

        array[0] = this.room.render(index, true);
        index += array[0].length;
        array[1] = this.inventory.render(index, true);
        index += array[1].length;

        System.out.println(output );
        output = "";

        Interactable[] interactables = new Interactable[index];
        interactables[0] = room;



        String input = scanner.nextLine();
        String[] arr = input.split(" ");

        if(arr.length < 2){return;}
        if(Objects.equals(arr[0], "d")){

        }
    }
}