package corridors;

import items.Item;
import rooms.Room;

public class TempleFrame extends Corridor{

    public TempleFrame(Room roomA, Room roomB) {
        super(roomA, roomB);
    }

    public String getName() {
        return "temple frame";
    }

    public String describe() {
        return "This ornate temple frame leads to another room. It looks quite secure.";
    }
}
