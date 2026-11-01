import java.util.List;

public class Capture {
    public static void main(String[] args) {
        List<Song> songs = Playlist.songs();
        int limit = 300;
        songs.forEach(s -> {
            if (s.seconds() > limit) {
                System.out.println(s.title());
            }
        });

        int count = 0;
        songs.forEach(s -> count++);
    }
}
