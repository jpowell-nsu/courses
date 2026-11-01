import java.util.List;
import java.util.Random;

/** Picks items at random. The seed makes the picks repeatable for testing. */
public class Shuffler {
    private final Random random;

    public Shuffler(long seed) {
        random = new Random(seed);
    }

    /** Returns a random element of a list that is not empty. */
    public <T> T pick(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
}
