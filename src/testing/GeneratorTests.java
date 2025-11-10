package testing;

import rooms.BigRoom;
import temple.CorridorSide;
import temple.Side;
import corridors.Corridor;
import rooms.Room;
import temple.TempleGenerator;
import rooms.Chamber;

import java.util.Arrays;

//normally this would not be a feature as the fun is in the mapping, but it is here for debug purposes.
/**
 * This has a single function for displaying the map.
 * It is not a game feature, but a debug system.
 */
public class GeneratorTests {
    /**
     * Displays the map straight to the console. Intended for debugging
     * Note that it is rotated by 90 degrees counter-clockwise, so north is right
     * Theoretically, this could be used to only show discovered rooms
     * @param templeSize - size of grid, the grid itself is not needed
     * @param rooms - list of rooms to draw
     */
    public static void displayMap(int templeSize, Room[] rooms){
        char[][] output = new char[templeSize*2][templeSize*3].clone();
        //not quite sure how this works but thanks internet:
        java.util.Arrays.stream(output).forEach(row -> Arrays.fill(row, ' '));

        for(Room room : rooms) {
            if(room.x < 0){continue;}
            int startX = room.x * 2;
            int startY = room.y * 3;
            int endX = startX + room.getSizeX() * 2 - 1;
            int endY = startY + room.getSizeY() * 3 - 1;
            for(int x = startX; x<endX; x++){
                for(int y = startY; y<endY; y++){
                    if(x == startX || y == startY || x == endX-1 || y == endY-1){
                        output[x][y] = '█';
                    }else{
                        output[x][y] = '.';
                    }
                }
            }
        }


        for(Room room : rooms){
            if(room.x < 0){continue;}
            for(Corridor corridor : room.corridors){
                addCorridor(room, corridor, output);
            }
        }

        //assumes the player is in room 0
        Room start = rooms[0];
        output[start.x*2 - 1 + start.getSizeX()][start.y*3 - 1 + start.getSizeY()] = '@';

        for(char[] str : output){
            System.out.println(str);
        }
    }

    /**
     * Adds a corridor to the display map
     * note: each corridor has two rooms, and this means each corridor is drawn twice in the same place
     * @param room - room that has teh corridor
     * @param corridor - the side to add the corridor to
     * @param output - the char grid to write to
     */
    public static void addCorridor(Room room, Corridor corridor, char[][] output){
        int x = room.x;
        int y = room.y;
        CorridorSide side = corridor.getSide(room);
        if(side == null){
            return;
        }
        //System.out.println("corridor:" + side.side());
        //System.out.println(room.x + " " + room.y);
        //System.out.println(corridor.other(room).x + " " + corridor.other(room).y);
        switch (side.side()){
            case Side.North -> output[(x + side.x()) * 2][(y) * 3 - 1] = '-';
            case Side.South -> output[(x + side.x()) * 2][(y + room.getSizeY() - 1) * 3 + 2] = '-';
            case Side.West -> output[(x) * 2 - 1][(y + side.x()) * 3] = '|';
            case Side.East -> output[(x + room.getSizeX() - 1) * 2 + 1][(y + side.x()) * 3] = '|';
        }
    }

    public GeneratorTests() {
        TempleGenerator gen = new TempleGenerator();
        int passed = 0;
        int total = 0;

        System.out.println("Test 1: checkFree should return true for empty grid");
        total++;
        if (Test.test(gen.checkFree(0, 0, 2, 2, new Room[5][5]), true)) passed++;

        System.out.println("Test 2: checkFree should return false for overlapping room");
        Room[][] grid = new Room[5][5];
        Room dummy = new Chamber();
        grid[2][2] = dummy;
        total++;
        if (Test.test(gen.checkFree(1, 1, 2, 2, grid), false)) passed++;

        System.out.println("Test 3: generateSides should return non-empty array");
        Room room = new BigRoom();
        room.x = 1;
        room.y = 1;
        total++;
        if (Test.test(gen.generateSides(room).length > 0, true)) passed++;

        System.out.println("Test 4: getSideFromPosition should return North when other is above");
        Room roomA = new Chamber();
        Room roomB = new Chamber();
        roomA.x = 2; roomA.y = 2;
        roomB.x = 2; roomB.y = 1;
        CorridorSide sideFromB = new CorridorSide(0, Side.South);
        CorridorSide sideFromA = gen.getSideFromPosition(roomA, roomB, sideFromB);
        total++;
        if (Test.test(sideFromA.side(), Side.North)) passed++;

        System.out.println("Test 5: isExistingCorridor should return false for empty corridor list");
        Room roomC = new Chamber();
        roomC.x = 0; roomC.y = 0;
        CorridorSide side = new CorridorSide(0, Side.East);
        total++;
        if (Test.test(gen.isExistingCorridor(roomC, side), false)) passed++;

        System.out.println("Test 6: getOtherRoom should return correct neighbor");
        Room[][] grid2 = new Room[5][5];
        Room center = new Chamber();
        center.x = 2; center.y = 2;
        grid2[2][2] = center;
        Room neighbor = new Chamber();
        neighbor.x = 3; neighbor.y = 2;
        grid2[3][2] = neighbor;
        CorridorSide eastSide = new CorridorSide(0, Side.East);
        total++;
        if (Test.test(gen.getOtherRoom(center, eastSide, grid2) == neighbor, true)) passed++;

        System.out.println("successful: " + passed + " / " + total + "\n");
    }
}
