package base;

import items.Item;

import java.util.ArrayList;

/**
 * Represents a container of items, and is used by the player and every room.
 */
public class Inventory {
    public int maxSize;
    public final ArrayList<Item> items;

    /**
     *
     * @param size - maximum size of the inventory
     */
    public Inventory(int size) {
        items = new ArrayList<>();
        maxSize = size;
    }

    /**
     * Tries to add an item to an inventory. Could fail if the inventory is full, so have a backup feature to not destroy the item.
     *
     * @param item - the item to add
     * @return - true if it was a success
     */
    public boolean addItem(Item item) {
        if (maxSize != 0 && items.size() >= maxSize) {
            return false;
        }
        items.add(item);
        item.parent = this;
        return true;
    }

    /**
     * Removes an item from this inventory
     *
     * @param item - the item to remove
     * @return - true if the item was found, false if not
     */
    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    /**
     * Prints the contents of the inventory to the screen
     *
     * @param index - the index to start the first item at (1 in a default case)
     */
    public void render(int index) {
        System.out.println("Inventory contents (" + items.size() + "/" + maxSize + "): ");
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.println(index + i + 1 + ":" + item.getName());
        }
    }
}
