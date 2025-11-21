package base;

import items.Item;
import rooms.Room;
import temple.Temple;

import java.util.ArrayList;

//represents the player object
//Not a singleton, although there is never multiple players
public class Player{
    public Player(){
        inventory = new Inventory(5);
        Temple temple = Temple.getInstance();
        temple.tickEvent.listen((_)->tick());
    };

    int oxygenLeft = 50;

    public int getOxygen(){
        return oxygenLeft;
    }

    public void refillOxygen(){
        oxygenLeft = 50;
    }

    Room room;
    Inventory inventory;


    Room lastRoom = null;
    Room secondLastRoom = null;
    public void enterRoom(Room room){
        this.room = room;
        Console console = Console.getInstance();
        console.displayText("Entering a " + room.getName());

        Temple temple = Temple.getInstance();
        secondLastRoom = lastRoom;
        lastRoom = room;

        //trigger any room-specific effects
        room.enterRoom();
    }

    public void tick(){
        oxygenLeft -= 1;
    }

    //get functions to prevent setting the inventory and the room.
    public Inventory getInventory() {
        return inventory;
    }

    public Room getRoom() {
        return room;
    }
}