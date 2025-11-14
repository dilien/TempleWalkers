package rooms;

import java.util.Random;

public class MaintenanceTunnel extends Room{
    public MaintenanceTunnel(){
        if(Math.random() > 0.5){
            sizeX = 4;
            sizeY = 1;
        }else{
            sizeX = 1;
            sizeY = 4;
        }
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
