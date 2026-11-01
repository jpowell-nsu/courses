import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoopVsStream {
    public static void main(String[] args) {
        List<Song> songs = Playlist.songs();

        List<String> loop = new ArrayList<>();
        for (Song s : songs) {
            if (s.seconds() > 240) {
                loop.add(s.title());
            }
        }
        Collections.sort(loop);
        System.out.println(loop);

        List<String> stream = songs.stream()
                .filter(s -> s.seconds() > 240)
                .map(Song::title)
                .sorted()
                .toList();
        System.out.println(stream);
    }
}
