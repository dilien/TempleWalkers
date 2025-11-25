package structures;

import base.Console;
import base.Player;
import items.Item;
import items.Keycard;

public class Elevator extends Structure{
    public int level = 0;

    public String getName() {
        return "Surface Elevator number " + level;
    }

    public String describe() {
        return "This button calls an elevator, but requires a keycard of level " + level;
    }

    public boolean interact(Player player, Item other){
        Console console = Console.getInstance();
        if(other == null){
            return false;
        }
        if (other instanceof Keycard) {
            if(((Keycard) other).level >= level){
                //win
                Console.getInstance().displayText("\n You enter the elevator and head back up to the surface.");
                player.end = true;
            }else{
                console.displayText("The elevator beeps. The key-card is not the right one.");
            }
            return true;
        }
        console.displayText("You need a level " + level + " keycard to use the elevator");
        return true;
    }
}
