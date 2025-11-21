package items;

import javax.print.Doc;
import java.util.Random;

public class Document extends Item{

    private static final String[] FullTitle = {
            "Quantum tunneling to bypass",
            "Neural interfacing to connect to",
            "Using atomic instabilities to achieve",
            "Genetic alterations to create",
            "Blueprints on how to create "};
    private static final String[] Thing = {
            "meddling space owls",
            "self replicating toasters",
            "even more science",
            "unlimited pirated copies of the sims 4",
            "java version 27"};
    private static final String[] Success = {
            "REDACTED",
            "a complete and utter failure",
            "something uncomprehensible",
            "The secrets to the universe",
            "a minor success"};

    public int score = 5;
    String description;
    public Document(int score){
        Random random = new Random();
        this.score = score;
        int category = random.nextInt(5);
        //hard coded rather than re-generated every time.
        this.description = "The paper: " + FullTitle[category] + " " + Thing[random.nextInt(5)] + " resulting in " + Success[random.nextInt(5)] + ". Score: " + score;
    }

    public String getName() {
        return "Research paper - " + score;
    }

    public String describe() {
        return this.description;
    }
}
