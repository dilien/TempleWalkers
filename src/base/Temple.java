package base;

import corridors.Corridor;
import corridors.TempleFrame;
import items.Stick;
import items.Torch;
import rooms.Chamber;
import rooms.Room;
import structures.Brazier;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Temple {

    private Temple(){

    }
    private static Temple temple;
    public static Temple getInstance(){
        if(temple == null){
            temple = new Temple();
        }
        return temple;
    }

    public void testInit(Player player){
        Room room = new Chamber();
        Room room2 = new Chamber();
        Corridor[] corridors = new Corridor[1];
        corridors[0] = new TempleFrame(room, room2);
        room.corridors = corridors;
        room2.corridors = corridors;

        player.enterRoom(room);

        room2.structs.add(new Brazier());
        player.inventory.addItem(new Stick());
        player.inventory.addItem(new Torch());
    }

    public Event<Void> tickEvent = new Event<>();
    public void tick(){
        tickEvent.send(null);
    }

    static void generateRooms(){

    }
}
