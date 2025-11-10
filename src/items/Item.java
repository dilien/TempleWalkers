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
        Temple.getInstance().tickEvent.listen((_void)->tick());
    }

    public boolean interact(Player player, Item other) {
        //we should make them pick up the item before any other checks can be made
        if(parent != player.getInventory()){
            Inventory parent = this.parent;
            if(player.getInventory().addItem(this)){
                parent.removeItem(this);
                Console.getInstance().displayText("You pick up the " + this.getName());
                return true;
            }else{
                Console.getInstance().displayText("You cannot use this until you pick it up, but your hands are full at the moment.");
                return false;
            }
        }
        return false;
    }

    public void tick(){

    };
}

/*
Item notes:

Items can be replaced/converted into other items, and the way to do that is quite complicated.
We cannot change the class of the object from within the object, so we have to replace it.
However, some items are in a room dropped on the floor, and some are in the players inventory, and possibly even chests having a separate inventory.
This is why I have decided that rooms will contain a inventory class (without a max count) as well as display being false.
 */