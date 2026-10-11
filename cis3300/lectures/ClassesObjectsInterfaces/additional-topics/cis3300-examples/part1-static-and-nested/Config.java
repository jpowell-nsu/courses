import java.io.IOException;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Config {
    public static final int MAX_ROSTER_SIZE;

    static {
        System.out.println("Config: static block running");
        int size = 30;   // default if the file is missing or unreadable
        try (Scanner in = new Scanner(Path.of("config.txt"))) {
            size = in.nextInt();
        } catch (IOException | NoSuchElementException e) {
            System.out.println("Config: using the default roster size");
        }
        MAX_ROSTER_SIZE = size;
    }
}
