import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;

public class EndGracefully {
    public static void main(String[] args) {
        String fileName = args.length > 0 ? args[0] : "files/staff.csv";
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of(fileName));
        } catch (NoSuchFileException e) {
            System.out.println("Could not find " + fileName + ". Check the file name and try again.");
            return;
        } catch (IOException e) {
            System.out.println("Could not read " + fileName + ": " + e.getMessage());
            return;
        }
        System.out.println("Loaded " + lines.size() + " lines from " + fileName);
    }
}
