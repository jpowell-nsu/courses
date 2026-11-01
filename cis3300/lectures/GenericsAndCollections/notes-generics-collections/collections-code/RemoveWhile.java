import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RemoveWhile {
    public static void main(String[] args) {
        List<Song> songs = new ArrayList<>(Playlist.songs());
        Iterator<Song> it = songs.iterator();
        while (it.hasNext()) {
            if (it.next().seconds() < 200) {
                it.remove();                     // safe: the iterator removes it
            }
        }
        System.out.println(songs.size() + " songs left");

        for (Song s : songs) {
            if (s.seconds() > 300) {
                songs.remove(s);                 // not safe
            }
        }
    }
}
