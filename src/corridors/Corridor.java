package corridors;

import temple.PositionSide;
import temple.Temple;
import base.Interactable;
import base.Player;
import items.Item;
import rooms.Room;

/**
 * Represents a corridor between two rooms
 * has an absolute position within the grid, as well as the two rooms it is connected to.
 */
public abstract class Corridor implements Interactable {
    public final PositionSide side;
    final Room roomA;
    final Room roomB;

    /**
     * Creates a new corridor. It handles telling the rooms about it.
     * @param roomA - one of the rooms
     * @param roomB - the other room
     * @param side - the global position of the corridor
     */
    Corridor(Room roomA, Room roomB, PositionSide side){
        this.roomA = roomA;
        this.roomB = roomB;
        this.side = side;

        roomA.addCorridor(this);
        roomB.addCorridor(this);
    }

    /**
     * will fail if parameter A is neither of the rooms
     * @param a - one room
     * @return - the other room connected
     */
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
