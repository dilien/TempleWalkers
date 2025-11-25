package testing;

import base.Inventory;
import items.OxygenCanister;

public class InventoryTests {
    public InventoryTests() {
        int passed = 0;
        int total = 0;

        System.out.println("Test 1: Inventory should add item and set parent");
        total++;
        Inventory inv = new Inventory(2);
        OxygenCanister oxygenCanister = new OxygenCanister();
        boolean added = inv.addItem(oxygenCanister);
        if (Test.test(added && oxygenCanister.parent == inv, true)) passed++;

        System.out.println("Test 2: Inventory should reject item if full");
        total++;
        inv.addItem(new OxygenCanister());
        boolean rejected = inv.addItem(new OxygenCanister());
        if (Test.test(rejected, false)) passed++;

        System.out.println("Test 3: Inventory should remove item");
        total++;
        boolean removed = inv.removeItem(oxygenCanister);
        if (Test.test(removed && !inv.items.contains(oxygenCanister), true)) passed++;

        System.out.println("\nscore " + passed + "/" + total + "\n");
    }
}