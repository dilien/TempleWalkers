package temple;

import base.Player;
import items.*;
import rooms.Room;
import utility.Event;

import javax.print.Doc;
import java.util.ArrayList;

//This holds all the rooms and is responsible for gameticks. It is a singleton.
public class Temple {

    public LootManager lootManager;
    public static int size = 10;

    private Temple(){
        lootManager = new LootManager();
    }
    private static Temple temple;
    public static Temple getInstance(){
        if(temple == null){
            temple = new Temple();
        }
        return temple;
    }

    public boolean dark;
    public void testInit(Player player){
        TempleGenerator generator = new TempleGenerator();
        Room start = generator.generateRooms();

        player.enterRoom(start);
    }

    public Event<Void> tickEvent = new Event<>();

    int timeUntilFlip = 50;
    public int totalTurns;
    public void tick(){
        totalTurns += 1;
        timeUntilFlip -= 1;
        if(timeUntilFlip == 0){
            dark = true;
        }
        tickEvent.send(null);
    }
    public void resetLights(){
        dark = false;
        timeUntilFlip = (int)(Math.random() * 10) + 20;
    }
}
