import java.util.ArrayList;
import java.util.List;

public class StaffLoader {
    record Staff(String name, double salary) { }

    static Staff parse(int lineNumber, String line) throws BadRecordException {
        String[] parts = line.split(",");
        if (parts.length != 2) {
            throw new BadRecordException(lineNumber, "expected name,salary but found \"" + line + "\"");
        }
        try {
            double salary = Double.parseDouble(parts[1]);
            if (salary < 0) {
                throw new BadRecordException(lineNumber, "negative salary " + salary);
            }
            return new Staff(parts[0], salary);
        } catch (NumberFormatException e) {
            throw new BadRecordException(lineNumber, "salary is not a number", e);
        }
    }

    public static void main(String[] args) {
        List<String> lines = List.of("Ada,60000", "Grace,abc", "Alan,-5", "Linus", "Barbara,84000");
        ArrayList<Staff> staff = new ArrayList<>();
        ArrayList<String> errors = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            try {
                staff.add(parse(i + 1, lines.get(i)));
            } catch (BadRecordException e) {
                errors.add(e.getMessage());
            }
        }
        System.out.println("Loaded: " + staff);
        System.out.println("Skipped:");
        for (String error : errors) {
            System.out.println("  " + error);
        }
    }
}
