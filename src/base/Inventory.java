package base;

import items.Gold;
import items.Item;

public class Inventory implements Renderable {
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

    public void tick(){
        for(int i = 0; i<items.length; i++){
            if(items[i] != null){
                items[i].tick();
            }
        }
    }

    public Interactable[] render(int index, boolean display) {

        //todo: have array be length of items, rather than length of whole inventory
        Interactable[] arr = new Interactable[items.length];

        for (int i = 0; i < items.length; i++) {
            Interactable item = items[i];
            if(item == null){break;}
            System.out.println(index + i + ":" + item.getName());
        }

        return arr;
    }
}
