package items;

import temple.Temple;
import base.*;

//TODO: Item is not being deleted when it is removed from its inventory
//I am not sure if this is to do with the event listener, or if it is happening anyway
//The Intellij debugger isn't working so I cannot check individual objects for now. Waiting on that to be fixed, so I can find the root issue.

/**
 * This represents an item contained in an inventory.
 */
public abstract class Item implements Interactable {
    public Inventory parent;

    public Item() {
        Temple.getInstance().tickEvent.listen((_void) -> tick());
    }

    /**
     * @return -
     * true if the item needs to be picked up,
     * false if the item is already picked up/or inventory full
     */
    public boolean tryPickup(Player player) {
        if (parent != player.getInventory()) {
            Inventory parent = this.parent;
            if (player.getInventory().addItem(this)) {
                if (parent != null) {
                    parent.removeItem(this);
                }
                Console.getInstance().displayText("You pick up the " + this.getName());
                return true;
            } else {
                Console.getInstance().displayText("You cannot pick this up because your hands are full at the moment.");
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean interact(Player player, Item other) {
        //we should make them pick up the item before any other checks can be made
        if (parent != player.getInventory()) {
            tryPickup(player);
            return true;
        }
        return false;
    }

    public void tick() {
        //empty by default, other classes can override if they want
    }

    /**
     * Deletes an item from the game, and therefore from its parent inventory.
     */
    public void delete() {
        if (parent == null) {
            return;
        }
        parent.removeItem(this);
        //hopefully garbage manager deals with the rest...
    }
}