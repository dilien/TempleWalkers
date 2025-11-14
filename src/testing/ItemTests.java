package testing;

import items.Item;
import base.Player;
import items.Keycard;

import java.security.Key;

public class ItemTests {
    public ItemTests() {
        int passed = 0;
        int total = 0;

        System.out.println("Test 1: Item should be added to player's inventory");
        total++;
        Player player = new Player();
        Item stick = new Keycard();
        boolean added = player.getInventory().addItem(stick);
        if (Test.test(added && stick.parent == player.getInventory(), true)) passed++;

        System.out.println("Test 2: Item should be picked up when interacted with");
        total++;
        Player otherPlayer = new Player();
        Item droppedStick = new Keycard();
        otherPlayer.getInventory().addItem(droppedStick);
        Item groundStick = new Keycard();
        groundStick.parent = otherPlayer.getInventory();
        boolean pickedUp = groundStick.interact(player, null);
        if (Test.test(pickedUp && player.getInventory().items.contains(groundStick), true)) passed++;

        System.out.println("Test 3: Item should not be picked up if inventory is full");
        total++;
        Player fullPlayer = new Player();
        fullPlayer.getInventory().maxSize = 1;
        fullPlayer.getInventory().addItem(new Keycard());
        Item anotherStick = new Keycard();
        anotherStick.parent = otherPlayer.getInventory();
        boolean failedPickup = anotherStick.interact(fullPlayer, null);
        if (Test.test(failedPickup, false)) passed++;

        System.out.println("Test 4: Item tick should not throw error");
        total++;
        try {
            stick.tick();
            if (Test.test(true, true)) passed++;
        } catch (Exception e) {
            Test.test(false, true);
        }

        System.out.println("\nscore " + passed + "/" + total + "\n");
    }
}