package testing;

import base.*;
import items.Battery;
import items.EmployeeID;
import items.Item;
import items.Keycard;
import structures.*;
import temple.Temple;

public class StructureTests extends Test {
    public static void main(String[] args) {
        testContainerName();
        testContainerDescription();
        testContainerInteraction();
        testEmptyContainerInteraction();
        testAlreadyLootedInteraction();
        testAdvancedContainer();
        testBreakerSwitch();
        testElevator();
    }

    private static void testContainerName() {
        System.out.println("name change after loot:");
        Container container = new Container(ContainerType.cupboard);
        test(container.getName(), "Cupboard");
        container.interact(new Player(), null); // loot it
        test(container.getName(), "Empty Cupboard");
    }

    private static void testContainerDescription() {
        System.out.println("description change after loot:");
        Container container = new Container(ContainerType.toolbox);
        test(container.describe(), ContainerType.toolbox.description() + " You have not searched through it yet.");
        container.interact(new Player(), null); // loot it
        test(container.describe(), ContainerType.toolbox.description() + " It has been searched through already.");
    }

    private static void testContainerInteraction() {
        System.out.println("looting container test:");
        Container container = new Container(ContainerType.gunLocker);
        Item item = new Battery();
        container.item = item;

        Player player = new Player();
        test(container.interact(player, null), true);
        test(item.parent == player.getInventory(), true);
    }

    private static void testEmptyContainerInteraction() {
        System.out.println("looting empty container test:");
        Container container = new Container(ContainerType.containmentPod);

        Player player = new Player();
        test(container.interact(player, null), true);
    }

    private static void testAlreadyLootedInteraction() {
        System.out.println("looting an already looted container:");
        Container container = new Container(ContainerType.biohazardbox);
        container.item = new Battery();

        Player player = new Player();
        container.interact(player, null); //loots and gets item

        Item item2 = new Battery();
        container.item = item2;
        container.interact(player, null); //loots again and should not work

        test(item2.parent == player.getInventory(), false);
    }

    static void testAdvancedContainer() {
        System.out.println("Advanced cupboard tests:");
        ContainerType type = ContainerType.cupboard;
        AdvancedContainer ac = new AdvancedContainer(type, "HR");

        // getName contains type name
        test(true, ac.getName().contains(type.name()));

        // describe before looting
        String desc = ac.describe();
        test(true, desc.contains("requires an ID"));

        // interact with wrong item
        Player p = new Player();
        Item wrong = new Battery();
        test(false, ac.interact(p, wrong));

        // interact with wrong ID
        EmployeeID badID = new EmployeeID("WRONG", "HR");
        test(true, ac.interact(p, badID)); // returns true but denies access

        // interact with correct ID
        EmployeeID goodID = new EmployeeID(ac.id, "HR");
        test(true, ac.interact(p, goodID));
        test(true, ac.looted);
    }

    static void testBreakerSwitch() {
        System.out.println("Breaker switch tests:");
        BreakerSwitch bs = new BreakerSwitch();
        test("Breaker Switch", bs.getName());
        test(true, bs.describe().contains("turn the power"));

        Player p = new Player();
        Item dummy = new Battery();

        // wrong item
        test(false, bs.interact(p, dummy));

        // lights off
        Temple.getInstance().dark = true;
        test(true, bs.interact(p, null));

        // lights on
        Temple.getInstance().dark = false;
        test(true, bs.interact(p, null));
    }

    static void testElevator() {
        System.out.println("Elevator tests:");
        Elevator e = new Elevator();
        e.level = 2;

        test("Surface Elevator number 2", e.getName());
        test(true, e.describe().contains("level 2"));

        Player p = new Player();

        // null item
        test(false, e.interact(p, null));

        // wrong item
        Item rock = new Battery();
        test(true, e.interact(p, rock));

        // insufficient keycard
        Keycard low = new Keycard(1);
        test(true, e.interact(p, low));
        test(false, p.end);

        // sufficient keycard
        Keycard high = new Keycard(2);
        test(true, e.interact(p, high));
        test(true, p.end);
    }
}