package rooms;

import structures.Container;
import structures.ContainerType;
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
import java.util.Random;


/**
 * The level is made up of rooms
 * Each room contains corridors, structures and items.
 * Rooms themselves can be interacted with, to drop/pickup items.
 */
public abstract class Room implements Interactable {
    //represents position on the map grid
    public int x = -100;
    public int y = -100;
    boolean flipped = false;
    int sizeX;
    int sizeY;
    public int getSizeX(){
        return flipped ? sizeY : sizeX;
    }
    public int getSizeY(){
        return flipped ? sizeX : sizeY;
    }

    int accessLevel = 0;
    public int getAccessLevel(){return accessLevel;}
    public void setAccessLevel(int accessLevel){this.accessLevel = accessLevel;}

    public int xs = 4; //x size in characters
    public int ys = 2; //y size in characters

    //corridors temp is used during generation, and is converted to an array after.
    public Corridor[] corridors;

    public ArrayList<Structure> structs = new ArrayList<>();

    public Inventory inventory;

    public Room(int sizeX, int sizeY){
        inventory = new Inventory(0);
        flipped = Math.random() > 0.5;
        int perimiter = (sizeX + sizeY) * 2;
        corridors = new Corridor[perimiter];
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        generateCorridors();
    }

    void generateCorridors(){
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
    void addCorridorsToDisplay(char[][] output, boolean dark, int playerFrom, int indexOffset){
        for(int y = 0; y<getSizeY(); y++){
            int id = y;
            if(!dark && this.corridors[id]==null){continue;}
            write(output[1+y*ys], String.format("%-2d", id+1+indexOffset),0);
            if(id == playerFrom){
                write(output[1+y*ys], "@",2);
            }
        }
        for(int x = 0; x<getSizeX(); x++){
            int id = x+getSizeY();
            if(!dark && this.corridors[id]==null){continue;}
            write(output[1+getSizeY()*ys], String.format("%-2d", id+1+indexOffset), 3+x*xs);
            if(id == playerFrom){
                write(output[getSizeY()*ys], "@",3+x*xs);
            }
        }
        for(int y = 0; y<getSizeY(); y++){
            int id = y+getSizeX()+getSizeY();
            if(!dark && this.corridors[id]==null){continue;}
            write(output[-1+(getSizeY()-y)*ys], String.format("%-2d", id+1+indexOffset), 2+this.getSizeX()*xs);
            if(id == playerFrom){
                write(output[-1+(getSizeY()-y)*ys], "@",1+this.getSizeX()*xs);
            }
        }
        for(int x = 0; x<getSizeX(); x++){
            int id = x+getSizeY()*ys+getSizeX();
            if(!dark && this.corridors[id]==null){continue;}
            write(output[0], String.format("%-2d", id+1+indexOffset),-1+(this.getSizeX()-x)*xs);
            if(id == playerFrom){
                write(output[1], "@",-1+(this.getSizeX()-x)*xs);
            }
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
        for (int i = 0; i < structs.size(); i++) {
            Structure struct = structs.get(i);
            arr[i+start] = struct;
        }
        start += structs.size();

        for (int i = 0; i < items.length; i++) {
            Interactable item = items[i];
            arr[i+start] = item;
        }
        start += items.length;

        for (int i = 0; i < corridors.length; i++) {
            Corridor item = corridors[i];
            arr[i+start] = item;
        }
        start += corridors.length;

        return arr;
    }

    /**
     * Prints a view of the room for the user.
     *
     * @param start - index to start with when displaying the interactables
     */
    public void render(int start, PositionSide playerPosition){

        Temple temple = Temple.getInstance();

        Interactable[] items = inventory.getAll();

        int length = 1 + corridors.length + structs.size() + items.length;

        int realCorridorIndex = 0; //for corridors that are not null
        for (Corridor corridor : corridors) {
            if (corridor != null || temple.dark) {
                realCorridorIndex++;
            }
        }

        int sectionLeft = this.getSizeX()*xs+6;
        int rightHeight = items.length + realCorridorIndex + structs.size() + 1;

        int leftHeight = 2 + this.getSizeY() * ys;
        int height = Math.max(rightHeight, leftHeight);

        char[][] output = new char[height][100].clone();
        //not quite sure how this works but thanks internet:
        java.util.Arrays.stream(output).forEach(row -> Arrays.fill(row, ' '));

        for(int x = 0; x<this.getSizeX()*xs+4; x++){
            for(int y = 0; y<this.getSizeY()*ys+2; y++){
                if(x == 0 || y == 0 || x == 1 || x == this.getSizeX()*xs +2 || x == this.getSizeX()*xs +3 || y == this.getSizeY()*ys + 1){
                    output[y][x] = temple.dark?'.':'█';
                }else{
                    output[y][x] = temple.dark?' ':'.';
                }
            }
        }
        if(temple.dark){
            write(output[0], start + 1 + " : You are in a ????", sectionLeft);
        }else{
            write(output[0], start + 1 + " : You are in a " + this.getName(), sectionLeft);
        }
        start += 1;

        for (int i = 0; i < structs.size(); i++) {
            Interactable struct = structs.get(i);

            String text;
            if(!temple.dark){
                text = i + start + 1 + " : " + struct.getName();
            }else{
                text = i + start + 1 + " : ????";
            }
            write(output[i + start], text, sectionLeft);
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
            write(output[i + start], text, sectionLeft);
        }
        start += items.length;

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

            write(output[realCorridorIndex + start], text, sectionLeft);
            realCorridorIndex++;


        }
        int localPos = globalSideToLocal(playerPosition);
        addCorridorsToDisplay(output, temple.dark, localPos, start);
        start += realCorridorIndex;


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
    public boolean generateRareLoot(Item item){
        return false;
    }
    public boolean generateLoot(Item item){
        ArrayList<Container> containers = new ArrayList<>();
        for(Structure s : structs) {
            if(s instanceof Container && ((Container) s).item == null) {
                containers.add((Container)s);
            }
        }
        if(!containers.isEmpty()){
            containers.get(new Random().nextInt(containers.size())).item = item;
            return true;
        }
        return false;
    }

    //really less of a target and more of a minimum
    //Some rooms, especially bigger rooms with a lot of neighbours will typically have more.
    public int getTargetCorridors(){
        return 2;
    }
}
