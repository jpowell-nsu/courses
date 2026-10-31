import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class CatchDemo {
    static String readStaff(String fileName) throws IOException {
        return Files.readString(Path.of(fileName));
    }

    public static void main(String[] args) {
        for (String name : new String[] {"files/staff.csv", "files/staf.csv"}) {
            try {
                String text = readStaff(name);
                System.out.println("Read " + text.lines().count() + " lines");
            } catch (NoSuchFileException e) {
                System.out.println("No such file: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Could not read the file: " + e.getMessage());
            }
        }
    }
}
