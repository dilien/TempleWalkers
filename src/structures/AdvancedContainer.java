package structures;

import base.Console;
import base.Player;
import items.EmployeeID;
import items.Item;
import temple.Temple;

import java.util.Objects;

public class AdvancedContainer extends Container{

    String id;
    public AdvancedContainer(ContainerType type, String department) {
        super(type);

        String letters = "" + (char)(65 + Math.random() * 26) + (char)(65 + Math.random() * 26);
        String numbers = "" + (int)(Math.random() * 10) + (int)(Math.random() * 10);
        id = letters + "-" + numbers;

        //Make sure the correct ID is hidden in the facility somewhere.
        EmployeeID ID = new EmployeeID(id, department);
        Temple.getInstance().lootManager.itemsToAdd.add(ID);
    }

    public String describe() {
        return myType.description() + (looted ? " It has been searched through already." : "It requires an ID to open " +
                (item == null ? ", although it looks to be empty." : ". It contains a " + item.getName() + "."));
    }

    public boolean interact(Player player, Item other) {
        Console console = Console.getInstance();
        if(looted){
            console.displayText("This " + this.getName() + "has already been looted");
        } else if (other != null) {
            if(! (other instanceof EmployeeID)){
                return  false;
            }
            if(!Objects.equals(((EmployeeID) other).id, id)){
                Console.getInstance().displayText("The ID scans correctly, but is rejected. ACCESS DENIED.");
            }
            if(item != null){
                console.displayText("You search through the " + this.getName() + " and find a " + item.getName() + "!");
            }else{
                //this should probably not print, as all advanced containers should have an item.
                console.displayText("You search through the " + this.getName() + " but find nothing. What a waste of time.");
            }
            looted = true;
            Temple.getInstance().tick();
        }else{
            Console.getInstance().displayText("You need an employee ID to open this " + this.getName() + ".");
        }
        return true;

    }
}
