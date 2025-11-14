package rooms;

import base.Inventory;
import corridors.Corridor;

import java.util.Random;

public class MaintenanceTunnel extends Room{
    public MaintenanceTunnel() {
        super (1, 4);
    }
    public void enterRoom() {
    }

    public String getName() {
        return "Maintenance Tunnel";
    }

    public String describe() {
        return "The low ceiling forces you to hunch. Pipes leak steam at irregular intervals, hissing in the darkness.";
    }

}
