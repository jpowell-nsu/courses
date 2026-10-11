import java.io.Serializable;

public record Student(String id, String name, double percent)
        implements Comparable<Student>, Serializable {

    private static int nextId = 1;

    public Student {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("percent must be 0 to 100: " + percent);
        }
        name = name.strip();
    }

    public static Student create(String name, double percent) {
        String id = "S" + nextId;
        nextId++;
        return new Student(id, name, percent);
    }

    public LetterGrade letterGrade() {
        return LetterGrade.fromPercent(percent);
    }

    @Override
    public int compareTo(Student other) {
        int result = name.compareTo(other.name);
        if (result != 0) {
            return result;
        }
        result = id.compareTo(other.id);
        if (result != 0) {
            return result;
        }
        return Double.compare(percent, other.percent);
    }
}
