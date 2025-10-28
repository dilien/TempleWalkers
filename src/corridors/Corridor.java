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

    public boolean interact(Item other){
        Player player = Player.getInstance();
        if(player.room == roomA){
            player.enterRoom(roomB);
        } else if (player.room == roomB) {
            player.enterRoom(roomA);
        }else{
            throw new java.lang.RuntimeException("A corridor that should not be accessed is being interacted with!");
        }
        return true;
    }
}
