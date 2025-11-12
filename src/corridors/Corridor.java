package corridors;

import temple.PositionSide;
import temple.Temple;
import base.Interactable;
import base.Player;
import items.Item;
import rooms.Room;

/*
This represents a corridor between to rooms, A and B.
 */
public abstract class Corridor implements Interactable {
    public PositionSide side;
    Room roomA;
    Room roomB;
    Corridor(Room roomA, Room roomB, PositionSide side){
        this.roomA = roomA;
        this.roomB = roomB;
        this.side = side;

        int localRoomASide = roomA.globalSideToLocal(side);
        int localRoomBSide = roomB.globalSideToLocal(side);
        System.out.println(side);
        System.out.println(roomA.x + " " + roomA.y);
        System.out.println(roomB.x + " " + roomB.y);
        System.out.println(roomA.getSizeX() + " " + roomA.getSizeY());
        System.out.println(roomB.getSizeX() + " " + roomB.getSizeY());
        System.out.println(side.getForward());
        System.out.println(side.getBackward());
        System.out.println(localRoomASide);
        System.out.println(localRoomBSide);

        roomA.addCorridor(this);
        roomB.addCorridor(this);
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
}
