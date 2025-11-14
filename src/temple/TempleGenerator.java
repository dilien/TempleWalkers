package temple;

import base.Console;
import base.Vector2;
import com.sun.tools.javac.Main;
import corridors.Corridor;
import corridors.KeycardDoor;
import corridors.TempleFrame;
import rooms.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
This class is used to generate the map, once the map is generated, the class is done.
It should really only be called once, but I haven't implemented it as a singleton yet.
 */
public class TempleGenerator {

    public boolean checkFree(int x, int y, int sizeX, int sizeY, Room[][] grid){
        for(int x2 = x; x2 < x+sizeX; x2++){
            int x3 = x2 % Temple.size;
            for(int y2 = y; y2 < y+sizeY; y2++){
                int y3 = y2 % Temple.size;
                if(grid[x3][y3] != null){
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
        Room[] rooms = new Room[60];
        for(int i = 0; i<3; i++){
            rooms[i] = new LaserTestingLab();
        }
        for(int i = 3; i<6; i++){
            rooms[i] = new ParticleAcceleratorRoom();
        }
        for(int i = 6; i<10; i++){
            rooms[i] = new SecurityCheckpoint();
        }
        for(int i = 10; i<20; i++){
            rooms[i] = new ColdStorage();
        }
        for(int i = 20; i<30; i++){
            rooms[i] = new SecurityCheckpoint();
        }
        for(int i = 30; i<40; i++){
            rooms[i] = new ResearchLab();
        }
        for(int i = 30; i<50; i++){
            rooms[i] = new MaintenanceTunnel();
        }
        for(int i = 40; i<60; i++){
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
                    room.x = x;
                    room.y = y;
                    room.generateCorridors();
                    System.out.println(room.getSizeX() + " " +  room.getSizeY() + " " + room.corridors.length);
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
                    KeycardDoor frame = new KeycardDoor(room, other, global);

                    //auto added to the rooms
                    current += 1;
                }
                max -= 1;
            }
        }

        createSectors(rooms);

        Map map = new Map();
        map.displayMap(templeSize, rooms);

        return rooms[0];
    }
    void createSectors(Room[] rooms){
        ArrayList<Room>[] toCheck = new ArrayList[]{new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()};
        for(int i = 0; i<5; i++){
            toCheck[i].add(rooms[i]);
        }

        boolean allEmpty = false;
        while (!allEmpty){
            allEmpty = true;
            for(int i = 0; i <5; i++){
                int sector = i+1;
                if(toCheck[i].isEmpty()){
                    continue;
                }
                allEmpty = false;
                Room room = toCheck[i].getFirst();
                toCheck[i].removeFirst();
                if(room.getAccessLevel() != 0){
                    continue;
                }

                room.setAccessLevel(sector);

                for(Corridor corridor : room.corridors){
                    if(corridor != null){
                        Room other = corridor.other(room);
                        toCheck[i].add(other);
                        if(corridor instanceof KeycardDoor){
                            if(other.getAccessLevel() != sector){
                                ((KeycardDoor) corridor).setLevel(sector);
                            }else{
                                ((KeycardDoor) corridor).setLevel(0);
                            }
                        }
                    }
                }
            }
        }

    }
}
