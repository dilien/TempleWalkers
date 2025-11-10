package corridors;

import temple.CorridorSide;
import base.Console;
import base.Player;
import items.Item;
import rooms.Room;

/**
 * A corridor that can only be used once
 */
public class CollapsingBridge extends Corridor{
    boolean broken = false;

    public CollapsingBridge(Room roomA, Room roomB, CorridorSide sideA, CorridorSide sideB) {
        super(roomA, roomB, sideA, sideB);
    }

    public String getName() {
        if(broken){
            return "Broken Bridge";
        }else{
            return "Old Bridge";
        }
    }

    public String describe() {
        if(broken){
            return "This bridge has broken, and cannot be crossed";
        }else{
            return "The bridge looks quite fragile. Its probably safe to cross at least once.";
        }
    }

    public boolean interact(Player player, Item other) {
        Console console = Console.getInstance();
        if(broken){
            console.displayText("The bridge is broken and cannot be used!");
            return false;
        }else{
            super.interact(player, other);
            broken = true;
            console.displayText("As you cross the bridge, it collapses. You get off just in time.");
            return true;
        }
    }
}
