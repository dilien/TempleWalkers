package rooms;

import structures.BreakerSwitch;
import structures.Container;
import structures.ContainerType;

public class SupplyCloset extends Room{
    public SupplyCloset() {
        super(1, 1);
        //0-1 cupboards
        this.structs.add(new BreakerSwitch());
        for(int i = 0; i<Math.random()*1; i++){
            this.structs.add(new Container(ContainerType.cupboard));
        }
    }

    public String getName() {
        return "Supply Closet";
    }

    public String describe() {
        return "A small supply closet, and the gentle hum of the breaker switch.";
    }
}
