/**
 * Remembers the largest item offered to it, using the items' natural order.
 *
 * @param <T> the type of item; it must be comparable
 */
public class Best<T extends Comparable<? super T>> {
    private T best;

    public void offer(T item) {
        if (best == null || item.compareTo(best) > 0) {
            best = item;
        }
    }

    public T get() {
        return best;
    }
}
