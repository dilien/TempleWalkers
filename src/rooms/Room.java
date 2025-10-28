package rooms;

import base.Interactable;
import base.Inventory;
import base.Player;
import base.Renderable;
import corridors.Corridor;
import items.Item;
import structures.Structure;

import java.util.ArrayList;
import java.util.Arrays;

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

    public static void write(char[] output, String text, int start){
        System.arraycopy(text.toCharArray(), 0, output, start, text.length());
    }

    public Interactable[] render(int start, boolean render){
        Interactable[] items = inventory.render(0, false);

        int length = 1 + corridors.length + structs.size() + items.length;

        int sectionLeft = 20;
        int rightHeight = 4 + items.length + corridors.length + structs.size();
        int leftHeight = 5;
        int height = Math.max(rightHeight, leftHeight);

        char[][] output = new char[height][100].clone();
        //not quite sure how this works but thanks internet:
        java.util.Arrays.stream(output).forEach(row -> Arrays.fill(row, ' '));

        for(int y = 0; y<height; y++){
            output[y][sectionLeft-1] = '|';
        }

        Interactable[] arr = new Interactable[length];

        arr[0] = this;
        //System.out.println(start + 1 + ":You are in a " + this.getName());
        write(output[0], start + 2 + " : You are in a " + this.getName(), sectionLeft);
        start += 1;

        for (int i = 0; i < corridors.length; i++) {
            Corridor item = corridors[i];
            arr[i+start] = item;
            int plr_index = i + start + 1;
            String text = plr_index + " : A " + item.getName() + " that leads to a " + item.other(this).getName();
            write(output[1 + i], text, sectionLeft);
        }
        start += corridors.length;

        for (int i = 0; i < structs.size(); i++) {
            Interactable struct = structs.get(i);
            arr[i+start] = struct;
            write(output[2 + i + corridors.length], i + start + 1 + " : " + struct.getName(), sectionLeft);
        }
        start += structs.size();

        for (int i = 0; i < items.length; i++) {
            Interactable item = items[i];
            arr[i+start] = item;
            //System.out.println(i + start + 1 + ":" + item.getName());
            write(output[2 + i + corridors.length + structs.size()], i + start + 1 + " : A " + item.getName(), sectionLeft);
        }
        start += structs.size();

        for(char[] str : output){
            System.out.println(str);
        }

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
