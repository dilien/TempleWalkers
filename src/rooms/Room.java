package rooms;

import base.Interactable;
import base.Player;
import corridors.Corridor;
import items.Item;

import java.util.ArrayList;

//you cannot interact with the rooms themselves,
//you can only interact with the things in them.
public abstract class Room implements Interactable{
    public Corridor[] corridors;

    public ArrayList<Interactable> items = new ArrayList<>();

    public int render(){
        System.out.println("You are in a room.");
        for (int i = 0; i < corridors.length; i++) {
            Interactable item = corridors[i];
            System.out.println(i + ":" + item.getName());
        }
        int offset = corridors.length;

        for (int i = 0; i < items.size(); i++) {
            Interactable item = items.get(i);
            System.out.println(i + offset + ":" + item.getName());
        }

        return offset + items.size();
    }

    public boolean interact(Item other){
        Player player = Player.getInstance();
        if(other != null){
            if(player.inventory.removeItem(other)){
                items.add(other);
                return true;
            }
        }
        return false;
    }

    public abstract void enterRoom();
}
