package rooms;

public class ResearchLab extends Room{
    public ResearchLab() {
        super(2, 2);
    }

    public void enterRoom() {
    }

    public String getName() {
        return "Research Lab";
    }

    public String describe() {
        return "Upturned chairs scatter the room. Half-finished equations cover whiteboards, their meanings lost in hasty scribbles.";
    }

}
