package rooms;

import items.Item;
import structures.AdvancedContainer;
import structures.Container;
import structures.ContainerType;

public class SecurityCheckpoint extends Room{
    public SecurityCheckpoint() {
        super(3, 2);

        //0-2 cupboards
        for(int i = 0; i<Math.random()*3; i++){
            this.structs.add(new Container(ContainerType.cupboard));
        }
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
