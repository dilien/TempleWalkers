package base;

import items.Gold;
import items.ItemBase;

public class Inventory {
    public Gold gold;
    ItemBase[] items;
    public Inventory(int size){
        gold = new Gold();
        items = new ItemBase[size];
    }

    public boolean addItem(ItemBase item){
        for(int i = 0; i<items.length; i++){
            if(items[i] == null){
                items[i] = item;
                return true;
            }
        }
        return false;
    }

}
