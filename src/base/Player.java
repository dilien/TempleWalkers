package base;

import items.Item;
import rooms.Room;

//represents the player object
//Not a singleton, although there is never multiple players
public class Player{
    public Player(){
        inventory = new Inventory(5);
    };

    Room room;
    Inventory inventory;

    public void enterRoom(Room room){
        this.room = room;
        Console.getInstance().displayText("Entering a " + room.getName());
        //trigger any room-specific effects
        room.enterRoom();
    }

    //get functions to prevent setting the inventory and the room.
    public Inventory getInventory() {
        return inventory;
    }

    public Room getRoom() {
        return room;
    }
}