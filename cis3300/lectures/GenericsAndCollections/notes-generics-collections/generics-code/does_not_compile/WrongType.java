public class WrongType {
    public static void main(String[] args) {
        RecentlyPlayed<Song> songs = new RecentlyPlayed<>(3);
        songs.add("Static");
    }
}
