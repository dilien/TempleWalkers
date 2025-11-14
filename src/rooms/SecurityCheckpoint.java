package rooms;

//should
public class SecurityCheckpoint extends Room{
    public SecurityCheckpoint() {
        super(3, 2);
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
