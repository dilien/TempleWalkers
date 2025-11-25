package testing;

import base.Inventory;
import items.Battery;
import items.Item;
import base.Player;
import rooms.Room;
import rooms.SupplyCloset;

public class ItemTests {
    public ItemTests() {
        int passed = 0;
        int total = 0;

        Room room = new SupplyCloset();
        Inventory inventory = room.inventory;

        System.out.println("Test 1: Item should be added to player's inventory");
        total++;
        Player player = new Player();
        Item battery = new Battery();
        battery.parent = inventory;
        battery.interact(player, null);
        if (Test.test(player.getInventory().items.contains(battery), true)) passed++;

        System.out.println("Test 2: Item should be picked up when interacted with");
        total++;
        battery = new Battery();
        battery.parent = inventory;
        battery.interact(player, null);
        if (Test.test(player.getInventory().items.contains(battery), true)) passed++;

        System.out.println("Test 3: Item should not be picked up if inventory is full");
        total++;
        Player fullPlayer = new Player();
        fullPlayer.getInventory().maxSize = 1;
        fullPlayer.getInventory().addItem(new Battery());
        Item otherBattery = new Battery();
        otherBattery.parent = inventory;
        otherBattery.interact(fullPlayer, null);
        if (Test.test(fullPlayer.getInventory().items.contains(otherBattery), false)) passed++;

        System.out.println("Test 4: Item tick should not throw error");
        total++;
        try {
            battery.tick();
            if (Test.test(true, true)) passed++;
        } catch (Exception e) {
            Test.test(false, true);
        }

        System.out.println("\nscore " + passed + "/" + total + "\n");
    }
}