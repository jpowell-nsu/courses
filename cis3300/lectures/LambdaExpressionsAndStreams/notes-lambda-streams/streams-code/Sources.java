import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Sources {
    public static void main(String[] args) {
        System.out.println(Stream.of("Static", "Echo Park", "Slow Burn").count());
        int[] ratings = {4, 5, 3, 5};
        System.out.println(Arrays.stream(ratings).sum());
        System.out.println(IntStream.rangeClosed(1, 5).sum());
    }
}
