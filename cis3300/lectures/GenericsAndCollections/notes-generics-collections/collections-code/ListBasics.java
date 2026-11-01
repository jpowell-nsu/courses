import java.util.ArrayList;
import java.util.List;

public class ListBasics {
    public static void main(String[] args) {
        List<String> queue = new ArrayList<>();
        queue.add("Static");
        queue.add("Echo Park");
        queue.add(0, "Slow Burn");          // insert at the front
        queue.add("Static");                // duplicates are allowed
        System.out.println(queue + ", size " + queue.size());
        System.out.println(queue.get(1) + ", index of Static: " + queue.indexOf("Static"));
        queue.set(1, "Paper Moons");        // replace the element at index 1
        System.out.println(queue);

        List<Integer> lengths = new ArrayList<>(List.of(254, 198, 312));
        lengths.remove(1);                          // removes index 1
        System.out.println(lengths);
        lengths.remove(Integer.valueOf(254));       // removes the value 254
        System.out.println(lengths);
    }
}
