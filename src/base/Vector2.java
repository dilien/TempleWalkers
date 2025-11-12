package base;

import temple.Temple;

public record Vector2(int x, int y) {
    public Vector2 wrap(){
        return new Vector2((x+Temple.size) % Temple.size, (y+Temple.size) % Temple.size);
    }
    public Vector2 wrap(int xSize, int ySize){
        return new Vector2((x+xSize) % xSize, (y+ySize) % ySize);
    }
}
