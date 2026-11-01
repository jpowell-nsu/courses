public class ConsoleWriter implements ReportWriter {
    @Override
    public void write(String text) {          // throws nothing: allowed
        System.out.println(text);
    }
}
