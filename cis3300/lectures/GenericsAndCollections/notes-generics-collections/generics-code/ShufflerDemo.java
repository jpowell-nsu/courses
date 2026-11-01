import java.util.List;

public class ShufflerDemo {
    public static void main(String[] args) {
        Shuffler shuffler = new Shuffler(3300);
        Song song = shuffler.pick(Playlist.songs());
        String genre = shuffler.pick(List.of("rock", "jazz", "folk"));
        Integer rating = shuffler.pick(List.of(3, 4, 5));
        System.out.println(song.title() + ", " + genre + ", " + rating);
    }
}
