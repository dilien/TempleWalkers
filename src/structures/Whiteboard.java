package structures;

import base.Player;
import com.sun.source.tree.WhileLoopTree;
import items.Item;

public class Whiteboard extends Structure{
    String content;
    public Whiteboard(String content){
        this.content = content;
    }

    public String getName() {
        return "Whiteboard";
    }

    public String describe() {
        return this.content;
    }

    public boolean interact(Player player, Item other) {
        return false;
    }
}
