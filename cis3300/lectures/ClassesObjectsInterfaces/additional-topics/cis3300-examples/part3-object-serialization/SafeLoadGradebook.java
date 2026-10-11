import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;

// Part 3, "If you must deserialize": load with a filter that allows only
// the classes we expect. Run SaveGradebook first to create gradebook.ser.
public class SafeLoadGradebook {
    public static Gradebook load(String fileName)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream in =
                 new ObjectInputStream(new FileInputStream(fileName))) {
            // Allow only the classes we expect; reject everything else.
            in.setObjectInputFilter(ObjectInputFilter.Config.createFilter(
                "maxdepth=10;Gradebook;Student;java.util.ArrayList;java.lang.Object;!*"));
            return (Gradebook) in.readObject();
        }
    }

    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        Gradebook book = load("gradebook.ser");
        System.out.println(book.getCourseCode() + ": "
                + book.getStudents().size() + " students loaded safely");
    }
}
