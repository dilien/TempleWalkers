package Temple;

/*
represents a side of a room. Example:
(note that north is left and east is down)
     1 2
    #####
   4#   #3
    #####
     5 6

1 - 0, west
2 - 1, west
3 - 0, south
4 - 0, north
5 - 0, east
6 - 1, east
 */
public record CorridorSide (int x, Side side){}
