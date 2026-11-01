import java.util.List;

public class GenericMethod {
    /** Returns the last element of list, or fallback if the list is empty. */
    static <T> T lastOrDefault(List<T> list, T fallback) {
        if (list.isEmpty()) {
            return fallback;
        }
        return list.get(list.size() - 1);
    }

    public static void main(String[] args) {
        List<String> titles = List.of("Paper Moons", "Static", "Echo Park");
        List<Integer> none = List.of();
        String last = lastOrDefault(titles, "nothing");
        int lastLength = lastOrDefault(none, 0);
        System.out.println(last);
        System.out.println(lastLength);
    }
}
