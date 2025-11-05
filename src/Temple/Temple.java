package Temple;

import base.Player;
import corridors.Corridor;
import corridors.TempleFrame;
import items.Stick;
import items.Torch;
import rooms.Chamber;
import rooms.Room;
import structures.Brazier;
import utility.Event;

//This holds all the rooms and is responsible for gameticks. It is a singleton.
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
        TempleGenerator generator = new TempleGenerator();
        Room start = generator.generateRooms();

        player.enterRoom(start);
        player.inventory.addItem(new Stick());
        player.inventory.addItem(new Torch());
    }

    public Event<Void> tickEvent = new Event<>();
    public void tick(){
        tickEvent.send(null);
    }
}
