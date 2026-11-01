import java.util.function.Function;

public class BlockLambda {
    public static void main(String[] args) {
        Function<Song, String> length = s -> {
            int minutes = s.seconds() / 60;
            int seconds = s.seconds() % 60;
            return String.format("%d:%02d", minutes, seconds);
        };
        System.out.println(length.apply(new Song("Slow Burn", "Neon Harbor", 312)));
    }
}
