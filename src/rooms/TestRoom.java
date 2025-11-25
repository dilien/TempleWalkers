package rooms;

public class TestRoom extends Room{
    public TestRoom(int x, int y) {
        super(x, y);
    }

    public String getName() {
        return "Test Room";
    }

    public String describe() {
        return "Oops. You shouldn't be seeing this.";
    }

}
