package rooms;

public class BigRoom extends Room{
    public int getSizeX(){
        return 3;
    }
    public int getSizeY(){
        return 3;
    }

    public void enterRoom() {
    }

    public String getName() {
        return "bigroom";
    }

    public String describe() {
        return "This room is big. Wow.";
    }

}
