import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Terminal {
    public static void main(String[] args) {
        List<Song> songs = Playlist.songs();
        System.out.println(songs.stream().anyMatch(s -> s.seconds() > 340));
        System.out.println(songs.stream().allMatch(s -> s.seconds() > 180));
        System.out.println(songs.stream().noneMatch(s -> s.artist().isBlank()));

        Optional<Song> longest = songs.stream().max(Comparator.comparingInt(Song::seconds));
        System.out.println(longest.map(Song::title).orElse("no songs"));

        Optional<Song> byNobody = songs.stream()
                .filter(s -> s.artist().equals("Nobody"))
                .findFirst();
        System.out.println(byNobody);
        System.out.println(byNobody.map(Song::title).orElse("no songs"));
        byNobody.ifPresent(s -> System.out.println("found " + s.title()));

        int total = songs.stream().map(Song::seconds).reduce(0, Integer::sum);
        System.out.println(total);
    }
}
