package structures;

import base.Interactable;
import items.Item;

//Structures represent things in the room, like a lever or a painting or a chest.
abstract public class Structure implements Interactable {
    public boolean interact(Item other) {
        return false;
    }
}
