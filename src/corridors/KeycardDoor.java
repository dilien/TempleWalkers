package corridors;

import base.Console;
import base.Player;
import items.Item;
import items.Keycard;
import rooms.Room;
import temple.PositionSide;
import temple.Temple;

public class KeycardDoor extends Corridor{
    boolean open = true;
    public int level = 0;

    public KeycardDoor(Room roomA, Room roomB, PositionSide sideA) {
        super(roomA, roomB, sideA);
    }

    public void setLevel(int level){
        this.level = level;
        open = level == 0;
    }

    public String getName() {
        if(open){
            return "unlocked key-card gate";
        }
        return "Locked key-card gate";
    }

    public String describe() {
        if(open){
            return "This key-card gate gate is now open.";
        }
        return "This gate is locked and needs a level " + level + " key-card to open";
    }

    public boolean interact(Player player, Item other){
        Temple temple = Temple.getInstance();
        Console console = Console.getInstance();
        if(other == null){
            if(open){
                return super.interact(player, null);
            }else{
                console.displayText("This door is locked.");
                return false;
            }
        } else if (other instanceof Keycard) {
            if(((Keycard) other).level >= level){
                open = !open;
                if(open){
                    console.displayText("This door opens.");
                }else{
                    console.displayText("This door closes");
                }
            }else{
                console.displayText("The door beeps. The key-card is not the right one.");
            }
            return true;
        }
        return false;
    }
}
