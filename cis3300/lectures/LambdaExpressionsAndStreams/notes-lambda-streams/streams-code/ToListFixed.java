import java.util.List;

public class ToListFixed {
    public static void main(String[] args) {
        List<String> titles = Playlist.songs().stream().map(Song::title).toList();
        titles.add("Bonus Track");
    }
}
