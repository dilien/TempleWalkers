package base;

import items.Gold;
import items.Item;

public class Inventory {
    public Gold gold;
    Item[] items;
    public Inventory(int size){
        gold = new Gold();
        items = new Item[size];
    }

    public boolean addItem(Item item){
        for(int i = 0; i<items.length; i++){
            if(items[i] == null){
                items[i] = item;
                return true;
            }
        }
        return false;
    }

    public boolean removeItem(Item item){
        for(int i = 0; i<items.length; i++){
            if(items[i] == item){
                items[i] = null;
                return true;
            }
        }
        return false;
    }

    public void render(int startIndex){
        for (int i = 0; i < items.length; i++) {
            Interactable item = items[i];
            if(item == null){return;}
            System.out.println(startIndex + ":" + item.getName());
        }
    }

}
