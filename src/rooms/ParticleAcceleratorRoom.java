package rooms;

import base.Inventory;
import corridors.Corridor;

public class ParticleAcceleratorRoom extends Room {
    public ParticleAcceleratorRoom(){
        super();
    }
    public int getSizeX(){
        return 4;
    }
    public int getSizeY(){
        return 4;
    }

    public void enterRoom() {

    }

    public String getName() {
        return "Particle accelerator chamber";
    }

    public String describe() {
        return "";
    }
}
