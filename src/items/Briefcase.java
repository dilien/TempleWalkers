package items;

import base.Console;
import base.Player;
import temple.Temple;

public class Briefcase extends Score {
    public String getName() {
        return "Briefcase - " + score;
    }

    public String describe() {
        return "You can put research papers in the briefcase to stop them taking up so much inventory space.";
    }

    public boolean interact(Player player, Item other) {
        if (tryPickup(player)) {
            return true;
        }
        if (other instanceof Document document) {
            Console.getInstance().displayText("You meticulously place the paper into the briefcase, making sure it follows the sorting system. This takes some time.");
            score += document.score;
            document.delete();
            Temple.getInstance().tick();
            return true;
        }
        if (other instanceof Briefcase briefcase) {
            Console.getInstance().displayText("You move all the contents of one briefcase into another.");
            score += briefcase.score;
            briefcase.score = 0;
            return true;
        }
        return false;
    }
}
