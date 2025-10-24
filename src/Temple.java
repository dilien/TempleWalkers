import base.Player;
import corridors.Corridor;
import corridors.TempleFrame;
import items.Stick;
import items.Torch;
import rooms.Chamber;
import rooms.Room;
import structures.Brazier;

public class Temple {
    public Temple(){

        Room room = new Chamber();
        Room room2 = new Chamber();
        Corridor[] corridors = new Corridor[1];
        corridors[0] = new TempleFrame(room, room2);
        room.corridors = corridors;
        room2.corridors = corridors;
        Player player = Player.getInstance();
        player.room = room;

        room2.items.add(new Brazier());
        player.inventory.addItem(new Stick());
        player.inventory.addItem(new Torch());
    }
}
