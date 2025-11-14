package rooms;

import base.Interactable;

public class Chamber extends Room{
    public Chamber() {
        super(1, 1);
    }

    public void enterRoom() {
    }

    public String getName() {
        return "chamber";
    }

    public String describe() {
        return "This chamber is a basic room, with any decorations decayed away";
    }

}
