import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Comparators {
    public static void main(String[] args) {
        List<Song> songs = new ArrayList<>(Playlist.songs());

        songs.sort(Comparator.comparing(Song::artist).thenComparing(Song::title));
        for (Song s : songs) {
            System.out.println(s.artist() + ": " + s.title());
        }

        songs.sort(Comparator.comparingInt(Song::seconds).reversed());
        System.out.println("Longest first: " + songs.get(0).title() + ", "
                + songs.get(1).title() + ", " + songs.get(2).title());
    }
}
