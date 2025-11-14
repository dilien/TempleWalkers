package structures;

import base.Console;
import base.Player;
import items.Item;

public class ContainmentPod extends Structure{
    Item contents;
    boolean released = false;
    String id;
    public ContainmentPod(){
        String letters = "" + (char)(65 + Math.random() * 26) + (char)(65 + Math.random() * 26);
        String numbers = "" + (int)(Math.random() * 10) + (int)(Math.random() * 10);
        id = letters + "-" + numbers;
    }

    public void setItem(Item item){
        contents = item;
    }

    public String getName() {
        return "Containment Pod " + id;
    }

    public String describe() {
        return "You cant really see what is in the pod. Its all fogged up. There is a big red button, that will release the contained thing, whatever it is.";
    }

    public boolean interact(Player player, Item other) {
        if(released){
            Console.getInstance().displayText("There is nothing left to release from this pod");
            return true;
        }else{
            String result = "You press the release button, and a dead human flushes out of the pod. ";
            if(contents != null){
                result+="Among him, you find a " + contents.getName();
            }else{
                result+="You search but find nothing else";
            }
            Console.getInstance().displayText(result);
            return true;
        }
    }
}
