package rooms;

import temple.PositionSide;
import temple.Side;
import base.*;
import corridors.Corridor;
import items.Item;
import structures.Structure;
import temple.Temple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/**
 * The level is made up of rooms
 * Each room contains corridors, structures and items.
 * Rooms themselves can be interacted with, to drop/pickup items.
 */
public abstract class Room implements Interactable {
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
    public Corridor[] corridors;

    public ArrayList<Structure> structs = new ArrayList<>();

    public Inventory inventory;

    public Room(){
        inventory = new Inventory(0);
        int perimiter = (getSizeX() + getSizeY()) * 2;
        corridors = new Corridor[perimiter];
    }

    public void generateCorridors(){
        int p_h = getSizeX() + getSizeY(); //perimiter half
        int perimiter = p_h * 2;
        globalDirections = new PositionSide[perimiter];
        for(int i = 0; i<2; i++){
            for(int x = 0; x<getSizeX(); x++){
                globalDirections[i*p_h+x] = new PositionSide(new Vector2(x+this.x, y-1 + getSizeY() * i).wrap(), false);
            }
            for(int y = 0; y<getSizeY(); y++){
                globalDirections[i*p_h+getSizeX()+y] = new PositionSide(new Vector2(x-1 + getSizeX() * (1-i), y+this.y).wrap(), true);
            }
        }
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

    public PositionSide[] globalDirections;

    public int globalSideToLocal(PositionSide globalSide){
        for(int i = 0; i<globalDirections.length; i++){
            if(globalDirections[i].equals(globalSide)){
                return  i;
            }
        }
        return -1;
    }

    public PositionSide localSideToGlobal(int localSide){
        return globalDirections[localSide];
    }

    /**
     * Links a corridor to a room
     * @param corridor - corridor to add
     * @return - true/false if failed, could fail if the side is already occupied.
     */
    public boolean addCorridor(Corridor corridor){
        int localSide = globalSideToLocal(corridor.side);
        if(corridors[localSide] == null){
            corridors[localSide] = corridor;
            return true;
        }
        return false;
    }

    /**
     * A function to add a corridor to the top left display (the room top-down view)
     * @param output - grid of characters to write to
     * @param localSide - CorridorSide that represents the corridor location
     * @param character - character to write (usually a number for the corridor index)
     */
    void addCorridorToDisplay(char[][] output, int localSide, char character){
        PositionSide global = this.localSideToGlobal(localSide);
        Vector2 v = global.pos();
        int x = 1 + v.x() - this.x;
        int y = 1 + v.y() - this.y;

        if(global.right()){
            output[x*3][y*2 + 1] = character;
        }else{
            output[x*3 + 1][y*2] = character;
        }
    }

    /**
     * Generates a list of items that can be interacted with, which are all things in the room.
     *
     * @return - A list of items that can be interacted with.
     */
    public Interactable[] getAll(){
        Interactable[] items = inventory.getAll();

        int length = 1 + corridors.length + structs.size() + items.length;
        Interactable[] arr = new Interactable[length];
        arr[0] = this;

        int start = 1;
        for (int i = 0; i < corridors.length; i++) {
            Corridor item = corridors[i];
            arr[i+start] = item;
        }

        start += corridors.length;
        for (int i = 0; i < structs.size(); i++) {
            Structure struct = structs.get(i);
            arr[i+start] = struct;
        }

        start += structs.size();
        for (int i = 0; i < items.length; i++) {
            Interactable item = items[i];
            arr[i+start] = item;
        }

        return arr;
    }

    /**
     * Prints a view of the room for the user.
     *
     * @param start - index to start with when displaying the interactables
     */
    public void render(int start){
        Temple temple = Temple.getInstance();

        Interactable[] items = inventory.getAll();

        int length = 1 + corridors.length + structs.size() + items.length;

        int sectionLeft = this.getSizeY()*3+4;
        int rightHeight = items.length + corridors.length + structs.size() + 1;
        int leftHeight = 2 + this.getSizeX() * 2;
        int height = Math.max(rightHeight, leftHeight);

        char[][] output = new char[height][100].clone();
        //not quite sure how this works but thanks internet:
        java.util.Arrays.stream(output).forEach(row -> Arrays.fill(row, ' '));

        for(int x = 0; x<this.getSizeX()*2+2; x++){
            for(int y = 1; y<this.getSizeY()*3+3; y++){
                if(x == 0 || y == 1 || x == this.getSizeX()*2 +1 || y == this.getSizeY()*3 + 2){
                    output[x][y] = temple.dark?'?':'█';
                }else{
                    output[x][y] = '.';
                }
            }
        }
        if(temple.dark){
            write(output[0], start + 1 + " : You are in a ????", sectionLeft);
        }else{
            write(output[0], start + 1 + " : You are in a " + this.getName(), sectionLeft);
        }
        start += 1;

        for (int i = 0; i < corridors.length; i++) {
            Corridor item = corridors[i];
            if(item == null){
                continue;
            }
            //"A {item-name} that leads to a {other-item-name}"
            //"Je {other-item-name} est led to by {item-name}"
            String text;
            int plr_index = i + start + 1;
            if(!temple.dark){
                text = plr_index + " : A " + item.getName() + " that leads to a " + item.other(this).getName();
                addCorridorToDisplay(output, i, String.valueOf(plr_index).charAt(0));
            }else{
                text = plr_index + " : ????";
            }

            write(output[1 + i], text, sectionLeft);


        }
        start += corridors.length;

        for (int i = 0; i < structs.size(); i++) {
            Interactable struct = structs.get(i);

            String text;
            if(!temple.dark){
                text = i + start + 1 + " : " + struct.getName();
            }else{
                text = i + start + 1 + " : ????";
            }
            write(output[2 + i + corridors.length], text, sectionLeft);
        }
        start += structs.size();

        for (int i = 0; i < items.length; i++) {
            Interactable item = items[i];
            String text;
            if(!temple.dark){
                text = i + start + 1 + " : A " + item.getName();
            }else{
                text = i + start + 1 + " : ????";
            }
            write(output[2 + i + corridors.length], text, sectionLeft);
        }
        start += structs.size();

        for(char[] str : output){
            System.out.println(str);
        }
    }

    public boolean interact(Player player, Item other){
        if(other != null){
            if(player.getInventory().removeItem(other)){
                inventory.addItem(other);

                Console.getInstance().displayText("You drop the " + other.getName() + " on the ground.");
                return true;
            }
        }
        return false;
    }

    public abstract void enterRoom();

    //really less of a target and more of a minimum
    //Some rooms, especially bigger rooms with a lot of neighbours will typically have more.
    public int getTargetCorridors(){
        return 2;
    }
}
