package temple;

import base.DifficultyManager;
import items.*;
import rooms.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * Keeps track of the loot that needs to be generated during generation.
 */
public class LootManager {
    public ArrayList<Item> rareItemsToAdd;
    public ArrayList<Item> itemsToAdd;
    final Random rand = new Random();

    LootManager(){
        rareItemsToAdd = new ArrayList<>();
        itemsToAdd = new ArrayList<>();
    }

    /**
     * Generates all rare items. If a rare item cannot be generated, it is added to the list of regular items.
     * @param rooms - rooms to generate in.
     */
    void generateRareItems(Room[] rooms){
        for(Item rare : rareItemsToAdd){
            boolean success = false;
            for(int i = 0; i<20; i++){
                Room randomRoom = rooms[rand.nextInt(rooms.length)];
                success = randomRoom.generateRareLoot(rare);
                if(success){
                    break;
                }
            }
            if(!success){
                itemsToAdd.add(rare);
            }
        }
    }

    /**
     * Generates all common items. If items cannot be added by the room, they are just placed on the floor.
     * @param rooms - list of all rooms in the map
     */
     void generateCommonItems(Room[] rooms){
         //replaced with an indexed loop to properly adjust to length increases
         for (int i = 0; i < itemsToAdd.size(); i++) {
             Item common = itemsToAdd.get(i);
             Room randomRoom = rooms[rand.nextInt(rooms.length)];
             boolean success = randomRoom.generateLoot(common);
             if (!success) {
                 //just drop the item on the floor
                 randomRoom.inventory.addItem(common);
             }
         }
    }

    /**
     * Generate all loot in the facility.
     * @param rooms - a list of rooms
     */
    public void generateLoot(Room[] rooms){
        itemsToAdd = DifficultyManager.getCommonItems();
        rareItemsToAdd = DifficultyManager.getRareItems();
        //filter for non-null rooms (rooms that have generated successfully)
        Room[] rooms2 = Arrays.stream(rooms).filter(Objects::nonNull).toArray(Room[]::new);
        generateRareItems(rooms2);
        generateCommonItems(rooms2);
    }
}
