package rooms;

import structures.Container;
import structures.ContainerType;

public class MaintenanceTunnel extends Room{
    public MaintenanceTunnel() {
        super (1, 4);
        //0-2 toolboxes
        for(int i = 0; i<Math.random()*3; i++){
            this.structs.add(new Container(ContainerType.toolbox));
        }
    }

    public String getName() {
        return "Maintenance Tunnel";
    }

    public String describe() {
        return "A cramped service tunnel filled with pipes and cables. Someone's left empty coffee cups in a neat little line, creating a timeline of the facility's declining coffee quality.";
    }

}
