import java.nio.file.Files;
import java.nio.file.Path;

public class Unreported {
    static String readStaff(String fileName) {
        return Files.readString(Path.of(fileName));
    }
}
