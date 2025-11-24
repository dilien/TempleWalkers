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

    public String getName() {
        return "Research Lab";
    }

    public String describe() {
        return "A sterile laboratory filled with expensive equipment and sticky notes. Most monitors display error messages, except one playing some kind of console based adventure game. How nerdy.";
    }

}
