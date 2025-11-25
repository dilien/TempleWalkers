package items;

import java.util.Random;

public class Artefact extends Score{
    final static String[] names = new String[]{"Gizmo", "Invention", "Gadget", "Contraption"};
    final static String[] fact = new String[]{
            "has exactly two dials",
            "hums very loudly",
            "makes your hands tingly",
            "floats on its own",
            "stinks really really bad",
            "is yellow",
            "is not yellow",
            "seems to understand how you feel",
            "provides a sense of dread",
            "is bouncy and squishy",
            "has eyes that look right at you",
            "mutters strange incantations",
            "does not interest you in the slightest"};
    final String name;
    final String description;
    public Artefact(int score){
        Random random = new Random();
        this.score = score;
        name = names[random.nextInt(4)];
        description = "The " + name + " " + fact[random.nextInt(13)] + " and " + fact[random.nextInt(13)] + ". Its function is beyond you. Score: " + score;
    }

    public String getName() {
        return name + " - " + score;
    }

    public String describe() {
        return description;
    }
}
