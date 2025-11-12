package temple;

import base.Vector2;

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
