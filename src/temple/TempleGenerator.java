package temple;

import base.Console;
import base.Vector2;
import com.sun.tools.javac.Main;
import corridors.Corridor;
import corridors.TempleFrame;
import rooms.*;

import java.util.Arrays;
import java.util.Random;

/**
This class is used to generate the map, once the map is generated, the class is done.
It should really only be called once, but I haven't implemented it as a singleton yet.
 */
public class TempleGenerator {

    public boolean checkFree(int x, int y, int sizeX, int sizeY, Room[][] grid){
        for(int x2 = x; x2 < x+sizeX; x2++){
            for(int y2 = y; y2 < y+sizeY; y2++){
                if(grid[x2][y2] != null){
                    return false;
                }
            }
        }
        return true;
    }

    /**Checks if a corridor already exists on that side, so we know it is not valid for new corridors
     *
     * @param room - room to check for corridors
     * @param side - side to check for corridors
     * @return - true/false depending on if other corridor exists
     */
    public boolean isExistingCorridor(Room room, PositionSide side){
        int local = room.globalSideToLocal(side);
        if(local >= 0){
            return room.corridors[local] != null;
        }
        //in this case the corridor side is not part of the room
        return  true;
    }

    public Room getOtherRoom(Room[][] grid, Room room, PositionSide side){
        Vector2 forward = side.getForward();
        if(grid[forward.x()][forward.y()] != room){
            return grid[forward.x()][forward.y()];
        }
        Vector2 backward = side.getBackward();
        return grid[backward.x()][backward.y()];
    }

    /**
     * Generates a room. Duh.
     * @return - the starting room
     */
    Room generateRooms(){

        //A list of rooms we would like to add (although not guaranteed they will all be added)
        Room[] rooms = new Room[100];
        for(int i = 0; i<10; i++){
            rooms[i] = new BigRoom();
        }
        for(int i = 10; i<20; i++){
            rooms[i] = new ColdStorage();
        }
        for(int i = 20; i<30; i++){
            rooms[i] = new MaintenanceTunnel();
        }
        for(int i = 20; i<40; i++){
            rooms[i] = new ResearchLab();
        }
        for(int i = 30; i<50; i++){
            rooms[i] = new SecurityCheckpoint();
        }
        for(int i = 40; i<60; i++){
            rooms[i] = new SecurityCheckpoint();
        }
        for(int i = 50; i<100; i++){
            //rooms[i] = new CustomCorridor(1, 1);
            rooms[i] = new Chamber();
        }


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
            for (int i = 0; i < 100; i++) {
                int x = random.nextInt(1 + templeSize - sizeX);
                int y = random.nextInt(1 + templeSize - sizeY);
                if (checkFree(x, y, sizeX, sizeY, grid)) {
                    //If valid, assign the room to its position
                    for (int x2 = x; x2 < x + sizeX; x2++) {
                        for (int y2 = y; y2 < y + sizeY; y2++) {
                            grid[x2][y2] = room;
                        }
                    }
                    room.x = x;
                    room.y = y;
                    room.generateCorridors();
                    break;
                }
            }
            if (room.x < 0) {
                System.out.println("warning: couldnt place room correctly.");
            }
            //If we picked 10 random positions and none of them were right, we just give up
        }

        //Assign the corridors
        for(Room room : rooms) {
            if(room == null || room.x < 0){
                continue;
            }
            int target = room.getTargetCorridors();
            int current = 0;
            for(Corridor corridor : room.corridors){
                if(corridor != null){
                    current++;
                }
            }
            // 4 sides of a 1x1 room, 8 sides of a 3x1 room ect..
            int max = 0;
            int perimiter = room.corridors.length;
            boolean[] valid = new boolean[perimiter];
            for(int i = 0; i<perimiter; i++) {
                if(room.corridors[i] != null){
                    continue;
                }
                PositionSide global = room.localSideToGlobal(i);
                Room other = getOtherRoom(grid, room, global);
                if(other != null && other.corridors.length > 0){
                    max += 1;
                    valid[i] = true;
                }else{
                    valid[i] = false;
                }
            }

            for(int i = 0; i<perimiter; i++){
                if(!valid[i]){
                    continue;
                }
                PositionSide global = room.localSideToGlobal(i);
                if(random.nextDouble() <= (double) (target - current) / max){
                    //generate corridor
                    Room other = getOtherRoom(grid, room, global);
                    TempleFrame frame = new TempleFrame(room, other, global);

                    //auto added to the rooms
                    current += 1;
                }
                max -= 1;
            }
        }

        Map map = new Map();
        map.displayMap(templeSize, rooms);

        return rooms[0];
    }
}
