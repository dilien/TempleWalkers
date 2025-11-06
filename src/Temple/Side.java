package Temple;

/*
Note that because of the way arrays are accessed [x][y], the components are flipped compared to these directions.
this means the compass is rotated 90 degrees clockwise when displayed to the user.
 */
public enum Side {
    North,
    East,
    South,
    West;
    public Side increment(int num) {
        Side[] values = Side.values();
        int nextIndex = (this.ordinal() + num) % values.length;
        return values[nextIndex];
    }
}
