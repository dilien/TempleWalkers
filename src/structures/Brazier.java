package structures;

import base.Interactable;
import items.Item;

public class Brazier extends Structure{
    boolean onfire = false;

    public String getName() {
        return "brazier";
    }

    public String describe() {
        if(onfire){
            return "This metal brazer burns brightly, lighting up the room. It shows no signs of stopping any time soon.";
        }else{
            return "This metal brazer lays dormant. It has oil if you had the tools to re-light it.";
        }
    }
}
