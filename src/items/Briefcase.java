package items;

import base.Player;

public class Briefcase extends Score{
    public String getName() {
        return "Briefcase - " + score;
    }

    public String describe() {
        return "You can put research papers in the briefcase to stop them taking up so much inventory space.";
    }

    public boolean interact(Player player, Item other) {
        if(super.interact(player, other)){return true;}
        if(other instanceof Document document){
            score += document.score;
            document.delete();
            return true;
        }if(other instanceof Briefcase briefcase){
            score += briefcase.score;
            briefcase.score = 0;
            return true;
        }
        return false;
    }
}
