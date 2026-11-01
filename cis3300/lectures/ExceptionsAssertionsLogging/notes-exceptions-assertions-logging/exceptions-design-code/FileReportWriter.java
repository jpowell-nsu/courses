import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileReportWriter implements ReportWriter {
    @Override
    public void write(String text) throws FileNotFoundException {   // narrower: allowed
        try (PrintWriter out = new PrintWriter("report.txt")) {
            out.println(text);
        }
    }
}
