package rooms;

import base.Interactable;
import corridors.Corridor;

import java.util.ArrayList;

//you cannot interact with the rooms themselves,
//you can only interact with the things in them.
public abstract class Room {
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

    public abstract void enterRoom();
}
