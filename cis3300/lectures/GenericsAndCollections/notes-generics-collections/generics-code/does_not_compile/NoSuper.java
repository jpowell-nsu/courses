import java.time.LocalDate;
import java.util.List;

public class NoSuper {
    static <T extends Comparable<T>> T largest(List<T> list) {
        return list.get(0);
    }

    public static void main(String[] args) {
        LocalDate d = largest(List.of(LocalDate.of(2026, 3, 1)));
    }
}
