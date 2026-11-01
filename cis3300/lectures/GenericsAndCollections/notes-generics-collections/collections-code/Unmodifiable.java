import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Unmodifiable {
    public static void main(String[] args) {
        List<String> source = new ArrayList<>(List.of("Static", "Echo Park"));
        List<String> copy = List.copyOf(source);
        List<String> view = Collections.unmodifiableList(source);
        source.add("Slow Burn");
        System.out.println("copy: " + copy + ", view: " + view);

        List<String> fixed = List.of("Static", "Echo Park");
        fixed.add("Slow Burn");
    }
}
