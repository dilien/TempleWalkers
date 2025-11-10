package corridors;

import temple.CorridorSide;
import rooms.Room;

/**
 * Is a super simple corridor without any additional functionality.
 */
public class TempleFrame extends Corridor{

    public TempleFrame(Room roomA, Room roomB, CorridorSide sideA, CorridorSide sideB) {
        super(roomA, roomB, sideA, sideB);
    }

    public String getName() {
        return "temple frame";
    }

    public String describe() {
        return "This ornate temple frame leads to another room. It looks quite secure.";
    }
}
