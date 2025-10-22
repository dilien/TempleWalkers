package rooms;

import base.Interactable;

import java.util.ArrayList;

//you cannot interact with the rooms themselves,
//you can only interact with the things in them.
public abstract class Room {
    public ArrayList<Interactable> items = new ArrayList<>();

    public void render(){
        System.out.println("You are in a room.");
        for (int i = 0; i < items.size(); i++) {
            Interactable item = items.get(i);
            System.out.println(i + ":" + item.getName());
        }
    }
}
