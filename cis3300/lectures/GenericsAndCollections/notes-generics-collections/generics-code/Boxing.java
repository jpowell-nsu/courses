import java.util.ArrayList;
import java.util.List;

public class Boxing {
    public static void main(String[] args) {
        List<Integer> lengths = new ArrayList<>();
        lengths.add(254);                 // the int is boxed into an Integer
        lengths.add(198);
        int total = 0;
        for (int seconds : lengths) {     // each Integer is unboxed into an int
            total += seconds;
        }
        System.out.println("Total: " + total);
        lengths.add(null);
        int last = lengths.get(2);        // unboxing null
    }
}
