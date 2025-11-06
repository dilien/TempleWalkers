package base;

/**
 * used for the inventory and the room, which both display something on different sections.
 */
//could be improved by having it specify a dimension size on the screen to print to, as well as estimating the size needed.
public interface Renderable {

    /**
     * This function will return a list of interactable objects, as well as display them to the console so the user can index them.
     * int index - number to start the data at, so that it lines up with other renderers
     * @return Interactable[]
     */
    public Interactable[] render(int index, boolean display);
}
