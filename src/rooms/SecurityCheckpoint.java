package rooms;

//should
public class SecurityCheckpoint extends Room{
    public int getSizeX(){
        return 2;
    }
    public int getSizeY(){
        return 3;
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
