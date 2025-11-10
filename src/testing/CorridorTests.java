package testing;

import corridors.Corridor;
import corridors.TempleFrame;
import rooms.Chamber;
import temple.CorridorSide;
import temple.Side;
import base.Player;
import items.Stick;
import testing.Test;

public class CorridorTests {
    public CorridorTests() {
        int passed = 0;
        int total = 0;

        Chamber roomA = new Chamber();
        Chamber roomB = new Chamber();
        CorridorSide sideA = new CorridorSide(0, Side.North);
        CorridorSide sideB = new CorridorSide(0, Side.South);
        Corridor corridor = new TempleFrame(roomA, roomB, sideA, sideB);

        System.out.println("Test 1: Corridor should link two rooms");
        total++;
        boolean correctLink = corridor.other(roomA) == roomB && corridor.other(roomB) == roomA;
        if (Test.test(correctLink, true)) passed++;

        System.out.println("Test 2: Corridor should return correct side for each room");
        total++;
        boolean correctSides = corridor.getSide(roomA) == sideA && corridor.getSide(roomB) == sideB;
        if (Test.test(correctSides, true)) passed++;

        System.out.println("Test 3: Corridor interaction should move player to other room");
        total++;
        Player player = new Player();
        player.enterRoom(roomA);
        boolean interacted = corridor.interact(player, new Stick());
        if (Test.test(player.getRoom() == roomB, true)) passed++;

        System.out.println("Test 4: Corridor should be added to both rooms' temp lists");
        total++;
        boolean addedToRooms = roomA.corridorsTemp.contains(corridor) && roomB.corridorsTemp.contains(corridor);
        if (Test.test(addedToRooms, true)) passed++;

        System.out.println("\nscore " + passed + "/" + total + "\n");
    }
}