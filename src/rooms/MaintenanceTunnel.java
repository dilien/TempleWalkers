package rooms;

import base.Inventory;
import corridors.Corridor;

import java.util.Random;

public class MaintenanceTunnel extends Room{
    final int sizeX;
    final int sizeY;
    public MaintenanceTunnel(){
        if(Math.random() > 0.5){
            sizeX = 4;
            sizeY = 1;
        }else{
            sizeX = 1;
            sizeY = 4;
        }
        inventory = new Inventory(0);
        int perimiter = (getSizeX() + getSizeY()) * 2;
        corridors = new Corridor[perimiter];
    }
    public int getSizeX(){
        return sizeX;
    }
    public int getSizeY(){
        return sizeY;
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
