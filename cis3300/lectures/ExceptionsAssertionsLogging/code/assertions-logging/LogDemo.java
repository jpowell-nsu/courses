import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogDemo {
    private static final Logger LOG = Logger.getLogger(LogDemo.class.getName());

    public static void main(String[] args) {
        String fileName = "files/staff.csv";
        LOG.info("Loading " + fileName);
        LOG.fine("This FINE message is hidden by default");
        try {
            Files.readString(Path.of(fileName));
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Could not read " + fileName, e);
        }
        LOG.log(Level.WARNING, "Skipped line {0}: {1}", new Object[] {2, "salary is not a number"});
    }
}
