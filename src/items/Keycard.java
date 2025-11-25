package items;

public class Keycard extends Item{
    public int level;
    public Keycard(int level){
        this.level = level;
    }

    public String getName() {
        return "Level " + level + " key-card";
    }

    public String describe() {
        return "This keycard can be used to open level " + level + " doors. It cannot open other levels, even those below it.";
    }
}
