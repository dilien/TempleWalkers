package base;

import items.Item;
import items.Score;
import rooms.Room;
import temple.Temple;

import java.util.ArrayList;

//represents the player object
//Not a singleton, although there is never multiple players
public class Player{
    public Player(){
        inventory = new Inventory(10);
        Temple temple = Temple.getInstance();
        temple.tickEvent.listen((_)->tick());
    };

    int oxygenLeft = 30;
    public boolean end = false;
    public boolean alive = true;

    //todo: move to console class because it makes more sense there
    public void summarise(){
        int score = 0;
        for(Item item : inventory.items){
            if(item instanceof Score score1){
                score += score1.score;
            }
        }
        if(alive){
            System.out.println(Console.getInstance().output);
            System.out.println("You made it out of the facility!");
            System.out.println("You had files worth over " + score + "! (out of a maximum: 1000)");
            if(score == 1000) {
                System.out.println("A perfect score. Congratulations!!!!!");
            }else if(score > 900){
                System.out.println("You did amazingly. It was almost perfect. Well done!");
            } else if(score > 700){
                System.out.println("You did excellently. Well done!");
            } else if (score > 400) {
                System.out.println("You did great. Well done!");
            }else if (score > 200){
                System.out.println("You did good, but you could have done better.");
            }else if (score > 100){
                System.out.println("You did pretty poorly, but at least you are alive.");
            }else{
                System.out.println("The rest of the science team almost kill you with anger after how little many documents you brought back. Shame on you.");
            }
        }else{
            System.out.println("You didn't make it out of the facility. :(");
            System.out.println("If you did make it out you would have had files worth up to " + score + ".");
            System.out.println("Better luck next time!");
        }
    }

    public int getOxygen(){
        return oxygenLeft;
    }

    public void refillOxygen(){
        oxygenLeft = 30;
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
        if(oxygenLeft < 0){
            Console.getInstance().displayText("\n Your suit runs out of oxygen...");
            alive = false;
            end = true;
        }
    }

    //get functions to prevent setting the inventory and the room.
    public Inventory getInventory() {
        return inventory;
    }

    public Room getRoom() {
        return room;
    }
}