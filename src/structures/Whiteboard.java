package structures;

import base.Player;
import com.sun.source.tree.WhileLoopTree;
import items.Item;

public class Whiteboard extends Structure{
    boolean read = false;
    String content;
    public Whiteboard(String content){
        this.content = content;
    }

    public String getName() {
        return (read ? "Read" : "Unread") + " Whiteboard";
    }

    public String describe() {
        read = true;
        return this.content;
    }

    public boolean interact(Player player, Item other) {
        return false;
    }
}
