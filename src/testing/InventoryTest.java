package testing;

import base.Inventory;
import items.Item;
import items.Keycard;

public class InventoryTest extends Test {

    public static void main(String[] args) {
        testInventoryCreation();
        testAddingItems();
        testRemovingItems();
        testInventoryCapacity();
    }

    private static void testInventoryCreation() {
        System.out.println("testing constructor");
        Inventory inventory = new Inventory(5);
        test(inventory.maxSize, 5);
        test(inventory.items.size(), 0);
    }

    private static void testAddingItems() {
        System.out.println("testing adding new items");
        Inventory inventory = new Inventory(3);
        Item item1 = new Keycard(0);
        Item item2 = new Keycard(1);

        // Test adding first item
        test(inventory.addItem(item1), true);
        test(inventory.items.size(), 1);
        test(item1.parent, inventory);

        // Test adding second item
        test(inventory.addItem(item2), true);
        test(inventory.items.size(), 2);
        test(item2.parent, inventory);
    }

    private static void testRemovingItems() {
        System.out.println("testing removing items");
        Inventory inventory = new Inventory(3);
        Item item1 = new Keycard(0);
        Item item2 = new Keycard(1);

        inventory.addItem(item1);
        inventory.addItem(item2);

        // Test removing existing item
        test(inventory.removeItem(item1), true);
        test(inventory.items.size(), 1);

        // Test removing non-existent item
        Item item3 = new Keycard(2);
        test(inventory.removeItem(item3), false);
        test(inventory.items.size(), 1);
    }

    private static void testInventoryCapacity() {
        System.out.println("testing capacity");
        Inventory inventory = new Inventory(2);
        Item item1 = new Keycard(0);
        Item item2 = new Keycard(1);
        Item item3 = new Keycard(2);

        // Fill inventory to capacity
        test(inventory.addItem(item1), true);
        test(inventory.addItem(item2), true);

        // Try to exceed capacity
        test(inventory.addItem(item3), false);
        test(inventory.items.size(), 2);

        // Test unlimited capacity
        Inventory unlimitedInventory = new Inventory(0);
        test(unlimitedInventory.addItem(item1), true);
        test(unlimitedInventory.addItem(item2), true);
        test(unlimitedInventory.addItem(item3), true);
    }
}