import java.time.LocalDate;

public class BestDemo {
    public static void main(String[] args) {
        Best<Integer> longest = new Best<>();
        for (Song s : Playlist.songs()) {
            longest.offer(s.seconds());
        }
        System.out.println(longest.get());

        Best<LocalDate> latest = new Best<>();
        latest.offer(LocalDate.of(2026, 3, 1));
        latest.offer(LocalDate.of(2026, 10, 31));
        latest.offer(LocalDate.of(2025, 12, 24));
        System.out.println(latest.get());
    }
}
