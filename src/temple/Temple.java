package temple;

import base.DifficultyManager;
import base.Player;
import items.*;
import rooms.Room;
import utility.Event;

/**
 * This singleton holds a lot of other classes.
 * it also manages game ticks.
 */
public class Temple {

    public final LootManager lootManager;
    public final static int size = DifficultyManager.getSize();

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

    /**
     * Creates the temple, and makes the player enter it.
     * @param player - player to add to the temple
     */
    public void testInit(Player player){
        TempleGenerator generator = new TempleGenerator();
        Room start = generator.generateRooms();

        player.getInventory().addItem(new OxygenCanister());

        player.enterRoom(start);
    }

    public final Event<Void> tickEvent = new Event<>();

    int timeUntilFlip = 25;
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
