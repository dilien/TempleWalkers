package testing;

import base.Player;
import items.*;

public class ItemsTest extends Test {
    public static void main(String[] args) {
        testArtifact();
        testBriefcase();
        testDocument();
        testEmployeeID();
        testFlashlight();
        testKeycard();
        testOxygenCanister();
    }

    private static void testArtifact() {
        System.out.println("Artifact tests:");
        Artefact artefact = new Artefact(100);

        // Test score incorporation
        test(artefact.getName().endsWith("- 100"), true);

        // Test description format
        String desc = artefact.describe();
        test(desc.startsWith("The "), true);
        test(desc.contains(" and "), true);
        test(desc.endsWith("Score: 100"), true);
    }

    private static void testBriefcase() {
        System.out.println("Briefcase tests");
        Briefcase briefcase = new Briefcase();
        Document document = new Document(50);
        Player player = new Player();
        briefcase.parent = player.getInventory();

        //test it absorbs the document score
        test(briefcase.interact(player, document), true);
        test(briefcase.score, 50);

        //test it absorbs the other briefcase score
        Briefcase briefcase2 = new Briefcase();
        briefcase2.score = 30;
        test(briefcase.interact(player, briefcase2), true);
        test(briefcase.score, 80);
        test(briefcase2.score, 0);
    }

    private static void testDocument() {
        System.out.println("Document tests");
        Document doc = new Document(75);

        //Test score is included in the name
        test(doc.getName().endsWith("- 75"), true);

        //description auto generated matches format
        String desc = doc.describe();
        test(desc.startsWith("The paper:"), true);
        test(desc.contains("resulting in"), true);
        test(desc.endsWith("Score: 75"), true);
    }

    private static void testEmployeeID() {
        System.out.println("EmployeeID tests");
        EmployeeID id = new EmployeeID("12345", "Research");

        //test that the employee ID name matches the constructor
        test(id.getName(), "ID number: 12345");
        test(id.department, "Research");
        test(id.id, "12345");
        test(id.describe().contains("Research"), true);
    }

    private static void testFlashlight() {
        System.out.println("Flashlight tests:");
        Flashlight flashlight = new Flashlight();
        Player player = new Player();
        flashlight.parent = player.getInventory();

        //test default values
        test(flashlight.active, false);
        test(flashlight.getCharge(), 0);

        //test battery works
        Battery battery = new Battery();
        test(flashlight.interact(player, battery), true);
        test(flashlight.getCharge(), flashlight.getMaxCharge());

        //test can be toggled
        test(flashlight.interact(player, null), true);
        test(flashlight.active, true);

        //test depletion
        flashlight.tick();
        test(flashlight.getCharge(), flashlight.getMaxCharge() - 1);

        //test auto turn off
        while(flashlight.getCharge() > 0){
            flashlight.tick();
        }
        test(flashlight.active, false);
    }

    private static void testKeycard() {
        System.out.println("Keycard tests:");
        Keycard card = new Keycard(3);

        //test data matches constructor
        test(card.level, 3);
        test(card.getName(), "Level 3 key-card");
        test(card.describe().contains("level 3"), true);
    }

    private static void testOxygenCanister() {
        System.out.println("Oxygen canister tests:");
        OxygenCanister canister = new OxygenCanister();
        Player player = new Player();
        player.tick(); //reduce starting oxygen

        //text oxygen refill
        player.getInventory().addItem(canister);
        test(canister.interact(player, null), true);
        test(player.getOxygen(), 30);
        test(player.getInventory().items.contains(canister), false);
    }
}