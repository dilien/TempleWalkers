package corridors;

import rooms.Room;
import temple.PositionSide;

//we want the hidden corridor to not be interactable in any way (other than using it as a corridor)
//this way the player basically thinks it is not a 'thing' and the right bar is less cluttered.
public class HiddenCorridor extends Corridor{
    public HiddenCorridor(Room roomA, Room roomB, PositionSide side) {
        super(roomA, roomB, side);
    }

    public String getName() {
        return null;
    }

    public String describe() {
        return null;
    }
}
