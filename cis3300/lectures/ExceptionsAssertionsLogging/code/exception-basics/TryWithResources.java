import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TryWithResources {
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter("files/report.txt")) {
            out.println("Payroll Report");
            out.println("Ada 5000.00");
        } catch (FileNotFoundException e) {
            System.out.println("Could not create the report: " + e.getMessage());
        }
        System.out.println("report written");
    }
}
