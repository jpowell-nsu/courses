import java.util.List;
import java.util.stream.Stream;

public class Lazy {
    public static void main(String[] args) {
        List<Song> songs = Playlist.songs();
        Stream<String> titles = songs.stream()
                .filter(s -> {
                    System.out.println("filter " + s.title());
                    return s.seconds() > 300;
                })
                .map(s -> {
                    System.out.println("map    " + s.title());
                    return s.title();
                });
        System.out.println("nothing has run yet");
        System.out.println(titles.findFirst().get());

        Stream<Song> once = songs.stream();
        System.out.println(once.count());
        System.out.println(once.count());
    }
}
