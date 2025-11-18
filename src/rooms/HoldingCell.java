package rooms;

import items.Item;
import structures.ContainmentPod;

public class HoldingCell extends Room{
    public HoldingCell() {
        super( 1, 1);
    }

    public void enterRoom() {

    }

    public String getName() {
        return "Holding Cell";
    }

    public String describe() {
        return "This small room has pipes containing genetic fluid running all over the place. It doesn't look very OSHA compliant.";
    }

    public boolean generateRareLoot(Item item){
        this.structs.add(new ContainmentPod(item));
        return true;
    }
}