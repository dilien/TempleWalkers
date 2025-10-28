package base;

public interface Renderable {

    /**
     * This function will return a list of interactable objects, as well as display them to the console so the user can index them.
     *
     * int index - number to start the data at, so that it lines up with other renderers
     * @return Interactable[]
     */
    public Interactable[] render(int index, boolean display);
}
