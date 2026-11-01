import java.time.LocalDate;
import java.util.List;

public class Bounded {
    /** Returns the largest element of a list that is not empty. */
    static <T extends Comparable<? super T>> T largest(List<T> list) {
        T best = list.get(0);
        for (T item : list) {
            if (item.compareTo(best) > 0) {
                best = item;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(largest(List.of("Static", "Echo Park", "Slow Burn")));
        System.out.println(largest(List.of(254, 198, 312)));
        System.out.println(largest(List.of(LocalDate.of(2026, 3, 1), LocalDate.of(2025, 12, 24))));
    }
}
