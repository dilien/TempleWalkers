package corridors;

import base.Interactable;
import base.Player;
import rooms.Room;

public abstract class Corridor implements Interactable {
    Room roomA;
    Room roomB;

    public boolean interact(Interactable other){
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
