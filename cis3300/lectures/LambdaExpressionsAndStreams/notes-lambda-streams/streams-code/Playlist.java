import java.util.List;

/** A small, fixed playlist used by the examples. The songs are invented. */
public class Playlist {
    public static List<Song> songs() {
        return List.of(
            new Song("Midnight Drive", "Neon Harbor", 254),
            new Song("Paper Moons", "The Lanterns", 198),
            new Song("Slow Burn", "Neon Harbor", 312),
            new Song("Northern Lights", "Ada Vale", 287),
            new Song("Echo Park", "The Lanterns", 221),
            new Song("Glass Garden", "Ada Vale", 345),
            new Song("Static", "Neon Harbor", 176));
    }
}
