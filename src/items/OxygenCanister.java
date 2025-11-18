package items;

import base.Player;

public class OxygenCanister extends Item{
    public String getName() {
        return "Oxygen Canister";
    }

    public String describe() {
        return "A fairly big oxygen canister with a lot of warning signs on. In big text it reads 'Approx. 50 turns worth'. What kind of unit is that?";
    }

    public boolean interact(Player player, Item other) {
        if(other != null){
            return false;
        }
        player.refillOxygen();
        player.getInventory().removeItem(this);
        return true;
    }
}
