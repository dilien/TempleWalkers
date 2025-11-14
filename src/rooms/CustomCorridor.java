package rooms;

public class CustomCorridor extends Room{
    int sizeX;
    int sizeY;
    public int getSizeX(){
        return sizeX;
    }
    public int getSizeY(){
        return sizeY;
    }

    CustomCorridor(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public void enterRoom() {
    }

    public String getName() {
        return "Corridor";
    }

    public String describe() {
        return "corridor description here";
    }

}
