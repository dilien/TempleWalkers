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

    //this is a weak reference, because if the object is destroyed (i.e. a item with no parent) then we want the garbage collector to clean it up still.
    ArrayList<WeakReference<Tickable>> tickables = new ArrayList<>();
    public void tick(){
        for(WeakReference<Tickable> tickable : tickables){
            if(tickable.get() == null){
                tickables.remove(tickable);
            }else{
                tickable.get().tick();
            }
        }
    }

    public void listenTick(Tickable self){
        tickables.add(new WeakReference<>(self));
    }

    static void generateRooms(){

    }
}
