package temple;

import base.Monster;
import base.Player;
import rooms.Room;
import utility.Event;

//This holds all the rooms and is responsible for gameticks. It is a singleton.
public class Temple {
    public static int size = 20;

    private Temple(){

    }
    private static Temple temple;
    public static Temple getInstance(){
        if(temple == null){
            temple = new Temple();
        }
        return temple;
    }

    public Monster monster;
    public boolean dark;
    public void testInit(Player player){
        TempleGenerator generator = new TempleGenerator();
        Room start = generator.generateRooms();

        monster = new Monster();

        player.enterRoom(start);
    }

    public Event<Void> tickEvent = new Event<>();

    int timeUntilFlip = 5;
    public int totalTurns;
    public void tick(){
        totalTurns += 1;
        timeUntilFlip -= 1;
        if(timeUntilFlip <= 0){
            dark = !dark;
            if(dark){
                //time until lights turned back on
                //we want this to get incrementally longer
                timeUntilFlip = (int)(Math.random() * 10) + (totalTurns / 5);
            }else{
                //time until lights turn off
                //we want this to be mostly constant, but unpredictable
                timeUntilFlip = (int)(Math.random() * 10) + 20;
            }
        }
        tickEvent.send(null);
    }
}
