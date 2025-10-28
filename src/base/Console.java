package base;

import items.Item;

import java.util.Objects;
import java.util.Scanner;

public class Console {
    Scanner scanner;
    private Console(){
        scanner = new Scanner(System.in);
    }
    static Console self;
    public static Console getInstance(){
        if(self == null){
            self = new Console();
        }
        return self;
    }

    String output = "";
    public void displayText(String text){
        output += "\n" + text;
    }

    public static Integer parseIntOrNull(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean dashboard(Player player){
        for(int i = 0; i<100; i++){
            System.out.println();
        }

        int index = 0;

        //could be made into a for loop
        int array1Start = index;
        Interactable[] array1 = player.room.render(index, true);
        index += array1.length;

        System.out.println("----------------------------------------------------------------------------------");

        int array2Start = index;
        Interactable[] array2 = player.getInventory().render(index, true);
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
            return false;
        }

        int obj1index = parseIntOrNull(arr[1]);
        Interactable obj1;
        if(obj1index < 1 || obj1index > interactables.length){
            displayText("'" + obj1index + "' is not a valid index.");
            return false;
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
                    return false;
                }else{
                    Interactable inter = interactables[obj2index-1];
                    if(inter instanceof Item){
                        obj2 = (Item) inter;
                    }else{
                        displayText(inter.getName() + " cannot be used as a tool");
                    }
                }
            }

            if(obj2 != null){
                displayText("Using " + obj1.getName() + " with " + obj2.getName());
            }
            boolean success = obj1.interact(player, obj2);
            if(!success){
                displayText("You cannot interact with " + obj1.getName() + " in this way.");
            }else{
                return true;
            }
        }
        return false;
    }
}
