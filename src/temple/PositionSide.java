package temple;

import base.Vector2;

/**
 * note: y going up is actually down. Sorry.
 * Represents a side on the grid.
 * For example,
 * 0,0,true is the right side of the square with the top left corner 0,0
 * 0,0,false is the bottom side of that same square
 *
 * @param pos
 * @param right
 */
public record PositionSide(Vector2 pos, boolean right){

    public Vector2 getBackward(){
        return new Vector2(pos.x() , pos.y()).wrap();
    }
    public Vector2 getForward(){
        if(right){
            return new Vector2(pos.x() + 1, pos.y()).wrap();
        }else{
            return new Vector2(pos.x() , pos.y() + 1).wrap();
        }
    }
}
