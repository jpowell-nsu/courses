import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortDemo {
    public static void main(String[] args) {
        List<String> titles = new ArrayList<>();
        for (Song s : Playlist.songs()) {
            titles.add(s.title());
        }
        Collections.sort(titles);
        System.out.println(titles);
        System.out.println("max: " + Collections.max(titles) + ", min: " + Collections.min(titles));
    }
}
