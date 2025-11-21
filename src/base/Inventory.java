package base;

import items.Item;

import java.util.ArrayList;

/**
Represents a container of items, and is used by the player and every room.
 */
public class Inventory {
    public int maxSize = 0;
    public ArrayList<Item> items;
    public Inventory(int size){
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

    public Item[] getAll(){
        return items.toArray(new Item[0]);
    }

    public void render(int index) {
        System.out.println("Inventory contents (" + items.size() + "/" + maxSize + "): ");
        for (int i = 0; i<items.size(); i++){
            Item item = items.get(i);
            System.out.println(index + i + 1 + ":" + item.getName());
        }
    }
}
