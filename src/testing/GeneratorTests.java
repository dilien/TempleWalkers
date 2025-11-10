package testing;

import rooms.BigRoom;
import temple.CorridorSide;
import temple.Side;
import corridors.Corridor;
import rooms.Room;
import temple.TempleGenerator;
import rooms.Chamber;

import java.util.Arrays;

/**
 * Some tests for the map to make sure individual functions work correctly generator
 */
public class GeneratorTests {

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
