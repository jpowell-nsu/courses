import java.util.ArrayList;
import java.util.List;

/**
 * Keeps the most recent items added to it, newest first, up to a fixed
 * number. When it is full, adding an item drops the oldest one.
 *
 * @param <T> the type of item
 */
public class RecentlyPlayed<T> {
    private final int capacity;
    private final List<T> items = new ArrayList<>();

    public RecentlyPlayed(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity must be at least 1");
        }
        this.capacity = capacity;
    }

    public void add(T item) {
        items.remove(item);              // played again: move it to the front
        items.add(0, item);
        if (items.size() > capacity) {
            items.remove(items.size() - 1);
        }
    }

    public T latest() {
        return items.get(0);
    }

    public List<T> getItems() {
        return List.copyOf(items);
    }
}
