import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetOrders {
    public static void main(String[] args) {
        List<String> titles = List.of("Static", "Echo Park", "Slow Burn", "Static",
                "Glass Garden", "Paper Moons", "Echo Park", "Northern Lights");
        Set<String> hash = new HashSet<>(titles);
        Set<String> linked = new LinkedHashSet<>(titles);
        Set<String> tree = new TreeSet<>(titles);
        System.out.println("HashSet:       " + hash);
        System.out.println("LinkedHashSet: " + linked);
        System.out.println("TreeSet:       " + tree);
        System.out.println("add again: " + tree.add("Static"));
    }
}
