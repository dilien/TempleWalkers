package rooms;

import structures.Elevator;

public class ElevatorShaft extends Room{
    public static int currentElevator = 1; //used to make sure there is one of each.
    public ElevatorShaft() {
        super(1, 1);

        Elevator elevator = new Elevator();
        elevator.level = currentElevator;
        currentElevator++;
        this.structs.add(elevator);
    }

    public String getName() {
        return "Elevator Shaft";
    }

    public String describe() {
        return "You stand at the bottom of a square elevator shaft. Looking up, you can just barely see the daylight shining down.";
    }
}
