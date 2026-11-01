import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Anonymous {
    public static void main(String[] args) {
        List<Song> songs = new ArrayList<>(Playlist.songs());

        // An anonymous class: a whole class, written just to hold one method.
        songs.sort(new Comparator<Song>() {
            @Override
            public int compare(Song a, Song b) {
                return Integer.compare(a.seconds(), b.seconds());
            }
        });
        System.out.println(songs.get(0).title() + " is shortest");

        // The same comparator as a lambda.
        songs.sort((a, b) -> Integer.compare(b.seconds(), a.seconds()));
        System.out.println(songs.get(0).title() + " is longest");
    }
}
