package structures;

import base.Console;
import base.Player;
import items.EmployeeID;
import items.Item;
import temple.Temple;

import java.util.Objects;

public class ContainmentPod extends Structure{
    Item contents;
    boolean released = false;
    String id;
    public ContainmentPod(){
        String letters = "" + (char)(65 + Math.random() * 26) + (char)(65 + Math.random() * 26);
        String numbers = "" + (int)(Math.random() * 10) + (int)(Math.random() * 10);
        id = letters + "-" + numbers;

        //Make sure the correct ID is hidden in the facility somewhere.
        EmployeeID ID = new EmployeeID(id, "Biology");
        Temple.getInstance().itemsToAdd.add(ID);
    }

    public String getName() {
        return "Containment Pod " + id;
    }

    public String describe() {
        return "You cant really see what is in the pod. Its all fogged up. There is a scanner for an ID.";
    }

    public boolean interact(Player player, Item other) {
        if(released){
            Console.getInstance().displayText("There is nothing left to release from this pod");
            return true;
        } else if (other != null) {
            if(! (other instanceof EmployeeID)){
                return  false;
            }else{
                if(!Objects.equals(((EmployeeID) other).id, id)){
                    Console.getInstance().displayText("The ID scans correctly, but is rejected. ACCESS DENIED.");
                    return true;
                }

                String result = "You press the release button, and a dead human flushes out of the pod. ";
                if(contents != null){
                    result+="Among him, you find a " + contents.getName();
                }else{
                    result+="You search but find nothing else";
                }
                Console.getInstance().displayText(result);
                return true;
            }
        }else{
            Console.getInstance().displayText("You need an employee ID to open this pod.");
            return true;
        }
    }
}
