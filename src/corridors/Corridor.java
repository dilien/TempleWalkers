package corridors;

import temple.CorridorSide;
import temple.Temple;
import base.Interactable;
import base.Player;
import items.Item;
import rooms.Room;

/*
This represents a corridor between to rooms, A and B.
 */
public abstract class Corridor implements Interactable {
    CorridorSide sideA;
    CorridorSide sideB;
    Room roomA;
    Room roomB;
    Corridor(Room roomA, Room roomB, CorridorSide sideA, CorridorSide sideB){
        this.roomA = roomA;
        this.roomB = roomB;
        this.sideA = sideA;
        this.sideB = sideB;
        roomA.corridorsTemp.add(this);
        roomB.corridorsTemp.add(this);
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
        Temple temple = Temple.getInstance();
        temple.tick();
        return true;
    }

    public CorridorSide getSide(Room a){
        if(a == roomA){
            return sideA;
        }else if(a == roomB){
            return sideB;
        }else{
            throw new java.lang.Error("this is very bad");
        }
    }
}
