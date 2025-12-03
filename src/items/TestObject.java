package items;

import base.Interactable;
import base.Player;
import rooms.TestRoom;
import structures.BreakerSwitch;
import structures.Elevator;

public class TestObject implements Interactable {
    public String getName() {
        return "Test object";
    }

    public String describe() {
        return "This is a object you shouldn't be seeing. Oops.";
    }

    public boolean interact(Player player, Item other) {
        return false;
    }

    public boolean additionalFunctionality(){

        //Works fine, since TestObject implements Interactable
        Interactable object = new TestObject();

        //Rooms work fine too, since they also implement Interactable
        object = new TestRoom(0, 0);

        //So does flashlight, since it inherits from Item
        object = new Flashlight();

        //So does breakerSwitch, since it inherits from Structure
        object = new BreakerSwitch();

        return false;
    }
}
