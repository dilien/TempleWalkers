package structures;

import base.Console;
import base.Player;
import items.Item;
import temple.Temple;

public class Container extends Structure {
    protected final ContainerType myType;
    public  Container(ContainerType type){
        myType = type;
    }

    protected boolean looted = false;
    public Item item;

    public String getName() {
        return (looted ? "Empty " : "") + myType.name();
    }

    public String describe() {
        return myType.description() + (looted ? " It has been searched through already." : " You have not searched through it yet.");
    }

    public boolean interact(Player player, Item other) {
        Console console = Console.getInstance();
        if(looted){
            console.displayText("This has already been looted");
            return true;
        } else if (other != null) {
            return false;
        }else{
            if(item != null){
                console.displayText("You search through the " + myType.name() + " and find a " + item.getName() + "!");
            }else{
                console.displayText("You search through the " + myType.name() + " but find nothing");
            }
            looted = true;
            Temple.getInstance().tick();
            return true;
        }
    }
}
