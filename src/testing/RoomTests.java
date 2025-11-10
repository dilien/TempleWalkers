package testing;

import corridors.TempleFrame;
import items.Stick;
import rooms.Room;
import rooms.Chamber;
import temple.CorridorSide;
import temple.Side;
import corridors.Corridor;
import base.Player;
import items.Item;
import temple.TempleGenerator;
import testing.Test;

public class RoomTests {
    public RoomTests() {
        int passed = 0;
        int total = 0;

        Room room = new Chamber();

        System.out.println("Test 1: Room should initialize with empty inventory and corridor list");
        total++;
        boolean initCheck = room.inventory != null && room.corridorsTemp != null;
        if (Test.test(initCheck, true)) passed++;

        System.out.println("Test 2: Room finalise should convert corridor list to array");
        total++;
        Corridor dummyCorridor = new TempleFrame(room, room, new CorridorSide(0, Side.North), new CorridorSide(0, Side.South)) {};
        room.finalise();
        //we set it to two because the corridor is connecting to itself
        if (Test.test(room.corridors.length == 2, true)) passed++;

        System.out.println("Test 3: Room interact should drop item into room inventory");
        total++;
        Player player = new Player();
        Item item = new Stick();
        player.getInventory().addItem(item);
        boolean interacted = room.interact(player, item);
        if (Test.test(interacted && room.inventory.items.contains(item), true)) passed++;

        System.out.println("Test 4: Room getTargetCorridors should return default value");
        total++;
        if (Test.test(room.getTargetCorridors(), 2)) passed++;

        System.out.println("\nscore " + passed + "/" + total + "\n");
    }
}