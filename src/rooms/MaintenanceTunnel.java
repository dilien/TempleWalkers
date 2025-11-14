package rooms;

public class MaintenanceTunnel extends Room{
    public int getSizeX(){
        return 4;
    }
    public int getSizeY(){
        return 1;
    }

    public void enterRoom() {
    }

    public String getName() {
        return "Maintenance Tunnel";
    }

    public String describe() {
        return "The low ceiling forces you to hunch. Pipes leak steam at irregular intervals, hissing in the darkness.";
    }

}
