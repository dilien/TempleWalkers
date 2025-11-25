package testing;

import base.*;
import corridors.Corridor;
import corridors.HiddenCorridor;
import items.Item;
import items.Keycard;
import rooms.Room;
import rooms.TestRoom;
import structures.Container;
import structures.ContainerType;
import temple.PositionSide;

public class RoomTest extends Test {

    public static void main(String[] args) {
        testRoomSize();
        testRoomPosition();
        testCorridorManagement();
        testStructureManagement();
        testInventoryManagement();
        testGlobalToLocalSideConversion();
    }

    private static void testRoomSize() {
        System.out.println("constructor test:");
        TestRoom room = new TestRoom(3, 4);
        boolean notRotated = room.getSizeX() == 3 && room.getSizeY() == 4;
        boolean rotated = room.getSizeX() == 4 && room.getSizeY() == 3;

        test(notRotated || rotated, true);
    }

    private static void testRoomPosition() {
        System.out.println("setPosition test:");
        TestRoom room = new TestRoom(2, 2);
        room.setPosition(5, 5);
        test(room.x, 5);
        test(room.y, 5);
    }

    private static void testCorridorManagement() {
        System.out.println("corridor management test:");
        TestRoom roomA = new TestRoom(2, 2);
        TestRoom roomB = new TestRoom(2, 2);
        roomA.setPosition(1, 1);
        roomB.setPosition(3, 1);

        PositionSide side = new PositionSide(new Vector2(2, 1).wrap(), true);
        Corridor corridor = new HiddenCorridor(roomA, roomB, side);

        // Test if corridors were added to both rooms
        test(roomA.corridors[roomA.globalSideToLocal(side)] != null, true);
        test(roomB.corridors[roomB.globalSideToLocal(side)] != null, true);

        // Test that the corridor connects to the correct rooms
        test(corridor.other(roomA) == roomB, true);
        test(corridor.other(roomB) == roomA, true);
    }

    private static void testStructureManagement() {
        System.out.println("structures tests:");
        TestRoom room = new TestRoom(2, 2);
        Container container = new Container(ContainerType.cupboard);

        room.structs.add(container);
        test(room.structs.size(), 1);
        test(room.structs.contains(container), true);
    }

    private static void testInventoryManagement() {
        System.out.println("room items test:");
        TestRoom room = new TestRoom(2, 2);
        Player player = new Player();

        // Test room interaction with null item (should fail)
        test(room.interact(player, null), false);

        Item item = new Keycard(3);
        item.interact(player, null); //picks up item
        test(room.interact(player, item), true);
        test(room.inventory.items.contains(item), true);
    }

    private static void testGlobalToLocalSideConversion() {
        System.out.println("global to local side conversion:");
        TestRoom room = new TestRoom(2, 2);
        room.setPosition(1, 1);

        PositionSide globalSide = new PositionSide(new Vector2(0, 1).wrap(), true);
        int localSide = room.globalSideToLocal(globalSide);
        test(localSide >= 0, true);

        // Test conversion back to global
        PositionSide convertedGlobal = room.localSideToGlobal(localSide);
        test(convertedGlobal.equals(globalSide), true);
    }
}