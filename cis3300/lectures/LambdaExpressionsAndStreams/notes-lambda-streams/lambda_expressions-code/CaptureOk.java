import java.util.List;

public class CaptureOk {
    public static void main(String[] args) {
        List<Song> songs = Playlist.songs();
        int limit = 300;
        songs.forEach(s -> {
            if (s.seconds() > limit) {
                System.out.println(s.title());
            }
        });
    }
}
