package base;

public interface Interactable {

    String getName();

    String getDescription();


    /**
     * @param other - optional other interactable object that is used as a tool
     * @return - returns if the interaction is valid
     */
    boolean interact(Interactable other);
}
