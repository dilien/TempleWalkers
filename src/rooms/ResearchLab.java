package rooms;

import structures.Container;
import structures.ContainerType;

public class ResearchLab extends Room{
    public ResearchLab() {
        super(2, 2);
        //1-3 cupboards
        for(int i = 0; i<Math.random()*3 + 1; i++){
            this.structs.add(new Container(ContainerType.cupboard));
        }
    }

    public void enterRoom() {
    }

    public String getName() {
        return "Research Lab";
    }

    public String describe() {
        return "Upturned chairs scatter the room. Half-finished equations cover whiteboards, their meanings lost in hasty scribbles.";
    }

}
