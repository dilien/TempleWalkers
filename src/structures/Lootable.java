package structures;

import base.Player;
import items.Item;

public abstract class Lootable extends Structure{

    public Item getItem(){
        return null;
    }

    public boolean interact(Player player, Item other) {
        return false;
    }
}
