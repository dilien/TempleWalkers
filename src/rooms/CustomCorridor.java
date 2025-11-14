package rooms;

public class CustomCorridor extends Room{
    public CustomCorridor(int sizeX, int sizeY) {
        super( sizeX, sizeY);
    }

    public void enterRoom() {
    }

    public String getName() {
        return "Corridor";
    }

    public String describe() {
        return "corridor description here";
    }

}
