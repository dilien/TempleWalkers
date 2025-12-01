package base;


import items.Item;

//This interface represents items that can be interacted with
//This effects a wide range, such as rooms (interact to drop), corridors (interact to move through), items (interact to pickup), structures (interact to use) ect...
public interface Interactable {

    String getName();

    /**
     *
     * @return - A written description of the object. Sometimes effected by object variables.
     */
    String describe();


    /**
     * Allows the player to 'interact' with the object.
     * This can mean a variety of things, so the functionality is pretty varied.
     * (this function does not always advance time, so it is done within the function using temple.tick())
     *
     * @param other - optional other interactable object that is used as a tool
     * @return - returns if the interaction is valid
     */
    boolean interact(Player player, Item other);
}
