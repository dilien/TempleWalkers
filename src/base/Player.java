package base;

import items.Item;
import rooms.Room;
import temple.Temple;

//represents the player object
//Not a singleton, although there is never multiple players
public class Player{
    public Player(){
        inventory = new Inventory(5);
    };

    Room room;
    Inventory inventory;

    //a b a
    //enter(a), last null,null,a
    //enter(b), last a, a,b
    //enter(a), last b,a

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

    //get functions to prevent setting the inventory and the room.
    public Inventory getInventory() {
        return inventory;
    }

    public Room getRoom() {
        return room;
    }
}