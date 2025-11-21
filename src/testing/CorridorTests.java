package testing;

import base.Vector2;
import corridors.Corridor;
import corridors.TempleFrame;
import items.Keycard;
import items.OxygenCanister;
import rooms.Chamber;
import temple.PositionSide;
import base.Player;

import java.util.Arrays;

public class CorridorTests {
    public CorridorTests() {
        int passed = 0;
        int total = 0;

        Chamber roomA = new Chamber();
        roomA.setPosition(0, 0);

        Chamber roomB = new Chamber();
        roomB.setPosition(0, 1);

        PositionSide side = new PositionSide(new Vector2(0, 0), false);
        Corridor corridor = new TempleFrame(roomA, roomB, side);

        System.out.println("Test 1: Corridor should link two rooms");
        total++;
        boolean correctLink = corridor.other(roomA) == roomB && corridor.other(roomB) == roomA;
        if (Test.test(correctLink, true)) passed++;


        System.out.println("Test 2: Corridor interaction should move player to other room");
        total++;
        Player player = new Player();
        player.enterRoom(roomA);
        boolean interacted = corridor.interact(player, new OxygenCanister());
        if (Test.test(player.getRoom() == roomB, true)) passed++;

        System.out.println("Test 3: Corridor should be added to both rooms' temp lists");
        total++;
        boolean addedToRooms = Arrays.stream(roomA.corridors).toList().contains(corridor) && Arrays.stream(roomB.corridors).toList().contains(corridor);
        if (Test.test(addedToRooms, true)) passed++;

        System.out.println("\nscore " + passed + "/" + total + "\n");
    }
}