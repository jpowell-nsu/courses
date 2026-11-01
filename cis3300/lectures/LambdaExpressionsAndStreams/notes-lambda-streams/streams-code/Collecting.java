import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Collecting {
    public static void main(String[] args) {
        List<Song> songs = Playlist.songs();

        Map<String, Long> count = songs.stream()
                .collect(Collectors.groupingBy(Song::artist, TreeMap::new, Collectors.counting()));
        System.out.println(count);

        Map<String, Double> average = songs.stream()
                .collect(Collectors.groupingBy(Song::artist, TreeMap::new,
                        Collectors.averagingInt(Song::seconds)));
        System.out.println(average);

        Map<String, List<String>> titles = songs.stream()
                .collect(Collectors.groupingBy(Song::artist, TreeMap::new,
                        Collectors.mapping(Song::title, Collectors.toList())));
        System.out.println(titles);

        Map<Boolean, List<String>> longOrShort = songs.stream()
                .collect(Collectors.partitioningBy(s -> s.seconds() > 240,
                        Collectors.mapping(Song::title, Collectors.toList())));
        System.out.println(longOrShort);

        String joined = songs.stream().map(Song::title).collect(Collectors.joining(", "));
        System.out.println(joined);

        Map<String, Integer> longestByArtist = songs.stream()
                .collect(Collectors.toMap(Song::artist, Song::seconds, Math::max, TreeMap::new));
        System.out.println(longestByArtist);
    }
}
