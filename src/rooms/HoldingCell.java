package rooms;

import items.Item;
import structures.AdvancedContainer;
import structures.Container;
import structures.ContainerType;

public class HoldingCell extends Room{
    public HoldingCell() {
        super( 2, 1);
        this.structs.add(new Container(ContainerType.biohazardbox));
    }

    public void enterRoom() {

    }

    public String getName() {
        return "Biology supply closet";
    }

    public String describe() {
        return "This small room has pipes containing genetic fluid running all over the place. It doesn't look very OSHA compliant.";
    }

    public boolean generateRareLoot(Item item){
        AdvancedContainer container = new AdvancedContainer(ContainerType.containmentPod, "biology");
        container.item = item;
        this.structs.add(container);
        return true;
    }
}