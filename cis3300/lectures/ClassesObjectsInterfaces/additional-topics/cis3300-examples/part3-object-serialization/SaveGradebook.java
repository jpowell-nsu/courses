import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveGradebook {
    public static void main(String[] args) throws IOException {
        Gradebook book = new Gradebook("CIS 3300");
        book.add(Student.create("Ada", 91.5));
        book.add(Student.create("Grace", 84.0));
        book.add(Student.create("Alan", 72.5));
        System.out.println("Average: " + book.average());
        System.out.println("Cached before saving? " + book.isAverageCached());

        try (ObjectOutputStream out =
                 new ObjectOutputStream(new FileOutputStream("gradebook.ser"))) {
            out.writeObject(book);
        }
        System.out.println("Saved.");
    }
}
