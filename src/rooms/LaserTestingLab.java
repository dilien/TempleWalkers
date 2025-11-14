package rooms;

public class LaserTestingLab extends Room{
    public LaserTestingLab(){
        if(Math.random() > 0.5){
            sizeX = 2;
            sizeY = 5;
        }else{
            sizeX = 5;
            sizeY = 2;
        }
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
