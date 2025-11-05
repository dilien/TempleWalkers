package base;


import items.Item;

//This interface represents items that can be interacted with
//This effects a wide range, such as rooms (interact to drop), corridors (interact to move through), items (interact to pickup), structures (interact to use) ect...
public interface Interactable {

    String getName();

    String describe();


    /**
     * @param other - optional other interactable object that is used as a tool
     * @return - returns if the interaction is valid
     */
    boolean interact(Player player, Item other);
}
