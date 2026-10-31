import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class OrderError {
    public static void main(String[] args) {
        try {
            String text = Files.readString(Path.of("files/staff.csv"));
        } catch (IOException e) {
            System.out.println("Could not read the file");
        } catch (NoSuchFileException e) {
            System.out.println("No such file");
        }
    }
}
