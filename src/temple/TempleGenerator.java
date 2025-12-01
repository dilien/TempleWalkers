package temple;

import base.DifficultyManager;
import base.Vector2;
import corridors.Corridor;
import corridors.HiddenCorridor;
import rooms.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used to generate the map, once the map is generated, the class is done.
 * It should really only be called once, but I haven't implemented it as a singleton yet.
 */
public class TempleGenerator {

    /**
     * Checks if a spot on the grid is free, making use of wrapping
     *
     * @param x     - x top left
     * @param y     - y top left
     * @param sizeX - width
     * @param sizeY - height
     * @param grid  - to check for existing rooms
     * @return - true if every spot is free
     */
    public boolean checkFree(int x, int y, int sizeX, int sizeY, Room[][] grid) {
        for (int x2 = x; x2 < x + sizeX; x2++) {
            int x3 = x2 % Temple.size;
            for (int y2 = y; y2 < y + sizeY; y2++) {
                int y3 = y2 % Temple.size;
                if (grid[x3][y3] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if a corridor already exists on that side, so we know it is not valid for new corridors
     *
     * @param room - room to check for corridors
     * @param side - side to check for corridors
     * @return - true/false depending on if other corridor exists
     */
    public boolean isExistingCorridor(Room room, PositionSide side) {
        int local = room.globalSideToLocal(side);
        if (local >= 0) {
            return room.corridors[local] != null;
        }
        //in this case the corridor side is not part of the room
        return true;
    }

    /**
     * Returns one of the two rooms on the other side of a global position
     *
     * @param grid - to find rooms
     * @param room - existing room
     * @param side - global side
     * @return - other room
     */
    public Room getOtherRoom(Room[][] grid, Room room, PositionSide side) {
        Vector2 forward = side.getForward();
        if (grid[forward.x()][forward.y()] != room) {
            return grid[forward.x()][forward.y()];
        }
        Vector2 backward = side.getBackward();
        return grid[backward.x()][backward.y()];
    }

    /**
     * Generates the rooms, the corridors, the loot ect...
     *
     * @return - the starting room
     */
    Room generateRooms() {

        //A list of rooms we would like to add (although not guaranteed they will all be added)
        Room[] rooms = DifficultyManager.getRooms();


        final int templeSize = Temple.size;
        //holds references to a room, ie, multiple blocks can hold the same reference for big rooms
        Room[][] grid = new Room[templeSize][templeSize];

        Random random = new Random();

        //give each room a position
        for (int j = 0; j < rooms.length; j++) {
            Room room = rooms[j];
            int sizeX = room.getSizeX();
            int sizeY = room.getSizeY();
            //pick 10 random positions and hope they are valid
            for (int i = 0; i < 1000; i++) {
                int x = random.nextInt(templeSize);
                int y = random.nextInt(templeSize);
                if (checkFree(x, y, sizeX, sizeY, grid)) {
                    //If valid, assign the room to its position
                    for (int x2 = x; x2 < x + sizeX; x2++) {
                        int x3 = x2 % Temple.size;
                        for (int y2 = y; y2 < y + sizeY; y2++) {
                            int y3 = y2 % Temple.size;
                            grid[x3][y3] = room;
                        }
                    }
                    room.setPosition(x, y);
                    break;
                }
            }
            if (room.x < 0) {
                //System.out.println("warning: couldnt place " + room.getClass().getName() +  " correctly.");
                rooms[j] = null;
            }
            //If we picked 1000 random positions and none of them were right, we just give up
        }

        //Assign the corridors
        int total = 0;
        for (Room room : rooms) {
            if (room == null || room.x < 0) {
                continue;
            }
            total++;
            int target = room.getTargetCorridors();
            int current = 0;
            for (Corridor corridor : room.corridors) {
                if (corridor != null) {
                    current++;
                }
            }
            // 4 sides of a 1x1 room, 8 sides of a 3x1 room ect..
            int max = 0;
            int perimiter = room.corridors.length;
            boolean[] valid = new boolean[perimiter];
            for (int i = 0; i < perimiter; i++) {
                if (room.corridors[i] != null) {
                    continue;
                }
                PositionSide global = room.localSideToGlobal(i);
                Room other = getOtherRoom(grid, room, global);
                if (other != null && other.corridors.length > 0) {
                    max += 1;
                    valid[i] = true;
                } else {
                    valid[i] = false;
                }
            }

            for (int i = 0; i < perimiter; i++) {
                if (!valid[i]) {
                    continue;
                }
                PositionSide global = room.localSideToGlobal(i);
                if (random.nextDouble() <= (double) (target - current) / max) {
                    //generate corridor
                    Room other = getOtherRoom(grid, room, global);
                    new HiddenCorridor(room, other, global);

                    //auto added to the rooms
                    current += 1;
                }
                max -= 1;
            }
        }

        //not the best solution but better than no solution
        //re-generates entire map if all rooms are not connected
        boolean connected = checkConnectivity(rooms[0], total);
        if (!connected) {
            return generateRooms();
        }


        Temple.getInstance().lootManager.generateLoot(rooms);

        Map map = new Map();
        map.displayMap(templeSize, rooms);

        return rooms[0];
    }

    /**
     * Checks if a room connects to a target amount of rooms, via its corridors and their corridors.
     *
     * @param start  - room to start with
     * @param target - target rooms to reach
     * @return - if the target was met
     */
    public boolean checkConnectivity(Room start, int target) {
        ArrayList<Room> checked = new ArrayList<>();
        ArrayList<Room> toCheck = new ArrayList<>();
        checked.add(start);
        toCheck.add(start);
        int count = 0;
        while (!toCheck.isEmpty()) {
            count++;
            Room room = toCheck.getFirst();
            toCheck.removeFirst();

            for (Corridor corridor : room.corridors) {
                if (corridor != null) {
                    Room other = corridor.other(room);
                    if (checked.contains(other)) {
                        continue;
                    }
                    toCheck.add(other);
                    checked.add(other);
                }
            }
        }
        return count == target;
    }
}
