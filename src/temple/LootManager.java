package temple;

import items.*;
import rooms.Room;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class LootManager {
    public ArrayList<Item> rareItemsToAdd;
    public ArrayList<Item> itemsToAdd;
    Random rand = new Random();

    LootManager(){
        rareItemsToAdd = new ArrayList<>();
        itemsToAdd = new ArrayList<>();
    }

    /**
     * Generates all rare items. If a rare item cannot be generated, it is just created like a regular item.
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
     * @param rooms
     */
     void generateCommonItems(Room[] rooms){
        for(Item common : itemsToAdd) {
            Room randomRoom = rooms[rand.nextInt(rooms.length)];
            boolean success = randomRoom.generateLoot(common);
            if (!success) {
                //just drop the item on the floor
                randomRoom.inventory.addItem(common);
            }
        }
    }

    void addItems(){
         for(int i = 1; i < 6; i++){
             rareItemsToAdd.add(new Keycard(i));
         }

        for(int i = 0; i < 10; i++){
            itemsToAdd.add(new OxygenCanister());
        }

        for(int i = 0; i < 3; i++){
            itemsToAdd.add(new Flashlight());
        }

        for(int i = 0; i < 5; i++){
            itemsToAdd.add(new Battery());
        }

        for(int i = 0; i < 3; i++){
            rareItemsToAdd.add(new Briefcase());
        }


        for(int i : new int[]{
                2, 2, 2, 2,
                3, 3, 3, 3,
                5, 5, 5,
                10, 10,
                20}){
            itemsToAdd.add(new Document(i));
        }
        for(int i : new int[]{40, 50, 60, 70, 80}){
            rareItemsToAdd.add(new Document(i));
        }

        for(int i : new int[]{10, 20, 30, 40, 50}){
            itemsToAdd.add(new Artifact(i));
        }
        for(int i : new int[]{125, 150, 200}){
            rareItemsToAdd.add(new Artifact(i));
        }
    }

    public void generateLoot(Room[] rooms){
        addItems();
        //filter for non-null rooms (rooms that have generated successfully)
        Room[] rooms2 = Arrays.stream(rooms).filter(Objects::nonNull).toArray(Room[]::new);
        generateRareItems(rooms2);
        generateCommonItems(rooms2);
    }
}
