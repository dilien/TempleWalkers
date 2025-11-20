package rooms;

import base.Console;
import base.Inventory;
import corridors.Corridor;
import structures.Container;
import structures.ContainerType;
import temple.Temple;

import java.util.Random;

public class MaintenanceTunnel extends Room{
    public MaintenanceTunnel() {
        super (1, 4);
        //0-2 toolboxes
        for(int i = 0; i<Math.random()*3; i++){
            this.structs.add(new Container(ContainerType.toolbox));
        }
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
