package base;

import items.Item;
import rooms.Room;

import java.util.Arrays;
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

    public static Integer parseIntOrNull(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    void dashboard(){
        for(int i = 0; i<100; i++){
            System.out.println();
        }

        int index = 0;

        //could be made into a for loop
        int array1Start = index;
        Interactable[] array1 = this.room.render(index, true);
        index += array1.length;

        int array2Start = index;
        Interactable[] array2 = this.inventory.render(index, true);
        index += array2.length;

        Interactable[] interactables = new Interactable[index];
        System.arraycopy(array1, 0, interactables, array1Start, array1.length);
        System.arraycopy(array2, 0, interactables, array2Start, array2.length);

        System.out.println(output );
        output = "";

        String input = scanner.nextLine().strip();
        String[] arr = input.split(" ");
        if(arr.length < 2){
            displayText("not enough arguments, only one command detected.");
            return;
        }

        int obj1index = parseIntOrNull(arr[1]);
        Interactable obj1;
        if(obj1index < 1 || obj1index > interactables.length){
            displayText("'" + obj1index + "' is not a valid index.");
            return;
        }else{
            obj1 = interactables[obj1index-1];
        }

        if(Objects.equals(arr[0], "d")){
            displayText(obj1.describe());
        } else if (Objects.equals(arr[0], "i")) {
            Item obj2 = null;

            if(arr.length > 2){
                int obj2index = parseIntOrNull(arr[2]);
                if(obj2index < 1 || obj2index > interactables.length){
                    displayText("'" + obj2index + "' is not a valid index.");
                    return;
                }else{
                    Interactable inter = interactables[obj2index-1];
                    if(inter instanceof Item){
                        obj2 = (Item) inter;
                    }else{
                        displayText(inter.getName() + " cannot be used as a tool");
                    }
                }
            }

            boolean success = obj1.interact(obj2);

        }
    }
}