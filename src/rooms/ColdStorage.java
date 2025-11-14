package rooms;

public class ColdStorage extends Room{
    public int getSizeX(){
        return 2;
    }
    public int getSizeY(){
        return 2;
    }

    public void enterRoom() {
    }

    public String getName() {
        return "Cold Storage";
    }

    public String describe() {
        return "Frost creeps along steel shelving. The temperature gauge reads -5°C, its digital display flickering weakly.";
    }

}
