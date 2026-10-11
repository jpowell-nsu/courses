import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Part 2, Sections 4 through 6: records, enums, and Comparable.
public class RecordDemo {
    public static void main(String[] args) {
        // ----- Records -----
        Student ada = new Student("S1", "  Ada  ", 91.5);   // name is stripped
        System.out.println(ada);                      // Student[id=S1, name=Ada, percent=91.5]
        System.out.println(ada.name());               // Ada
        Student copy = new Student("S1", "Ada", 91.5);
        System.out.println(ada.equals(copy));         // true: same values
        System.out.println(ada == copy);              // false: two different objects

        try {
            new Student("S9", "Bob", 140);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());       // percent must be 0 to 100: 140.0
        }

        // ----- A record holding a list -----
        ArrayList<Student> original = new ArrayList<>();
        original.add(ada);
        Section section = new Section("CIS 3300", original);
        original.clear();
        System.out.println(section.students().size());   // 1: the section kept its copy
        try {
            section.students().add(copy);
        } catch (UnsupportedOperationException e) {
            System.out.println("The section's list cannot be changed");
        }

        // ----- Records and CSV -----
        String line = "S7,Edsger,77.0";
        String[] parts = line.split(",");
        Student fromFile = new Student(parts[0], parts[1], Double.parseDouble(parts[2]));
        System.out.println(fromFile);

        // ----- Enums -----
        LetterGrade g = LetterGrade.fromPercent(89.49);
        System.out.println(g + " " + g.getGradePoints());     // B 3.0
        System.out.println(LetterGrade.valueOf("B") == LetterGrade.B);   // true
        System.out.println(LetterGrade.B.ordinal());           // 1

        // ----- Comparable -----
        ArrayList<Student> list = new ArrayList<>();
        list.add(Student.create("Grace", 84.0));
        list.add(Student.create("Alan", 72.5));
        list.add(Student.create("Ada", 89.49));
        list.add(Student.create("Barbara", 58.0));
        Collections.sort(list);
        for (Student s : list) {
            System.out.println(s.name() + " " + s.id() + " " + s.letterGrade());
        }

        List<LetterGrade> grades = new ArrayList<>(
                List.of(LetterGrade.C, LetterGrade.A, LetterGrade.F, LetterGrade.B));
        Collections.sort(grades);
        System.out.println(grades);   // [A, B, C, F]

        // ----- Enums and switch -----
        for (Student s : list) {
            String message = switch (s.letterGrade()) {
                case A, B -> "Strong work";
                case C    -> "Solid";
                case D, F -> "Please see me during office hours";
            };
            System.out.println(s.name() + ": " + message);
        }
    }
}
