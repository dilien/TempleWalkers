package items;

import base.Interactable;

public class Gold extends Item {
    int coins = 0;

    public String getName() {
        return String.valueOf(coins) + " gold coins";
    }

    public String describe(){
        return "The gold coins glisten in your hand. They are unfortunately not made of chocolate.";
    }

    public boolean interact(Interactable other) {
        return false;
    }

    public void addGold(int gold){
        coins += gold;
    }
}
