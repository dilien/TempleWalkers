package structures;

import base.Console;
import base.Player;
import items.Item;
import temple.Temple;

public class BreakerSwitch extends Structure {
    public String getName() {
        return "Breaker Switch";
    }

    public String describe() {
        return "You can use this to turn the power back on when the lights go out.";
    }

    public boolean interact(Player player, Item other) {
        if (other != null) {
            return false;
        }
        Temple temple = Temple.getInstance();
        if (temple.dark) {
            Console.getInstance().displayText("You pull the switch down and after a few seconds the lights turn back on again.");
            temple.resetLights();
        } else {
            Console.getInstance().displayText("You pull the switch down but it does nothing. Maybe it will do more when the lights turn off.");
        }
        return true;
    }
}
