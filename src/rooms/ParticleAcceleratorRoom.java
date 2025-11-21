package rooms;

import base.Inventory;
import corridors.Corridor;
import structures.Container;
import structures.ContainerType;

public class ParticleAcceleratorRoom extends Room {
    public ParticleAcceleratorRoom() {
        super(4, 4);

        this.structs.add(new Container(ContainerType.cupboard));
        for(int i = 0; i<Math.random()*2 + 1; i++){
            this.structs.add(new Container(ContainerType.hasmatlocker));
        }
    }

    public void enterRoom() {

    }

    public String getName() {
        return "Particle accelerator chamber";
    }

    public String describe() {
        return "A massive circular chamber housing the particle accelerator. Warning signs everywhere remind you not to throw paper airplanes into the beam path. How oddly specific.";
    }
}
