package speedrun;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Keeps the best entries added to it, best first, up to a fixed number of
 * places. It works for any type that can be compared, and "best" means first
 * in that type's natural order.
 * The Speedrun Leaderboard: a running example for CIS 3300.
 *
 * @param <T> the type of entry; it must be Comparable
 * @author  Dr. Powell
 * @version 1.0
 */
public class Leaderboard<T extends Comparable<? super T>> {
    private final int capacity;
    private final List<T> entries = new ArrayList<>();

    /**
     * @param capacity the number of places on the board
     * @throws IllegalArgumentException if capacity is less than 1
     */
    public Leaderboard(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException(
                    "capacity must be at least 1, but was " + capacity);
        }
        this.capacity = capacity;
    }

    /**
     * Adds an entry in its place. An entry equal to one already on the board
     * goes after it, so the entry that got there first keeps its place.
     *
     * @param entry the entry to add
     * @return true if the entry made the board
     * @throws NullPointerException if entry is null
     */
    public boolean add(T entry) {
        Objects.requireNonNull(entry, "entry is required");
        int position = 0;
        while (position < entries.size()
                && entries.get(position).compareTo(entry) <= 0) {
            position++;
        }
        if (position >= capacity) {
            return false;
        }
        entries.add(position, entry);
        if (entries.size() > capacity) {
            entries.remove(entries.size() - 1);
        }
        return true;
    }

    /**
     * @return the best entry
     * @throws IllegalStateException if the board is empty
     */
    public T getLeader() {
        if (entries.isEmpty()) {
            throw new IllegalStateException("the leaderboard is empty");
        }
        return entries.get(0);
    }

    /** @return the entries, best first, as a list that cannot be changed */
    public List<T> getEntries() {
        return List.copyOf(entries);
    }

    /** @return how many entries are on the board */
    public int size() {
        return entries.size();
    }

    /** @return the number of places on the board */
    public int getCapacity() {
        return capacity;
    }
}
