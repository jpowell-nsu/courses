import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Functional {
    public static void main(String[] args) {
        Song song = new Song("Glass Garden", "Ada Vale", 345);

        Predicate<Song> isLong = s -> s.seconds() > 300;
        Function<Song, String> label = s -> s.title() + " (" + s.artist() + ")";
        Consumer<String> show = text -> System.out.println("> " + text);
        Supplier<List<Song>> emptyQueue = () -> new ArrayList<>();
        BinaryOperator<Integer> add = (a, b) -> a + b;

        System.out.println(isLong.test(song));
        System.out.println(label.apply(song));
        show.accept("now playing");
        System.out.println(emptyQueue.get().size());
        System.out.println(add.apply(254, 198));
    }
}
