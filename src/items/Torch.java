package items;

import base.Player;

public class Torch extends Item{
    public int turnsLeft;

    public Torch(){
        super();
        turnsLeft = 50;
    }

    public String getName() {
        return "torch";
    }

    public String describe() {
        String text;
        if(turnsLeft > 40){
            text = "It has plenty of stick left.";
        } else if (turnsLeft > 30) {
            text = "It has most of the stick left.";
        }else if (turnsLeft > 20) {
            text = "It has some of the stick left.";
        }else if (turnsLeft > 10) {
            text = "It is starting to run out of stick.";
        }else{
            text = "It is almost extinguished.";
        }
        return "This improvised torch burns quickly. " + text;
    }

    public void tick(){
        turnsLeft -= 1;
        if(turnsLeft == 0){
            this.parent.removeItem(this);
            //todo: re-implement this player log (though it is made more difficult by player not being a singleton)
            //player.displayText("Your torch has finally extinguished, and the entire stick has been burnt.");
        }
    }
}
