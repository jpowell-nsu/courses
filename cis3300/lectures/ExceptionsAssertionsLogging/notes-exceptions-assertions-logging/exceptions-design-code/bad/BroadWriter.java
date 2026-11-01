public class BroadWriter implements ReportWriter {
    @Override
    public void write(String text) throws Exception {   // broader: not allowed
        System.out.println(text);
    }
}
