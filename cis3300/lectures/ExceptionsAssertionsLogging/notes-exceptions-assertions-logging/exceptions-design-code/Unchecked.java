public class Unchecked implements ReportWriter {
    @Override
    public void write(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text is required");   // unchecked: always allowed
        }
        System.out.println(text);
    }
}
