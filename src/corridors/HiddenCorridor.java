package corridors;

import rooms.Room;
import temple.PositionSide;

/**
 * The hidden corridor has a name of NULL to prevent it displaying.
 *
 */
public class HiddenCorridor extends Corridor {
    /**
     * Creates a new corridor. It handles telling the rooms about it.
     *
     * @param roomA - one of the rooms
     * @param roomB - the other room
     * @param side  - the global position of the corridor
     */
    public HiddenCorridor(Room roomA, Room roomB, PositionSide side) {
        super(roomA, roomB, side);
    }

    public String getName() {
        return null;
    }

    public String describe() {
        return "Its a door that you can go through. It serves its purpose faithfully. Can you believe it isn't even credited on the sidebar?";
    }
}
