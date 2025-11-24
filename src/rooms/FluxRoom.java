package rooms;

import base.Console;
import base.Player;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FluxRoom extends Room{
    private static final ArrayList<FluxRoom> rooms = new ArrayList<>();

    FluxRoom(){
        super(1, 1);
        rooms.add(this);
    }

    public void enterRoom(Player player) {
        Console.getInstance().prompt("You suddenly feel disoriented and after a bright flash of light, you have no idea where you are.");
        player.enterRoom(rooms.get(new Random().nextInt(rooms.size())));
    }

    public String getName() {
        return "";
    }

    public String describe() {
        return "";
    }
}
