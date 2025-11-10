package temple;

import corridors.Corridor;
import corridors.TempleFrame;
import rooms.BigRoom;
import rooms.Chamber;
import rooms.Room;
import testing.GeneratorTests;

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

    //Lists the sides on a corridor. See corridorSide for more info.
    public CorridorSide[] generateSides(Room room){
        int maxHalf = (room.getSizeX() + room.getSizeY());
        CorridorSide[] arr = new CorridorSide[maxHalf * 2];

        for(int i = 0; i<2; i++){
            for(int x = 0;x<room.getSizeX();x++){
                arr[i * maxHalf + x] = new CorridorSide(x, Side.North.increment(i * 2));
            }
            for(int y = 0;y<room.getSizeY();y++){
                arr[i * maxHalf + y + room.getSizeX()] = new CorridorSide(y, Side.East.increment(i * 2));
            }
        }

        return arr;
    }

    //gets a room on the other side of a corridor.
    public Room getOtherRoom(Room room, CorridorSide side, Room[][] grid){
        int x = room.x;
        int y = room.y;
        if(side.side() == Side.East || side.side() == Side.West){
            y += side.x();
        }else{
            x += side.x();
        }

        switch (side.side()){
            case North -> y-=1;
            case East -> x+=room.getSizeX();
            case South -> y+=room.getSizeY();
            case West -> x-=1;
        }
        if (x >= 0 && x < grid.length && y >= 0 && y < grid.length) {
            return grid[x][y];
        }
        return null;
    }

    /**
     * Adds a corridor on a given side. needs the grid so it knows what the room on the other side of the corridor is.
     * @param room - which room the side is relative to
     * @param side - the side of the room
     * @param grid - the grid to find the other corridor
     */
    void addCorridorFromSide(Room room, CorridorSide side, Room[][] grid){
        Room other = getOtherRoom(room, side, grid);

        CorridorSide sideB = getSideFromPosition(other,room, side);
        Corridor corridor = new TempleFrame(room, other, side, sideB);
    }

    /**Checks if a corridor already exists on that side, so we know it is not valid for new corridors
     *
     * @param room - room to check for corridors
     * @param side - side to check for corridors
     * @return - true/false depending on if other corridor exists
     */
    public boolean isExistingCorridor(Room room, CorridorSide side){
        for(Corridor a : room.corridorsTemp){
            CorridorSide side2 = a.getSide(room);
            if(side2.side() == side.side()){
                return true;
            }
        }
        return  false;
    }

    /**
     * Used to determine the side, given the room and the other room, as well as the side of the other room
     * @param room - room to get side
     * @param other - room with existing side
     * @param side - side of other room
     * @return - side of first room
     */
    public CorridorSide getSideFromPosition(Room room, Room other, CorridorSide side){

        int x = other.x;
        int y = other.y;
        if(side.side() == Side.East || side.side() == Side.West){
            y += side.x();
        }else{
            x += side.x();
        }

        switch (side.side()){
            case North -> y-=0;
            case East -> x+=other.getSizeX()-1;
            case South -> y+=other.getSizeY()-1;
            case West -> x-=0;
        }

        if(y < room.y){
            return new CorridorSide(x - room.x, Side.North);
        }else if(y >= room.y + room.getSizeY()){
            return new CorridorSide(x - room.x, Side.South);
        }else if(x < room.x){
            return new CorridorSide(y - room.y, Side.West);
        }else if(x >= room.x + room.getSizeX()){
            return new CorridorSide(y - room.y, Side.East);
        }

        throw new java.lang.Error("this is very bad");
        //xy is in room
    }

    /**
     * Generates a room. Duh.
     * @return - the starting room
     */
    Room generateRooms(){

        //A list of rooms we would like to add (although not guaranteed they will all be added)
        Room[] rooms = new Room[40];
        for(int i = 0; i<5; i++){
            rooms[i] = new BigRoom();
        }
        for(int i = 5; i<40; i++){
            rooms[i] = new Chamber();
        }


        final int templeSize = 10;
        //holds references to a room, ie, multiple blocks can hold the same reference for big rooms
        Room[][] grid = new Room[templeSize][templeSize];

        Random random = new Random();

        //give each room a position
        for(Room room : rooms){
            int sizeX = room.getSizeX();
            int sizeY = room.getSizeY();
            System.out.println(sizeX + " " + sizeY);
            //pick 10 random positions and hope they are valid
            for(int i = 0; i<100; i++){
                int x = random.nextInt(1+templeSize-sizeX);
                int y = random.nextInt(1+templeSize-sizeY);
                if(checkFree(x, y, sizeX, sizeY, grid)){
                    //If valid, assign the room to its position
                    for(int x2 = x; x2 < x+sizeX; x2++){
                        for(int y2 = y; y2 < y+sizeY; y2++){
                            grid[x2][y2] = room;
                        }
                    }
                    room.x = x;
                    room.y = y;
                    break;
                }
            }
            if(room.x < 0){
                System.out.println("warning: couldnt place room correctly.");
            }
            //If we picked 10 random positions and none of them were right, we just give up
        }

        //Assign the corridors
        for(Room room : rooms) {
            int target = room.getTargetCorridors();
            int current = room.corridorsTemp.size();
            // 4 sides of a 1x1 room, 8 sides of a 3x1 room ect..
            int max = 0;
            CorridorSide[] sides = generateSides(room);
            boolean[] valid = new boolean[sides.length];
            for(int i = 0; i<sides.length; i++) {
                if(getOtherRoom(room, sides[i], grid) != null && ! isExistingCorridor(room, sides[i])){
                    max += 1;
                    valid[i] = true;
                }else{
                    valid[i] = false;
                }
            }

            for(int i = 0; i<sides.length; i++){
                CorridorSide side = sides[i];
                if(!valid[i]){
                    continue;
                }
                if(random.nextDouble() <= (double) (target - current) / max){
                    //generate corridor
                    addCorridorFromSide(room,side, grid);
                    //auto added to the rooms
                    current += 1;
                }
                max -= 1;
            }
            if(room.corridorsTemp.size() < target){
                System.out.println("couldn't reach target!");
            }
        }

        for(Room room: rooms){
            room.finalise();
        }

        Map map = new Map();
        map.displayMap(templeSize, rooms);

        return rooms[0];
    }
}
