package items;

import base.Console;
import base.Player;

public class OxygenCanister extends Item{
    public String getName() {
        return "Oxygen Canister";
    }

    public String describe() {
        return "A fairly big oxygen canister with a lot of warning signs on. In big text it reads 'Approx. 30 turns worth'. What kind of unit is that?";
    }

    public boolean interact(Player player, Item other) {
        //on pickup but full then use instead
        //on pickup and not full, then pass
        if(super.interact(player, other) && this.parent == player.getInventory()){
            return true;
        };
        if(other != null){
            return false;
        }
        player.refillOxygen();
        player.getInventory().removeItem(this);
        Console.getInstance().displayText("You replace your old oxygen canister, and your meter is refilled.");
        return true;
    }
}
