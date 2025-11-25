package temple;

import base.Vector2;
import corridors.Corridor;
import rooms.Room;

import java.util.Arrays;

/**
 * Used to display multiple rooms in a human-readable format
 * Used for debugging but might be implemented as an item later.
 */
public class Map {
    /**
     * Displays the map straight to the console. Intended for debugging
     * Note that it is rotated by 90 degrees counter-clockwise, so north is right
     * Theoretically, this could be used to only show discovered rooms
     * @param templeSize - size of grid, the grid itself is not needed
     * @param rooms - list of rooms to draw
     */
    public void displayMap(int templeSize, Room[] rooms){
        //extra to account for map looping
        char[][] output = new char[templeSize*2+10][templeSize*3+15].clone();
        //not quite sure how this works but thanks internet:
        java.util.Arrays.stream(output).forEach(row -> Arrays.fill(row, ' '));

        for(Room room : rooms) {
            if(room == null){continue;}
            int startX = room.x * 2;
            int startY = room.y * 3;
            int endX = startX + room.getSizeX() * 2 - 1;
            int endY = startY + room.getSizeY() * 3 - 1;
            for(int x = startX; x<endX; x++){
                for(int y = startY; y<endY; y++){
                    if(x == startX || y == startY || x == endX-1 || y == endY-1){
                        output[x][y] = '█';
                    }else{
                        output[x][y] = '.';
                    }
                }
            }
        }


        for(Room room : rooms){
            if(room == null){continue;}
            for(Corridor corridor : room.corridors){
                if(corridor != null){
                    addCorridor(room, corridor, output);
                }
            }
        }

        //assumes the player is in room 0
        Room start = rooms[0];
        output[start.x*2 -1 + start.getSizeX()][start.y*3 -1 + start.getSizeY()] = '@';

        for(char[] str : output){
            System.out.println(str);
        }
    }

    /**
     * Adds a corridor to the display map
     * note: each corridor has two rooms, and this means each corridor is drawn twice in the same place
     * @param corridor - the side to add the corridor to
     * @param output - the char grid to write to
     */
    public void addCorridor(Corridor corridor, char[][] output){
        PositionSide global = corridor.side;
        Vector2 v = global.pos();
        int x =  v.x();
        int y =  v.y();

        Vector2 vec;
        if(!global.right()){
            vec = new Vector2(x*2, y*3+2);
            output[vec.x()][ vec.y()] = '-';
        }else{
            vec = new Vector2(x*2+1, y*3);
            output[vec.x()][ vec.y()] = '|';
        }
    }
}
