package rooms;

import base.Console;
import base.Player;
import items.Item;
import temple.LootManager;
import temple.Temple;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FluxRoom extends Room{
    private static final ArrayList<FluxRoom> rooms = new ArrayList<>();
    private static int cooldown = 0;

    public FluxRoom(){
        super(1, 1);
        rooms.add(this);
    }

    //the reason we have to do this is because returning false would only cause the item to generate on the floor.
    public boolean generateLoot(Item item){
        Temple.getInstance().lootManager.itemsToAdd.add(item);
        return true;
    }

    public void enterRoom(Player player) {
        //If there is no where else to teleport then return before infinite loop.
        if(rooms.size() == 1){return;}

        //cooldown of one teleportation to prevent the player to be teleported in a infinite loop
        if(cooldown>0){cooldown-=1; return;}
        Console.getInstance().prompt("You suddenly feel disoriented and after a bright flash of light, you have no idea where you are.");
        cooldown = 1;

        Room other = this;
        while(other == this){
            other = rooms.get(new Random().nextInt(rooms.size()));
        }
        player.enterRoom(other);
    }

    public String getName() {
        return "Flux Room";
    }

    public String describe() {
        return "This room is a big teleporter. Entering it will cause you to teleport to a random flux room within the facility. How menacingly evil.";
    }
}
