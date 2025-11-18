package rooms;

import structures.Elevator;

//should
public class SecurityCheckpoint extends Room{
    public static int currentElevator; //used to make sure there is one of each.
    public SecurityCheckpoint() {
        super(3, 2);
        Elevator elevator = new Elevator();
        elevator.level = currentElevator;
        currentElevator++;
        this.structs.add(elevator);
    }

    public void enterRoom() {
    }

    public String getName() {
        return "Security Checkpoint";
    }

    public String describe() {
        return "Sirens blare and flash red near this security checkpoint. It contains an elevator to exit this facility.";
    }

}
