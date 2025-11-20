package rooms;

import items.Item;
import structures.AdvancedContainer;
import structures.Container;
import structures.ContainerType;
import structures.Elevator;

public class SecurityCheckpoint extends Room{
    public static int currentElevator = 1; //used to make sure there is one of each.
    public SecurityCheckpoint() {
        super(3, 2);
        Elevator elevator = new Elevator();
        elevator.level = currentElevator;
        currentElevator++;
        this.structs.add(elevator);

        //0-2 cupboards
        for(int i = 0; i<Math.random()*3; i++){
            this.structs.add(new Container(ContainerType.cupboard));
        }
    }

    public void enterRoom() {
    }

    public String getName() {
        return "Security Checkpoint";
    }

    public String describe() {
        return "Sirens blare and flash red near this security checkpoint. It contains an elevator to exit this facility.";
    }

    public boolean generateRareLoot(Item item){
        AdvancedContainer container = new AdvancedContainer(ContainerType.gunLocker, "security");
        container.item = item;
        this.structs.add(container);
        return true;
    }

}
