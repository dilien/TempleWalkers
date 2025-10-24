package corridors;

import base.Interactable;
import base.Player;
import items.Item;
import rooms.Room;

public abstract class Corridor implements Interactable {
    Room roomA;
    Room roomB;

    public boolean interact(Item other){
        Player player = Player.getInstance();
        if(player.room == roomA){
            player.enterRoom(roomA);
        } else if (player.room == roomB) {
            player.enterRoom(roomB);
        }else{
            //raise error
        }
        return true;
    }
}
