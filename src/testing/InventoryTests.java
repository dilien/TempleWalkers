package testing;

import base.Inventory;
import items.Keycard;

public class InventoryTests {
    public InventoryTests() {
        int passed = 0;
        int total = 0;

        System.out.println("Test 1: Inventory should add item and set parent");
        total++;
        Inventory inv = new Inventory(2);
        Keycard stick = new Keycard();
        boolean added = inv.addItem(stick);
        if (Test.test(added && stick.parent == inv, true)) passed++;

        System.out.println("Test 2: Inventory should reject item if full");
        total++;
        inv.addItem(new Keycard());
        boolean rejected = inv.addItem(new Keycard());
        if (Test.test(rejected, false)) passed++;

        System.out.println("Test 3: Inventory should remove item");
        total++;
        boolean removed = inv.removeItem(stick);
        if (Test.test(removed && !inv.items.contains(stick), true)) passed++;

        System.out.println("\nscore " + passed + "/" + total + "\n");
    }
}