package rooms;

import base.Console;
import base.Inventory;
import corridors.Corridor;
import temple.Temple;

import java.util.Random;

public class MaintenanceTunnel extends Room{
    public MaintenanceTunnel() {
        super (1, 4);
    }
    public void enterRoom() {
        Console console = Console.getInstance();
        console.displayText("The hot steam blocks your view and makes it harder to move.");
        Temple temple = Temple.getInstance();
        temple.tick();
    }

    public String getName() {
        return "Maintenance Tunnel";
    }

    public String describe() {
        return "The low ceiling forces you to hunch. Pipes leak steam at irregular intervals, hissing in the darkness.";
    }

}
