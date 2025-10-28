package structures;

import base.Console;
import base.Interactable;
import base.Player;
import items.Item;
import items.Stick;
import items.Torch;

public class Brazier extends Structure{
    boolean onfire = false;

    public String getName() {
        return "brazier";
    }

    public String describe() {
        if(onfire){
            return "This metal brazer burns brightly, lighting up the room. It shows no signs of stopping any time soon.";
        }else{
            return "This metal brazer lays dormant. It has oil if you had the tools to re-light it.";
        }
    }

    public boolean interact(Player player, Item other) {
        if(onfire && other instanceof Stick){
            Console.getInstance().displayText("You dip the stick into the oil and it becomes a torch.");
            player.inventory.removeItem(other);
            player.inventory.addItem(new Torch());
            return true;
        }
        if(!onfire && other instanceof Torch){
            Console.getInstance().displayText("You use a torch to light the brazier.");
            onfire = true;
            return true;
        }
        return false;
    }
}
