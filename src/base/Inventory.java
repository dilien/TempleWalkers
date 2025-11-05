package base;

import items.Gold;
import items.Item;

import java.util.ArrayList;

//Represents a container of items, and the player and each room has one.
public class Inventory implements Renderable {
    //TODO: Not every inventory needs gold, only the player one. Maybe it should be an item in the items instead.
    public Gold gold;
    public int maxSize = 0;
    public ArrayList<Item> items;
    public Inventory(int size){
        gold = new Gold();
        items = new ArrayList<>();
        maxSize = size;
    }

    public boolean addItem(Item item){
        if(maxSize != 0 && items.size() >= maxSize){
            return false;
        }
        items.add(item);
        item.parent = this;
        return true;
    }

    public boolean removeItem(Item item){
        return items.remove(item);
    }

    public void tick(){
        for(Item item : items){
            item.tick();
        }
    }

    public Interactable[] render(int index, boolean display) {
        Interactable[] arr = items.toArray(new Item[0]);
        if(!display){return arr;}

        System.out.println("Inventory contents (" + items.size() + "/" + maxSize + "): ");
        for (int i = 0; i<items.size(); i++){
            Item item = items.get(i);
            System.out.println(index + i + 1 + ":" + item.getName());
        }

        return arr;
    }
}
