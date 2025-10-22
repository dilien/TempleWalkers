package items;

import base.Interactable;

public abstract class Item implements Interactable {

    public String getName(){
        return "ItemBase";
    }

    public String getDescription() {
        return "This is the base item. You should not be seeing this. Oops.";
    }

    public boolean interact(Interactable other){
        return false;
    }
}
