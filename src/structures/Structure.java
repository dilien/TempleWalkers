package structures;

import base.Interactable;
import items.Item;

/**
 * Structures represent things in the room, like a lever or a painting or a chest.
 * They cannot move, or be destroyed or have any complicated functionality, so they are relatively simple.
 * TODO: extend structures to have more in common with items (i.e a parent) and maybe have a shared parent class for that functionality
 */
abstract public class Structure implements Interactable {
    public boolean interact(Item other) {
        return false;
    }
}
