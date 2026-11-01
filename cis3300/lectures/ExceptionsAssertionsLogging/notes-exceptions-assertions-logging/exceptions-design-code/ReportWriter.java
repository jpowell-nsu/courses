import java.io.IOException;

public interface ReportWriter {
    void write(String text) throws IOException;
}
