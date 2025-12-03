package base;

import items.*;
import rooms.*;
import structures.Whiteboard;

import java.util.ArrayList;

public class DifficultyManager {
    public static Difficulty difficulty = Difficulty.Easy;

    /**
     * Takes user input and yields until a difficulty is selected
     */
    public static void setDifficulty() {
        difficulty = null;
        Console console = Console.getInstance();
        while (difficulty == null) {
            String answer = console.prompt("Please select a difficulty (m/medium) (h/hard) (v/very hard)").strip() + " ";
            if (answer.charAt(0) == 'm') {
                difficulty = Difficulty.Easy;
            }
            if (answer.charAt(0) == 'h') {
                difficulty = Difficulty.Medium;
            }
            if (answer.charAt(0) == 'v') {
                difficulty = Difficulty.Hard;
            }
        }
    }

    /**
     *
     * @return - A list of rooms to generate, depending on difficulty
     */
    public static Room[] getRooms() {
        switch (difficulty) {
            case Easy:
                Room[] rooms = new Room[25];
                rooms[0] = new ResearchLab();
                for (int i = 1; i < 3; i++) {
                    rooms[i] = new MaintenanceTunnel();
                }
                for (int i = 3; i < 5; i++) {
                    rooms[i] = new SecurityCheckpoint();
                }
                for (int i = 5; i < 11; i++) {
                    rooms[i] = new ResearchLab();
                }
                for (int i = 11; i < 14; i++) {
                    rooms[i] = new ElevatorShaft();
                }
                for (int i = 14; i < 17; i++) {
                    rooms[i] = new HoldingCell();
                }
                for (int i = 17; i < 25; i++) {
                    rooms[i] = new SupplyCloset();
                }
                return rooms;
            case Medium:
                Room[] rooms2 = new Room[30];
                rooms2[0] = new ParticleAcceleratorRoom();
                for (int i = 1; i < 4; i++) {
                    rooms2[i] = new MaintenanceTunnel();
                }
                for (int i = 4; i < 6; i++) {
                    rooms2[i] = new SecurityCheckpoint();
                }
                for (int i = 6; i < 12; i++) {
                    rooms2[i] = new ResearchLab();
                }
                for (int i = 12; i < 17; i++) {
                    rooms2[i] = new ElevatorShaft();
                }
                for (int i = 17; i < 21; i++) {
                    rooms2[i] = new HoldingCell();
                }
                for (int i = 21; i < 23; i++) {
                    rooms2[i] = new FluxRoom();
                }
                for (int i = 23; i < 30; i++) {
                    rooms2[i] = new SupplyCloset();
                }
                return rooms2;
            case Hard:
                Room[] rooms3 = new Room[40];
                rooms3[0] = new ParticleAcceleratorRoom();
                for (int i = 1; i < 4; i++) {
                    rooms3[i] = new MaintenanceTunnel();
                }
                for (int i = 4; i < 8; i++) {
                    rooms3[i] = new SecurityCheckpoint();
                }
                for (int i = 8; i < 14; i++) {
                    rooms3[i] = new ResearchLab();
                }
                for (int i = 14; i < 19; i++) {
                    rooms3[i] = new ElevatorShaft();
                }
                for (int i = 19; i < 26; i++) {
                    rooms3[i] = new HoldingCell();
                }
                for (int i = 26; i < 31; i++) {
                    rooms3[i] = new FluxRoom();
                }
                for (int i = 31; i < 40; i++) {
                    rooms3[i] = new SupplyCloset();
                }
                return rooms3;
        }
        return new Room[0];
    }

    /**
     *
     * @return - size of each length of the temple. Depends on difficulty.
     */
    public static int getSize() {
        return switch (difficulty) {
            case Easy -> 8;
            case Medium -> 9;
            case Hard -> 10;
        };
    }

    /**
     *
     * @return - A List of common items to be generated. List depends on difficulty.
     */
    public static ArrayList<Item> getCommonItems() {
        ArrayList<Item> itemsToAdd = new ArrayList<>();
        switch (difficulty) {
            case Easy:
                for (int i = 0; i < 10; i++) {
                    itemsToAdd.add(new OxygenCanister());
                }
                for (int i = 0; i < 3; i++) {
                    itemsToAdd.add(new Flashlight());
                }

                for (int i = 0; i < 5; i++) {
                    itemsToAdd.add(new Battery());
                }
                for (int i : new int[]{10, 20, 30, 40, 50}) {
                    itemsToAdd.add(new Artefact(i));
                }
                break;
            case Medium:
                for (int i = 0; i < 10; i++) {
                    itemsToAdd.add(new OxygenCanister());
                }
                for (int i = 0; i < 3; i++) {
                    itemsToAdd.add(new Flashlight());
                }

                for (int i = 0; i < 5; i++) {
                    itemsToAdd.add(new Battery());
                }
                for (int i : new int[]{
                        2, 2, 2, 2,
                        3, 3, 3, 3,
                        5, 5, 5,
                        10, 10,
                        20}) {
                    itemsToAdd.add(new Document(i));
                }
                for (int i : new int[]{10, 20, 30, 40, 50}) {
                    itemsToAdd.add(new Artefact(i));
                }
                break;
            case Hard:
                for (int i = 0; i < 10; i++) {
                    itemsToAdd.add(new OxygenCanister());
                }
                for (int i = 0; i < 3; i++) {
                    itemsToAdd.add(new Flashlight());
                }

                for (int i = 0; i < 5; i++) {
                    itemsToAdd.add(new Battery());
                }
                //225 total
                for (int i : new int[]{
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
                        20, 20, 20}) {
                    itemsToAdd.add(new Document(i));
                }
                break;
        }
        return itemsToAdd;
    }

    /**
     *
     * @return - A list of rare items to be generated. The items depend on the difficulty.
     */
    public static ArrayList<Item> getRareItems() {
        ArrayList<Item> rareItemsToAdd = new ArrayList<>();
        switch (difficulty) {
            case Easy:
                for (int i = 1; i < 3; i++) {
                    rareItemsToAdd.add(new Keycard(i));
                }
                for (int i : new int[]{100, 125, 150, 200, 275}) {
                    rareItemsToAdd.add(new Artefact(i));
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
                    rareItemsToAdd.add(new Artefact(i));
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
                    rareItemsToAdd.add(new Artefact(i));
                }
                break;
        }
        return rareItemsToAdd;
    }

    public static ArrayList<Whiteboard> getHints(){
        ArrayList<Whiteboard> whiteboards = new ArrayList<>();
        whiteboards.add(new Whiteboard("Notice: Experimental time space navigation has caused the facility to 'mirror' for the time being. Please do not be alarmed if you enter a room you have previously left."));
        whiteboards.add(new Whiteboard("Notice: Please keep important artifacts locked away in secure containers that require ID's to open. "));
        if(difficulty == Difficulty.Medium){
            whiteboards.add(new Whiteboard("As part of our less-walking initiative due to the growing size of the facility, use an experimental flux room to teleport to the other pair. There are exactly 2 in the facility while we test it out."));
        }
        if(difficulty == Difficulty.Medium || difficulty == Difficulty.Hard){
            whiteboards.add(new Whiteboard("It has a diagram for a bottomless briefcase that can carry as many documents as you wish. It looks like there is only a few prototypes around the facility."));
        }
        if(difficulty == Difficulty.Hard){
            whiteboards.add(new Whiteboard("It looks like someone in the flux department messed up. All 5 flux rooms are activated and are randomly teleporting to each-other. Yikes."));
        }
        return  whiteboards;
    }
}
