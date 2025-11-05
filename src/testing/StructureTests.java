package testing;

import base.Player;
import items.Stick;
import items.Torch;
import structures.Brazier;

import java.awt.*;

import static testing.Test.test;
import static testing.Test.testLog;

public class StructureTests {
    public StructureTests(){
        Brazier brazier = new Brazier();
        Player player = new Player();


        int successful = 0;

        System.out.println("name test:");
        successful += test(brazier.getName(), "brazier") ? 1:0;

        System.out.println("interaction test with stick:");
        brazier.interact(player, new Stick());
        successful += test(brazier.describe(), "This metal brazer lays dormant. It has oil if you had the tools to re-light it.") ? 1:0;

        System.out.println("interaction test with torch:");
        brazier.interact(player, new Torch());
        successful += test(brazier.describe(), "This metal brazer burns brightly, lighting up the room. It shows no signs of stopping any time soon.") ? 1:0;

        System.out.println("interaction test with stick after being lit:");

        Stick stick = new Stick();
        player.getInventory().addItem(stick);
        brazier.interact(player,stick);
        boolean test = player.getInventory().items.getFirst() instanceof Torch;
        testLog(test);
        successful += test ? 1 : 0;

        System.out.println("successful " + successful + "/4");
    }
}
