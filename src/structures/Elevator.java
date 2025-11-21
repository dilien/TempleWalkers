package structures;

import base.Console;
import base.Player;
import items.Item;
import items.Keycard;
import rooms.Room;
import temple.PositionSide;
import temple.Temple;

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

            }else{
                console.displayText("The elevator beeps. The key-card is not the right one.");
            }
            return true;
        }
        return false;
    }
}
