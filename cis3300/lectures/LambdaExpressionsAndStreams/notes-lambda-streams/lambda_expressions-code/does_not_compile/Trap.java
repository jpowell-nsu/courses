import java.util.Comparator;

public class Trap {
    static Comparator<Song> longestFirst = Comparator.comparing(s -> s.seconds()).reversed();
}
