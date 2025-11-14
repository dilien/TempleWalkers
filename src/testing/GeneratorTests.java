package testing;

import base.Vector2;
import temple.PositionSide;
import rooms.Room;
import temple.TempleGenerator;
import rooms.Chamber;

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

        System.out.println("Test 3: isExistingCorridor should return false for empty corridor list");
        Room roomC = new Chamber();
        roomC.x = 0; roomC.y = 0;
        PositionSide side = new PositionSide(new Vector2(0, 0), false);
        total++;
        if (Test.test(gen.isExistingCorridor(roomC, side), false)) passed++;

        System.out.println("Test 4: getOtherRoom should return correct neighbor");
        Room[][] grid2 = new Room[5][5];
        Room center = new Chamber();
        center.setPosition(2, 2);
        grid2[2][2] = center;
        Room neighbor = new Chamber();
        center.setPosition(3, 2);
        grid2[3][2] = neighbor;
        PositionSide eastSide = new PositionSide(new Vector2(2, 2), true);
        total++;
        if (Test.test(gen.getOtherRoom(grid2, center, eastSide) == neighbor, true)) passed++;

        System.out.println("successful: " + passed + " / " + total + "\n");
    }
}
