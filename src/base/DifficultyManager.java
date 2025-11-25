package base;

import items.*;
import rooms.*;

import java.util.ArrayList;

public class DifficultyManager {
    public static Difficulty difficulty = Difficulty.Easy;

    /**
     * Takes user input and yields until a difficulty is selected
     */
    public static void setDifficulty(){
        Console console = Console.getInstance();
        while(difficulty == null){
            String answer = console.prompt("Please select a difficulty (m/medium) (h/hard) (v/very hard)").strip() + " ";
            if(answer.charAt(0) == 'm'){
                difficulty = Difficulty.Easy;
            }
            if(answer.charAt(0) == 'h'){
                difficulty = Difficulty.Medium;
            }
            if(answer.charAt(0) == 'v'){
                difficulty = Difficulty.Hard;
            }
        }
    }

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
                Room[] rooms2 = new Room[38];
                for(int i = 0; i<1; i++){
                    rooms2[i] = new ParticleAcceleratorRoom();
                }
                for(int i = 1; i<6; i++){
                    rooms2[i] = new SecurityCheckpoint();
                }
                for(int i = 6; i<16; i++){
                    rooms2[i] = new ResearchLab();
                }
                for(int i = 16; i<21; i++){
                    rooms2[i] = new MaintenanceTunnel();
                }
                for(int i = 21; i<26; i++){
                    rooms2[i] = new ElevatorShaft();
                }
                for(int i = 26; i<28; i++){
                    rooms2[i] = new FluxRoom();
                }
                for(int i = 28; i<33; i++){
                    rooms2[i] = new HoldingCell();
                }
                for(int i = 33; i<38; i++){
                    rooms2[i] = new SupplyCloset();
                }
                return rooms2;
            case Hard:
                Room[] rooms3 = new Room[54];
                for(int i = 0; i<1; i++){
                    rooms3[i] = new ParticleAcceleratorRoom();
                }
                for(int i = 1; i<9; i++){
                    rooms3[i] = new SecurityCheckpoint();
                }
                for(int i = 9; i<19; i++){
                    rooms3[i] = new MaintenanceTunnel();
                }
                for(int i = 19; i<31; i++){
                    rooms3[i] = new ResearchLab();
                }
                for(int i = 31; i<36; i++){
                    rooms3[i] = new ElevatorShaft();
                }
                for(int i = 36; i<44; i++){
                    rooms3[i] = new HoldingCell();
                }
                for(int i = 44; i<49; i++){
                    rooms3[i] = new SupplyCloset();
                }
                for(int i = 49; i<54; i++){
                    rooms3[i] = new FluxRoom();
                }
                return rooms3;
        }
        return new Room[0];
    }

    public static int getSize(){
        return switch (difficulty) {
            case Easy -> 8;
            case Medium -> 11;
            case Hard -> 13;
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
        }
        return itemsToAdd;
    }

    public static ArrayList<Item> getRareItems(){
        ArrayList<Item> rareItemsToAdd = new ArrayList<>();
        switch (difficulty) {
            case Easy:
                for (int i = 1; i < 3; i++) {
                    rareItemsToAdd.add(new Keycard(i));
                }
                for (int i : new int[]{125, 150, 200}) {
                    rareItemsToAdd.add(new Artifact(i));
                }
                break;
            case Medium:
                for (int i = 1; i < 6; i++) {
                    rareItemsToAdd.add(new Keycard(i));
                }
                for (int i = 0; i < 3; i++) {
                    rareItemsToAdd.add(new Briefcase());
                }
                for (int i : new int[]{40, 50, 60, 70, 80}) {
                    rareItemsToAdd.add(new Document(i));
                }
                for (int i : new int[]{125, 150, 200}) {
                    rareItemsToAdd.add(new Artifact(i));
                }
                break;
            case Hard:
                for (int i = 1; i < 6; i++) {
                    rareItemsToAdd.add(new Keycard(i));
                }
                for (int i = 0; i < 1; i++) {
                    rareItemsToAdd.add(new Briefcase());
                }
                for (int i : new int[]{20, 25, 30, 35, 40, 20, 25, 30, 35, 40}) {
                    rareItemsToAdd.add(new Document(i));
                }
                for (int i : new int[]{50, 75, 75, 75, 100, 100}) {
                    rareItemsToAdd.add(new Artifact(i));
                }
                break;
        }
        return rareItemsToAdd;
    }
}
