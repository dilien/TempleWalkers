package rooms;

import Temple.CorridorSide;
import Temple.Side;
import base.*;
import corridors.Corridor;
import items.Item;
import structures.Structure;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * The level is made up of rooms
 * Each room contains corridors, structures and items.
 * Rooms themselves can be interacted with, to drop/pickup items.
 */
public abstract class Room implements Interactable, Renderable {
    //represents position on the map grid
    public int x = -100;
    public int y = -100;
    public int getSizeX(){
        return 1;
    }
    public int getSizeY(){
        return 1;
    }

    //corridors temp is used during generation, and is converted to an array after.
    public ArrayList<Corridor> corridorsTemp;
    public Corridor[] corridors;

    public ArrayList<Structure> structs = new ArrayList<>();

    public Inventory inventory;

    public Room(){
        inventory = new Inventory(0);
        corridorsTemp = new ArrayList<Corridor>();
    }

    /**
     * A utility function to write text in a string array
     * @param output - string array to write to
     * @param text - text to write
     * @param start - index to start writing at
     */
    public static void write(char[] output, String text, int start){
        System.arraycopy(text.toCharArray(), 0, output, start, text.length());
    }

    /**
     * A function to add a corridor to the top left display (the room top-down view)
     * @param output - grid of characters to write to
     * @param side - CorridorSide that represents the corridor location
     * @param character - character to write (usually a number for the corridor index)
     */
    public void addCorridor(char[][] output, CorridorSide side, char character){
        int x = 1;
        int y = 1;
        if(side == null){
            return;
        }
        switch (side.side()){
            case Side.North -> output[(x + side.x()) * 2][(y) * 3 - 2] = character;
            case Side.South -> output[(x + side.x()) * 2][(y + this.getSizeY() - 1) * 3 + 2] = character;
            case Side.West -> output[(x) * 2 - 2][(y + side.x()) * 3] = character;
            case Side.East -> output[(x + this.getSizeX() - 1) * 2 + 1][(y + side.x()) * 3] = character;
        }
    }

    /**
     * Generates a list of items that can be interacted with, which are all things in the room.
     * It also displays the info nicely.
     *
     * @param start - index to start with when displaying the interactables
     * @param render - should the items be printed to the console?
     * @return - A list of items that can be interacted with.
     */
    public Interactable[] render(int start, boolean render){
        Interactable[] items = inventory.render(0, false);

        int length = 1 + corridors.length + structs.size() + items.length;

        int sectionLeft = this.getSizeY()*3+4;
        int rightHeight = items.length + corridors.length + structs.size();
        int leftHeight = 2 + this.getSizeX() * 2;
        int height = Math.max(rightHeight, leftHeight);

        char[][] output = new char[height][100].clone();
        //not quite sure how this works but thanks internet:
        java.util.Arrays.stream(output).forEach(row -> Arrays.fill(row, ' '));

        for(int x = 0; x<this.getSizeX()*2+2; x++){
            for(int y = 1; y<this.getSizeY()*3+3; y++){
                if(x == 0 || y == 1 || x == this.getSizeX()*2 +1 || y == this.getSizeY()*3 + 2){
                    output[x][y] = '█';
                }else{
                    output[x][y] = '.';
                }
            }
        }

        Interactable[] arr = new Interactable[length];

        arr[0] = this;
        write(output[0], start + 1 + " : You are in a " + this.getName(), sectionLeft);
        start += 1;

        for (int i = 0; i < corridors.length; i++) {
            Corridor item = corridors[i];
            arr[i+start] = item;
            int plr_index = i + start + 1;
            //"A {item-name} that leads to a {other-item-name}"
            //"Je {other-item-name} est led to by {item-name}"
            String text = plr_index + " : A " + item.getName() + " that leads to a " + item.other(this).getName();
            write(output[1 + i], text, sectionLeft);

            addCorridor(output, item.getSide(this), String.valueOf(plr_index).charAt(0));
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

        if(render){
            for(char[] str : output){
                System.out.println(str);
            }
        }

        return arr;
    }

    public boolean interact(Player player, Item other){
        if(other != null){
            if(player.getInventory().removeItem(other)){
                inventory.addItem(other);

                Console.getInstance().displayText("You drop the " + other.getName() + " on the ground.");
                return true;
            }
        }
        //player.displayText("You cannot interact with the " + this.getName());
        return false;
    }

    public abstract void enterRoom();

    //really less of a target and more of a minimum
    //Some rooms, especially bigger rooms with a lot of neighbours will typically have more.
    public int getTargetCorridors(){
        return 2;
    }

    /**
     * Converts the temporary corridor list into the final corridor array.
     */
    public void finalise(){
        corridors = corridorsTemp.toArray(new Corridor[0]);
        corridorsTemp = null;
    }
}
