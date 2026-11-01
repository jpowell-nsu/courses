/** A song written as an ordinary class, with equals but no hashCode. */
public class Track {
    private final String title;
    private final String artist;

    public Track(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Track t && title.equals(t.title) && artist.equals(t.artist);
    }

    @Override
    public String toString() {
        return title + " by " + artist;
    }
}
