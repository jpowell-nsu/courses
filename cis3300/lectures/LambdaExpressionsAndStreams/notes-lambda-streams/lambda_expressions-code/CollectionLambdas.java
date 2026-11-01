import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CollectionLambdas {
    public static void main(String[] args) {
        List<Song> songs = new ArrayList<>(Playlist.songs());
        songs.removeIf(s -> s.seconds() < 200);
        songs.forEach(s -> System.out.println(s.title()));

        List<String> titles = new ArrayList<>(List.of("static", "echo park"));
        titles.replaceAll(String::toUpperCase);
        System.out.println(titles);

        Map<String, Integer> plays = new TreeMap<>();
        for (String title : List.of("Static", "Echo Park", "Static")) {
            plays.merge(title, 1, Integer::sum);
        }
        plays.forEach((title, count) -> System.out.println(title + ": " + count));

        Map<String, List<String>> byArtist = new TreeMap<>();
        for (Song s : Playlist.songs()) {
            byArtist.computeIfAbsent(s.artist(), artist -> new ArrayList<>()).add(s.title());
        }
        System.out.println(byArtist);
    }
}
