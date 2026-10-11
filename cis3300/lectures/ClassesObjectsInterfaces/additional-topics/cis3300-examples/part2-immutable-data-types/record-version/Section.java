import java.util.List;

public record Section(String code, List<Student> students) {
    public Section {
        students = List.copyOf(students);   // unmodifiable snapshot
    }
}
