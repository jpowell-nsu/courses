import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FileLogDemo {
    private static final Logger LOG = Logger.getLogger(FileLogDemo.class.getName());

    public static void main(String[] args) throws IOException {
        FileHandler handler = new FileHandler("files/payroll.log");
        handler.setFormatter(new SimpleFormatter());   // the default is XML
        handler.setLevel(Level.FINE);
        LOG.addHandler(handler);
        LOG.setLevel(Level.FINE);

        LOG.info("Payroll run started");
        LOG.fine("Read 5 lines");
        LOG.warning("Skipped 3 bad lines");
        handler.close();
    }
}
