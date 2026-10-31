import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class MultiRelated {
    public static void main(String[] args) {
        try {
            Files.readString(Path.of("files/staff.csv"));
        } catch (NoSuchFileException | IOException e) {
            System.out.println("problem");
        }
    }
}
