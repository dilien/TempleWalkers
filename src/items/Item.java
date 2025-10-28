package items;

import base.Interactable;

public abstract class Item implements Interactable {
    public boolean interact(Item other) {
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