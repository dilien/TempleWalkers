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
        for(int y = 0; y<getSizeY(); y++){
            globalDirections[y] = new PositionSide(new Vector2(this.x-1 , y+this.y).wrap(), true);
        }
        for(int x = 0; x<getSizeX(); x++){
            globalDirections[getSizeY()+x] = new PositionSide(new Vector2(x+this.x, this.y-1 + getSizeY()).wrap(), false);
        }
        for(int y = 0; y<getSizeY(); y++){
            globalDirections[p_h+(getSizeY()-y-1)] = new PositionSide(new Vector2((this.x-1) +getSizeX(), y+this.y).wrap(), true);
        }
        for(int x = 0; x<getSizeX(); x++){
            globalDirections[p_h+getSizeY()+(getSizeX()-x-1)] = new PositionSide(new Vector2(x+this.x, this.y-1).wrap(), false);
        }
        System.out.println(Arrays.toString(globalDirections));
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
     * A function to add all corridors to the top left display (the room top-down view)
     * @param output - grid of characters to write to
     * @param dark - if all sides are displayed, or just the ones that lead somewhere.
     */
    void addCorridorsToDisplay(char[][] output, boolean dark){
        for(int x = 0; x<getSizeX(); x++){
            int id = x;
            if(!dark && this.corridors[id]==null){continue;}
            write(output[0], String.valueOf(id+2),3+x*3);
        }
        for(int y = 0; y<getSizeY(); y++){
            int id = y+this.getSizeX();
            if(!dark && this.corridors[id]==null){continue;}
            write(output[1+y*2], String.valueOf(id+2),2+this.getSizeX()*3);
        }
        for(int x = 0; x<getSizeX(); x++){
            int id = x+this.getSizeX()+this.getSizeY();
            if(!dark && this.corridors[id]==null){continue;}
            write(output[1+this.getSizeX()*2], String.valueOf(id+2),(this.getSizeX()*3)-x*3);
        }
        for(int y = 0; y<getSizeY(); y++){
            int id = y+this.getSizeX()*2+this.getSizeY();
            if(!dark && this.corridors[id]==null){continue;}
            write(output[(-1+this.getSizeX()*2)-y*2], String.valueOf(id+2),1);
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

        int realCorridorIndex = 0; //for corridors that are not null
        for (Corridor corridor : corridors) {
            if (corridor != null || temple.dark) {
                realCorridorIndex++;
            }
        }

        int sectionLeft = this.getSizeY()*3+4;
        int rightHeight = items.length + realCorridorIndex + structs.size() + 1;

        int leftHeight = 2 + this.getSizeX() * 2;
        int height = Math.max(rightHeight, leftHeight);

        char[][] output = new char[height][100].clone();
        //not quite sure how this works but thanks internet:
        java.util.Arrays.stream(output).forEach(row -> Arrays.fill(row, ' '));

        for(int x = 0; x<this.getSizeX()*2+2; x++){
            for(int y = 1; y<this.getSizeY()*3+3; y++){
                if(x == 0 || y == 1 || x == this.getSizeX()*2 +1 || y == this.getSizeY()*3 + 2){
                    output[x][y] = temple.dark?'.':'█';
                }else{
                    output[x][y] = temple.dark?' ':'.';
                }
            }
        }
        if(temple.dark){
            write(output[0], start + 1 + " : You are in a ????", sectionLeft);
        }else{
            write(output[0], start + 1 + " : You are in a " + this.getName(), sectionLeft);
        }
        start += 1;

        realCorridorIndex = 0; //for corridors that are not null
        for (int i = 0; i < corridors.length; i++) {
            Corridor item = corridors[i];
            //"A {item-name} that leads to a {other-item-name}"
            //"Je {other-item-name} est led to by {item-name}"
            String text;
            int plr_index = i + start + 1;
            if(!temple.dark){
                if(item == null){
                    continue;
                }
                text = plr_index + " : A " + item.getName() + " that leads to a " + item.other(this).getName();
            }else{
                text = plr_index + " : ????";
            }

            write(output[1 + realCorridorIndex], text, sectionLeft);
            realCorridorIndex++;


        }
        addCorridorsToDisplay(output, temple.dark);
        start += realCorridorIndex;

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
