package items;

import base.Interactable;

public class Stick extends Item{

    public String getName() {
        return "stick";
    }

    public String describe() {
        return "A long wooden stick. Its not a lot, but you can feel the potential that this stick is offering.";
    }

    public boolean interact(Interactable other) {
        return false;
    }
}
