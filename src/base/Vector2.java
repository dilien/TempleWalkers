package base;

import temple.Temple;

/**
 * A class to represent a position on a grid in 2D space
 * @param x - the X coordinate
 * @param y - the Y coordinate
 */
public record Vector2(int x, int y) {
    /**
     * Wraps the point to be within the temple
     * for example: if the size is 10 and the X is 11, it is wrapped over to 1.
     * @return - the new wrapped vector
     */
    public Vector2 wrap(){
        return new Vector2((x+Temple.size) % Temple.size, (y+Temple.size) % Temple.size);
    }
}
