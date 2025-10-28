package rooms;

import base.Interactable;
import base.Player;
import base.Renderable;
import corridors.Corridor;
import items.Item;

import java.util.ArrayList;

//you cannot interact with the rooms themselves,
//you can only interact with the things in them.
public abstract class Room implements Interactable, Renderable {
    //corridors are fixed. New ones cannot be added mid-game
    public Corridor[] corridors;

    //items can be added as the player interacts with them, it is dynamic length.
    public ArrayList<Interactable> items = new ArrayList<>();

    public Interactable[] render(int start, boolean render){
        int length = 1 + corridors.length + items.size();
        Interactable[] arr = new Interactable[length];
        arr[0] = this;

        System.out.println(start + ":You are in a " + this.getName());
        start += 1;
        for (int i = 0; i < corridors.length; i++) {
            Interactable item = corridors[i];
            arr[i+1] = item;
            System.out.println(i + start + 1 + ":" + item.getName());
        }
        start += corridors.length;

        for (int i = 0; i < items.size(); i++) {
            Interactable item = items.get(i);
            arr[i+1+corridors.length] = item;
            System.out.println(i + start + 1 + ":" + item.getName());
        }

        return arr;
    }

    public boolean interact(Item other){
        Player player = Player.getInstance();
        if(other != null){
            if(player.inventory.removeItem(other)){
                items.add(other);

                player.displayText("You drop the " + other.getName() + " on the ground.");
                return true;
            }
        }
        player.displayText("You cannot interact with the " + this.getName());
        return false;
    }

    public abstract void enterRoom();
}
