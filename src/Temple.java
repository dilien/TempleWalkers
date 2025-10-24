import base.Player;
import corridors.Corridor;
import corridors.TempleFrame;
import rooms.Chamber;
import rooms.Room;

public class Temple {
    public Temple(){

        Room room = new Chamber();
        Room room2 = new Chamber();
        Corridor[] corridors = new Corridor[1];
        corridors[0] = new TempleFrame(room, room2);
        room.corridors = corridors;
        room2.corridors = corridors;
        Player main = Player.getInstance();
        main.room = room;
    }
}
