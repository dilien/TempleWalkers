package Temple;

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
