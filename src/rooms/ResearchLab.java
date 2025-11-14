package rooms;

public class ResearchLab extends Room{
    public int getSizeX(){
        return 2;
    }
    public int getSizeY(){
        return 2;
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
