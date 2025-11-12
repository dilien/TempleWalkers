package testing;

import corridors.TempleFrame;
import items.Stick;
import rooms.Room;
import rooms.Chamber;
import temple.PositionSide;
import temple.Side;
import corridors.Corridor;
import base.Player;
import items.Item;

public class RoomTests {
    public RoomTests() {
        int passed = 0;
        int total = 0;

        Room room = new Chamber();

        System.out.println("Test 1: Room should initialize with empty inventory");
        total++;
        boolean initCheck = room.inventory != null;
        if (Test.test(initCheck, true)) passed++;

        System.out.println("Test 2: Room interact should drop item into room inventory");
        total++;
        Player player = new Player();
        Item item = new Stick();
        player.getInventory().addItem(item);
        boolean interacted = room.interact(player, item);
        if (Test.test(interacted && room.inventory.items.contains(item), true)) passed++;

        System.out.println("Test 3: Room getTargetCorridors should return default value");
        total++;
        if (Test.test(room.getTargetCorridors(), 2)) passed++;

        System.out.println("\nscore " + passed + "/" + total + "\n");
    }
}