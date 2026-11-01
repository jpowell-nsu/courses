import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashDemo {
    public static void main(String[] args) {
        Track a = new Track("Static", "Neon Harbor");
        Track b = new Track("Static", "Neon Harbor");
        System.out.println("equals: " + a.equals(b));
        System.out.println("in a list: " + List.of(a).contains(b));

        Set<Track> set = new HashSet<>();
        set.add(a);
        set.add(b);
        Track c = new Track("Static", "Neon Harbor");
        System.out.println("set size: " + set.size() + ", contains an equal Track: " + set.contains(c));

        Set<Song> songs = new HashSet<>();
        songs.add(new Song("Static", "Neon Harbor", 176));
        songs.add(new Song("Static", "Neon Harbor", 176));
        System.out.println("record set size: " + songs.size());
    }
}
