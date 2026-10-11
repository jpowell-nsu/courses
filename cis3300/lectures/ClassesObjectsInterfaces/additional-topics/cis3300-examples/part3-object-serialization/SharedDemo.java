import java.io.*;
import java.util.ArrayList;

public class SharedDemo {
    public static void main(String[] args) throws Exception {
        Student ada = Student.create("Ada", 91.5);
        ArrayList<Object> items = new ArrayList<>();
        items.add(ada);
        items.add(ada);              // the same object, twice
        items.add(LetterGrade.B);

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(bytes)) {
            out.writeObject(items);
        }
        ArrayList<?> copy;
        try (ObjectInputStream in = new ObjectInputStream(
                 new ByteArrayInputStream(bytes.toByteArray()))) {
            copy = (ArrayList<?>) in.readObject();
        }
        System.out.println(copy.get(0) == copy.get(1));   // shared reference kept?
        System.out.println(copy.get(0) == ada);           // same as the original?
        System.out.println(copy.get(2) == LetterGrade.B); // same enum constant?
    }
}
