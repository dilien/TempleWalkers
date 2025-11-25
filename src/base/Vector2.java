package base;

import temple.Temple;

public record Vector2(int x, int y) {
    public Vector2 wrap(){
        return new Vector2((x+Temple.size) % Temple.size, (y+Temple.size) % Temple.size);
    }
}
