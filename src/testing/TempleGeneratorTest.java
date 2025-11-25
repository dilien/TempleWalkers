package testing;

import base.Vector2;
import corridors.HiddenCorridor;
import rooms.Room;
import rooms.TestRoom;
import temple.PositionSide;
import temple.Temple;
import temple.TempleGenerator;

public class TempleGeneratorTest extends Test {

    public static void main(String[] args) {
        testCheckFree();
        testIsExistingCorridor();
        testGetOtherRoom();
        testCheckConnectivity();
    }

    private static void testCheckFree() {
        System.out.println("testing checkFree:");
        TempleGenerator generator = new TempleGenerator();
        Room[][] grid = new Room[Temple.size][Temple.size];

        //test free space
        test(generator.checkFree(0, 0, 2, 2, grid), true);

        //test not free space
        Room room = new TestRoom(2, 2);
        grid[1][1] = room;
        test(generator.checkFree(0, 0, 3, 3, grid), false);

        //test at grid edge (should still pass due to map wrapping)
        test(generator.checkFree(9, 9, 2, 2, grid), true);
    }

    private static void testIsExistingCorridor() {
        System.out.println("testing isExistingCorridor():");
        TempleGenerator generator = new TempleGenerator();
        Room room = new TestRoom(2, 2);
        room.setPosition(1, 1);

        PositionSide side = new PositionSide(new Vector2(0, 1).wrap(), true);
        test(generator.isExistingCorridor(room, side), false);
    }

    private static void testGetOtherRoom() {
        System.out.println("testing getOtherRoom():");
        TempleGenerator generator = new TempleGenerator();
        Room[][] grid = new Room[10][10];

        Room room1 = new TestRoom(1, 1);
        Room room2 = new TestRoom(1, 1);

        grid[1][1] = room1;
        grid[2][1] = room2;

        room1.setPosition(1,1);
        room2.setPosition(2,1);

        PositionSide side = new PositionSide(new Vector2(1, 1).wrap(), true);
        Room otherRoom = generator.getOtherRoom(grid, room1, side);
        test(otherRoom == room2, true);
    }

    private static void testCheckConnectivity() {
        System.out.println("testing room connectivity check");
        TempleGenerator generator = new TempleGenerator();

        //two connected rooms
        Room room1 = new TestRoom(1, 1);
        Room room2 = new TestRoom(1, 1);
        room1.setPosition(0, 0);
        room2.setPosition(1, 0);

        //create corridor
        PositionSide side = new PositionSide(new Vector2(0, 0).wrap(), true);
        new HiddenCorridor(room1, room2, side);

        Room[] rooms = new Room[]{room1, room2};
        test(generator.checkConnectivity(room1, 2), true);

        //add new unconnected room
        Room room3 = new TestRoom(1, 1);
        room3.setPosition(1, 1);
        test(generator.checkConnectivity(room1, 3), false);

    }
}