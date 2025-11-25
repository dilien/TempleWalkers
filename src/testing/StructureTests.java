package testing;

import base.*;
import items.Battery;
import items.Item;
import structures.Container;
import structures.ContainerType;

public class StructureTests extends Test {
    public static void main(String[] args) {
        DifficultyManager.difficulty = Difficulty.Medium;
        testContainerName();
        testContainerDescription();
        testContainerInteraction();
        testEmptyContainerInteraction();
        testAlreadyLootedInteraction();
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
        Item item = new Battery();
        container.item = item;

        Player player = new Player();
        container.interact(player, null); //loots and gets item

        Item item2 = new Battery();
        container.item = item2;
        container.interact(player, null); //loots again and should not work

        test(item2.parent == player.getInventory(), false);
    }
}