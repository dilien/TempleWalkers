package items;

import base.Console;
import base.Player;

public class Flashlight extends Item implements Rechargable{
    int charge;
    public int getMaxCharge() {
        return 29;
    }
    public int getCharge() {
        return charge;
    }
    public void recharge() {
        charge = getMaxCharge();
    }

    public boolean active;
    public String getName() {
        float charge = (float) getCharge() /getMaxCharge();
        return "Flashlight " + (active ? "(on)" : "(off)") + " - " + (int)(charge*100) + "% left";
    }

    public String describe() {
        return "A sturdy facility-issue flashlight. If the lights were to go out, this would help you navigate to the nearest breaker switch.";
    }

    void setActive(boolean active){
        this.active = active;
    }

    public boolean interact(Player player, Item other) {
        if(tryPickup(player)){return true;}
        Console console = Console.getInstance();
        if(other instanceof Battery){
            other.delete();
            recharge();
            console.displayText("You replace the batteries in the flashlight.");
            return true;
        } else if (other != null) {
            return false;
        }
        setActive(!active);
        console.displayText("You turn the flashlight " + (active?"on":"off"));
        return true;
    }

    public void tick() {
        if(active){
            charge--;
            if(charge==0){
                setActive(false);
            }
        }
    }
}
