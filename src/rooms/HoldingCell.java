package rooms;

public class HoldingCell extends Room{
    public HoldingCell(){
        sizeX = 1;
        sizeY = 1;
    }

    public void enterRoom() {

    }

    public String getName() {
        return "Holding Cell";
    }

    public String describe() {
        return "This small room has pipes containing genetic fluid running all over the place. It doesn't look very OSHA compliant.";
    }
}