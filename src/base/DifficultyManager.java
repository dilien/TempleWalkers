package base;

import items.*;
import rooms.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DifficultyManager {
    public static Difficulty difficulty = Difficulty.Easy;
    //TODO: implement difficulty with easy medium hard
    //this should effect, the items that generate, the the room size, and which rooms are added
    public static Room[] getRooms(){
        switch(difficulty){
            case Easy:
                Room[] rooms = new Room[25];
                rooms[0] = new ResearchLab();
                for(int i = 1; i<4; i++){
                    rooms[i] = new SecurityCheckpoint();
                }
                for(int i = 4; i<10; i++){
                    rooms[i] = new ResearchLab();
                }
                for(int i = 10; i<12; i++){
                    rooms[i] = new MaintenanceTunnel();
                }
                for(int i = 12; i<15; i++){
                    rooms[i] = new ElevatorShaft();
                }
                for(int i = 15; i<18; i++){
                    rooms[i] = new HoldingCell();
                }
                for(int i = 18; i<25; i++){
                    rooms[i] = new SupplyCloset();
                }
                return rooms;
            case Medium:
                Room[] rooms2 = new Room[42];
                for(int i = 0; i<1; i++){
                    rooms2[i] = new ParticleAcceleratorRoom();
                }
                for(int i = 1; i<5; i++){
                    rooms2[i] = new SecurityCheckpoint();
                }
                for(int i = 5; i<15; i++){
                    rooms2[i] = new ResearchLab();
                }
                for(int i = 15; i<20; i++){
                    rooms2[i] = new MaintenanceTunnel();
                }
                for(int i = 20; i<25; i++){
                    rooms2[i] = new ElevatorShaft();
                }
                for(int i = 25; i<30; i++){
                    rooms2[i] = new HoldingCell();
                }
                for(int i = 30; i<40; i++){
                    rooms2[i] = new SupplyCloset();
                }
                for(int i = 40; i<42; i++){
                    rooms2[i] = new FluxRoom();
                }
                return rooms2;
            case Hard:
                Room[] rooms3 = new Room[45];
                for(int i = 0; i<1; i++){
                    rooms3[i] = new ParticleAcceleratorRoom();
                }
                for(int i = 1; i<5; i++){
                    rooms3[i] = new SecurityCheckpoint();
                }
                for(int i = 5; i<15; i++){
                    rooms3[i] = new ResearchLab();
                }
                for(int i = 15; i<20; i++){
                    rooms3[i] = new MaintenanceTunnel();
                }
                for(int i = 20; i<25; i++){
                    rooms3[i] = new ElevatorShaft();
                }
                for(int i = 25; i<30; i++){
                    rooms3[i] = new HoldingCell();
                }
                for(int i = 30; i<40; i++){
                    rooms3[i] = new SupplyCloset();
                }
                for(int i = 40; i<45; i++){
                    rooms3[i] = new FluxRoom();
                }
                return rooms3;
        }
        return new Room[0];
    }

    public static int getSize(){
        return switch (difficulty) {
            case Easy -> 8;
            case Medium -> 10;
            case Hard -> 12;
        };
    }

    public static ArrayList<Item> getCommonItems(){
        ArrayList<Item> itemsToAdd = new ArrayList<>();
        switch (difficulty) {
            case Easy:
                for(int i = 0; i < 10; i++){
                    itemsToAdd.add(new OxygenCanister());
                }
                for(int i = 0; i < 3; i++){
                    itemsToAdd.add(new Flashlight());
                }

                for(int i = 0; i < 5; i++){
                    itemsToAdd.add(new Battery());
                }
                for(int i : new int[]{10, 20, 30, 40, 50}){
                    itemsToAdd.add(new Artifact(i));
                }
                break;
            case Medium:
                for(int i = 0; i < 10; i++){
                    itemsToAdd.add(new OxygenCanister());
                }
                for(int i = 0; i < 3; i++){
                    itemsToAdd.add(new Flashlight());
                }

                for(int i = 0; i < 5; i++){
                    itemsToAdd.add(new Battery());
                }
                for(int i : new int[]{
                        2, 2, 2, 2,
                        3, 3, 3, 3,
                        5, 5, 5,
                        10, 10,
                        20}){
                    itemsToAdd.add(new Document(i));
                }
                for(int i : new int[]{10, 20, 30, 40, 50}){
                    itemsToAdd.add(new Artifact(i));
                }
                break;
            case Hard:
                for(int i = 0; i < 10; i++){
                    itemsToAdd.add(new OxygenCanister());
                }
                for(int i = 0; i < 3; i++){
                    itemsToAdd.add(new Flashlight());
                }

                for(int i = 0; i < 5; i++){
                    itemsToAdd.add(new Battery());
                }
                //225 total
                for(int i : new int[]{
                        2, 2, 2, 2,
                        2, 2, 2, 2,
                        2, 2, 2, 2,
                        3, 3, 3, 3,
                        3, 3, 3, 3,
                        3, 3, 3, 3,
                        5, 5, 5,
                        5, 5, 5,
                        5, 5, 5,
                        10, 10,
                        10, 10,
                        10, 10,
                        20, 20, 20}){
                    itemsToAdd.add(new Document(i));
                }
                break;
        };
        return itemsToAdd;
    }

    public static ArrayList<Item> getRareItems(){
        ArrayList<Item> rareItemsToAdd = new ArrayList<>();
        switch (difficulty) {
            case Easy:
                for(int i = 1; i < 3; i++){
                    rareItemsToAdd.add(new Keycard(i));
                }
                for(int i : new int[]{125, 150, 200}){
                    rareItemsToAdd.add(new Artifact(i));
                }
                break;
            case Medium:
                for(int i = 1; i < 6; i++){
                    rareItemsToAdd.add(new Keycard(i));
                }
                for(int i = 0; i < 3; i++){
                    rareItemsToAdd.add(new Briefcase());
                }
                for(int i : new int[]{40, 50, 60, 70, 80}){
                    rareItemsToAdd.add(new Document(i));
                }
                for(int i : new int[]{125, 150, 200}){
                    rareItemsToAdd.add(new Artifact(i));
                }
                break;
            case Hard:
                for(int i = 1; i < 6; i++){
                    rareItemsToAdd.add(new Keycard(i));
                }
                for(int i = 0; i < 1; i++){
                    rareItemsToAdd.add(new Briefcase());
                }
                for(int i : new int[]{20, 25, 30, 35, 40, 20, 25, 30, 35, 40}){
                    rareItemsToAdd.add(new Document(i));
                }
                for(int i : new int[]{50, 75, 75, 75, 100, 100}){
                    rareItemsToAdd.add(new Artifact(i));
                }
                break;
        };
        return rareItemsToAdd;
    }
}
