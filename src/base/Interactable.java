package base;


import items.Item;

public interface Interactable {

    String getName();

    String describe();


    /**
     * @param other - optional other interactable object that is used as a tool
     * @return - returns if the interaction is valid
     */
    boolean interact(Item other);
}
