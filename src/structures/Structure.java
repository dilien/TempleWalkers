package structures;

import base.Interactable;
import items.Item;

abstract public class Structure implements Interactable {
    public boolean interact(Item other) {
        return false;
    }
}
