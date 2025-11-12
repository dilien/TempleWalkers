package corridors;

import temple.PositionSide;
import rooms.Room;

/**
 * Is a super simple corridor without any additional functionality.
 */
public class TempleFrame extends Corridor{

    public TempleFrame(Room roomA, Room roomB, PositionSide sideA) {
        super(roomA, roomB, sideA);
    }

    public String getName() {
        return "temple frame";
    }

    public String describe() {
        return "This ornate temple frame leads to another room. It looks quite secure.";
    }
}
