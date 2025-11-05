package rooms;

import Temple.CorridorSide;
import Temple.Side;
import base.*;
import corridors.Corridor;
import items.Item;
import structures.Structure;

import java.util.ArrayList;
import java.util.Arrays;

//you cannot interact with the rooms themselves,
//you can only interact with the things in them.
public abstract class Room implements Interactable, Renderable {
    public int x = -100;
    public int y = -100;
    public int sizeX = 1;
    public int sizeY = 1;

    public ArrayList<Corridor> corridorsTemp;
    public Corridor[] corridors;

    public ArrayList<Structure> structs = new ArrayList<>();

    public Inventory inventory;

    public Room(){

        inventory = new Inventory(0);
        corridorsTemp = new ArrayList<Corridor>();
    }

    public static void write(char[] output, String text, int start){
        System.arraycopy(text.toCharArray(), 0, output, start, text.length());
    }

    public void addCorridor(char[][] output, CorridorSide side, char character){
        int x = 1;
        int y = 1;
        if(side == null){
            return;
        }
        //System.out.println("corridor:" + side.side());
        //System.out.println(room.x + " " + room.y);
        //System.out.println(corridor.other(room).x + " " + corridor.other(room).y);
        switch (side.side()){
            case Side.North -> output[(x + side.x()) * 2][(y) * 2 - 2] = character;
            case Side.South -> output[(x + side.x()) * 2][(y + this.sizeY - 1) * 2 + 2] = character;
            case Side.West -> output[(x) * 2 - 2][(y + side.x()) * 2] = character;
            case Side.East -> output[(x + this.sizeX - 1) * 2 + 2][(y + side.x()) * 2] = character;
        }
    }

    public Interactable[] render(int start, boolean render){
        Interactable[] items = inventory.render(0, false);

        int length = 1 + corridors.length + structs.size() + items.length;

        int sectionLeft = 20;
        int rightHeight = 4 + items.length + corridors.length + structs.size();
        int leftHeight = 3 + this.sizeX * 2;
        int height = Math.max(rightHeight, leftHeight);

        char[][] output = new char[height][100].clone();
        //not quite sure how this works but thanks internet:
        java.util.Arrays.stream(output).forEach(row -> Arrays.fill(row, ' '));

        for(int x = 1; x<this.sizeX*2+2; x++){
            for(int y = 1; y<this.sizeY*2+2; y++){
                if(x == 1 || y == 1 || x == this.sizeX*2 + 1 || y == this.sizeY*2 + 1){
                    output[x][y] = '#';
                }
            }
        }

        for(int y = 0; y<height; y++){
            output[y][sectionLeft-1] = '|';
        }

        Interactable[] arr = new Interactable[length];

        arr[0] = this;
        //System.out.println(start + 1 + ":You are in a " + this.getName());
        write(output[0], start + 1 + " : You are in a " + this.getName(), sectionLeft);
        start += 1;

        for (int i = 0; i < corridors.length; i++) {
            Corridor item = corridors[i];
            arr[i+start] = item;
            int plr_index = i + start + 1;
            //"A {item-name} that leads to a {other-item-name}"
            //"Je {other-item-name} est led to by {item-name}"
            String text = plr_index + " : A " + item.getName() + " that leads to a " + item.other(this).getName() + item.getSide(this).side();
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

        for(char[] str : output){
            System.out.println(str);
        }

        return arr;
    }

    public boolean interact(Player player, Item other){
        if(other != null){
            if(player.inventory.removeItem(other)){
                inventory.addItem(other);

                Console.getInstance().displayText("You drop the " + other.getName() + " on the ground.");
                return true;
            }
        }
        //player.displayText("You cannot interact with the " + this.getName());
        return false;
    }

    public abstract void enterRoom();

    public int getTargetCorridors(){
        return 2;
    }

    public void finalise(){
        corridors = corridorsTemp.toArray(new Corridor[0]);
        corridorsTemp = null;
    }
}
