package rooms;

import base.Interactable;
import base.Inventory;
import base.Player;
import base.Renderable;
import corridors.Corridor;
import items.Item;
import structures.Structure;

import java.util.ArrayList;

//you cannot interact with the rooms themselves,
//you can only interact with the things in them.
public abstract class Room implements Interactable, Renderable {
    //corridors are fixed. New ones cannot be added mid-game
    public Corridor[] corridors;

    public ArrayList<Structure> structs = new ArrayList<>();

    public Inventory inventory;

    public Room(){
        inventory = new Inventory(0);
    }

    public Interactable[] render(int start, boolean render){

        Interactable[] items = inventory.render(0, false);

        int length = 1 + corridors.length + structs.size() + items.length;
        Interactable[] arr = new Interactable[length];

        arr[0] = this;
        System.out.println(start + 1 + ":You are in a " + this.getName());
        start += 1;

        for (int i = 0; i < corridors.length; i++) {
            Interactable item = corridors[i];
            arr[i+start] = item;
            System.out.println(i + start + 1 + ":" + item.getName());
        }
        start += corridors.length;

        for (int i = 0; i < structs.size(); i++) {
            Interactable struct = structs.get(i);
            arr[i+start] = struct;
            System.out.println(i + start + 1 + ":" + struct.getName());
        }
        start += structs.size();

        for (int i = 0; i < items.length; i++) {
            Interactable item = items[i];
            arr[i+start] = item;
            System.out.println(i + start + 1 + ":" + item.getName());
        }
        start += structs.size();

        return arr;
    }

    public boolean interact(Item other){
        Player player = Player.getInstance();
        if(other != null){
            if(player.inventory.removeItem(other)){
                inventory.addItem(other);

                player.displayText("You drop the " + other.getName() + " on the ground.");
                return true;
            }
        }
        //player.displayText("You cannot interact with the " + this.getName());
        return false;
    }

    public abstract void enterRoom();
}
