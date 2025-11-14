package rooms;

import base.Inventory;
import corridors.Corridor;

public class LaserTestingLab extends Room{
    final int sizeX;
    final int sizeY;
    public LaserTestingLab(){
        if(Math.random() > 0.5){
            sizeX = 2;
            sizeY = 5;
        }else{
            sizeX = 2;
            sizeY = 5;
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
        return "Laser Testing Laboratory";
    }

    public String describe() {
        return "";
    }
}
