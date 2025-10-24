package items;

import base.Interactable;

public abstract class Item implements Interactable {
    public boolean interact(Item other) {
        return false;
    }
    public void tick(){

    };
}
