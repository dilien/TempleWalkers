package items;

import base.Interactable;

public class ItemBase implements Interactable {
    public ItemBase(){

    }
    public String getName(){
        return "items.ItemBase";
    }

    public String getDescription() {
        return "This is the base item. You should not be seeing this. Oops.";
    }

    public boolean interact(Interactable other){
        return false;
    }
}
