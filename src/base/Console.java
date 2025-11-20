package base;

import corridors.Corridor;
import items.Item;
import temple.PositionSide;
import temple.Temple;

import java.util.Objects;
import java.util.Scanner;

/**
 This class represents the console and handles taking user input, as well as directing the other classes to display information when needed.
 It is a singleton.
 */
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
    //Display text to the user on the next dashboard step
    public void displayText(String text){
        output += "\n" + text;
    }

    /**
     * This function prevents full on crashes when a non-int is parsed.
     * @param value - string to parse
     * @return integer or null
     */
    public static int parseIntOrZero(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    PositionSide lastCorridor;

    /**
     * This function prints out the 'view' of the player, including the room and their inventory
     * It yields until the player chooses an action
     * @param player - the player to generate the dashboard for
     */
    public void dashboard(Player player){
        for(int i = 0; i<5; i++){
            System.out.println();
        }

        int index = 0;

        //could be made into a for loop
        int array1Start = index;
        player.room.render(index, lastCorridor);
        Interactable[] array1 = player.room.getAll();
        index += array1.length;
        System.out.println("----------------------------------------------------------------------------------");

        int array2Start = index;
        player.getInventory().render(index);
        Interactable[] array2 = player.getInventory().getAll();
        index += array2.length;

        //interactables is the list of things the player can interact with
        //when 2:corridor is printed, at the index 1 is the corridor instance
        Interactable[] interactables = new Interactable[index];
        System.arraycopy(array1, 0, interactables, array1Start, array1.length);
        System.arraycopy(array2, 0, interactables, array2Start, array2.length);

        displayText("Oxygen Left: " + player.getOxygen());
        System.out.println(output );
        output = "";

        //rest of the function is parsing user commands
        //if it is incorrect, we re-print the entire dashboard
        String input = scanner.nextLine().strip();
        String[] arr = input.split(" ");
        if(arr.length < 2){
            displayText("not enough arguments, only one command detected.");
            return;
        }

        int obj1index = parseIntOrZero(arr[1]);
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
                int obj2index = parseIntOrZero(arr[2]);
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
            if(obj1 == null){
                displayText("You try to go through it, but bump your head into the wall. Oops.");
                Temple.getInstance().tick();
                return;
            }

            if(obj2 != null){
                displayText("Using " + obj1.getName() + " with " + obj2.getName());
            }
            boolean success = obj1.interact(player, obj2);
            if(!success){
                displayText("You cannot interact with " + obj1.getName() + " in this way.");
            }else{
                if(obj1 instanceof Corridor){
                    lastCorridor = ((Corridor) obj1).side;
                }
            }
        }
    }
}
