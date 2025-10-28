package base;

import items.Item;
import rooms.Room;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Player{
    public Player(){
        inventory = new Inventory(5);
    };

    Room room;
    public Inventory inventory;

    public void start(){

    }

    public void tick(){
        inventory.tick();
    }

    public void enterRoom(Room room){
        this.room = room;
        Console.getInstance().displayText("Entering a " + room.getName());
        //trigger any room-specific effects
        room.enterRoom();
    }

    public void addItem(Item item){
        if(! inventory.addItem(item)){
            room.inventory.addItem(item);
            //the above statement could fail, but all rooms do not have a max size, so it shouldn't.
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Room getRoom() {
        return room;
    }
}