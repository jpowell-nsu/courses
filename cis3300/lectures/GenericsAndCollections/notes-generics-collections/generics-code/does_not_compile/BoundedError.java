import java.util.List;

public class BoundedError {
    public static void main(String[] args) {
        Song best = Bounded.largest(Playlist.songs());
    }
}
