package testing;

import Temple.CorridorSide;
import Temple.Side;
import corridors.Corridor;
import rooms.Room;

import java.util.Arrays;

//This class allows you to display the map
//normally this would not be a feature as the fun is in the mapping, but it is here for debug purposes.
public class GeneratorTests {
    public void displayMap(Room[][] grid, Room[] rooms){
        final int templeSize = grid.length;
        char[][] output = new char[templeSize*2][templeSize*2].clone();
        //not quite sure how this works but thanks internet:
        java.util.Arrays.stream(output).forEach(row -> Arrays.fill(row, ' '));

        for(int x = 0; x<templeSize;x++){
            for(int y = 0; y<templeSize;y++){
                Room room = grid[x][y];
                if(room == null) {
                    continue;
                }
                output[x * 2][y * 2] = '#';

                if(x > 0 && grid[x-1][y] == room){
                    output[x * 2-1][y * 2] = '#';
                }
                if(y > 0 && grid[x][y-1] == room){
                    output[x * 2][y * 2-1] = '#';
                }
                if(x < templeSize-1 && grid[x+1][y] == room){
                    output[x * 2 + 1][y * 2] = '#';
                }
                if(y < templeSize-1 && grid[x][y+1] == room){
                    output[x * 2][y * 2 + 1] = '#';
                }
            }
        }

        for(Room room : rooms){
            if(room.x < 0){continue;}
            for(Corridor corridor : room.corridors){
                addCorridor(room, corridor, output);
            }
        }

        Room start = rooms[0];
        output[start.x*2 - 1 + start.sizeX][start.y*2 - 1 + start.sizeY] = '@';

        for(char[] str : output){
            System.out.println(str);
        }
    }
    public void addCorridor(Room room, Corridor corridor, char[][] output){
        int x = room.x;
        int y = room.y;
        CorridorSide side = corridor.getSide(room);
        if(side == null){
            return;
        }
        //System.out.println("corridor:" + side.side());
        //System.out.println(room.x + " " + room.y);
        //System.out.println(corridor.other(room).x + " " + corridor.other(room).y);
        switch (side.side()){
            case Side.North -> output[(x + side.x()) * 2][(y) * 2 - 1] = '-';
            case Side.South -> output[(x + side.x()) * 2][(y + room.sizeY - 1) * 2 + 1] = '-';
            case Side.West -> output[(x) * 2 - 1][(y + side.x()) * 2] = '|';
            case Side.East -> output[(x + room.sizeX - 1) * 2 + 1][(y + side.x()) * 2] = '|';
        }
    }
}
