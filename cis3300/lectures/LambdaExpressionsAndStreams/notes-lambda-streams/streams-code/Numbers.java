import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;

public class Numbers {
    public static void main(String[] args) {
        List<Song> songs = Playlist.songs();
        int total = songs.stream().mapToInt(Song::seconds).sum();
        OptionalDouble average = songs.stream().mapToInt(Song::seconds).average();
        System.out.println(total + " seconds, average " + average.getAsDouble());

        IntSummaryStatistics stats = songs.stream().mapToInt(Song::seconds).summaryStatistics();
        System.out.println(stats);
    }
}
