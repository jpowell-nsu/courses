import java.util.List;

public class Intermediate {
    public static void main(String[] args) {
        List<Song> songs = Playlist.songs();
        System.out.println(songs.stream().map(Song::artist).distinct().toList());
        System.out.println(songs.stream().map(Song::title).sorted().limit(3).toList());
        System.out.println(songs.stream().map(Song::title).sorted().skip(5).toList());
    }
}
