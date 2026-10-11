import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadGradebook {
    public static void main(String[] args)
            throws IOException, ClassNotFoundException {
        Gradebook book;
        try (ObjectInputStream in =
                 new ObjectInputStream(new FileInputStream("gradebook.ser"))) {
            book = (Gradebook) in.readObject();   // readObject returns Object
        }
        System.out.println(book.getCourseCode());
        for (Student s : book.getStudents()) {
            System.out.println(s);
        }
        System.out.println("Cached after loading? " + book.isAverageCached());
        System.out.println("Average: " + book.average());

        Student newcomer = Student.create("Barbara", 88.0);
        System.out.println("New student ID: " + newcomer.id());
    }
}
