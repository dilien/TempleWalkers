package corridors;

import base.Interactable;
import base.Player;
import items.Item;
import rooms.Room;

public abstract class Corridor implements Interactable {
    Room roomA;
    Room roomB;
    Corridor(Room roomA, Room roomB){
        this.roomA = roomA;
        this.roomB = roomB;
    }

    public Room other(Room a){
        if(a == roomA){
            return roomB;
        }else if(a == roomB){
            return roomA;
        }else{
            throw new java.lang.RuntimeException("other being called on corridor with neither room.");
        }
    }

    public boolean interact(Player player, Item other){
        player.enterRoom(other(player.getRoom()));
        return true;
    }
}
