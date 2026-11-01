public class RecentDemo {
    public static void main(String[] args) {
        RecentlyPlayed<Song> songs = new RecentlyPlayed<>(3);
        for (Song s : Playlist.songs()) {
            songs.add(s);
        }
        System.out.println("Latest: " + songs.latest().title());
        for (Song s : songs.getItems()) {
            System.out.println("  " + s.title());
        }

        RecentlyPlayed<String> searches = new RecentlyPlayed<>(3);
        searches.add("ada vale");
        searches.add("slow burn");
        searches.add("ada vale");
        System.out.println(searches.getItems());
    }
}
