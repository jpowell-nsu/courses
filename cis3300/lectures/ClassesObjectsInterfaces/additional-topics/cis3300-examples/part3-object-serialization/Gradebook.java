import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Gradebook implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String courseCode;
    private final ArrayList<Student> students = new ArrayList<>();

    // Derived from the student list, so there is no reason to save it.
    private transient Double cachedAverage;   // null means "not computed yet"

    public Gradebook(String courseCode) {
        this.courseCode = courseCode;
    }

    public void add(Student s) {
        students.add(s);
        cachedAverage = null;   // the old average is now stale
    }

    public double average() {
        if (cachedAverage == null) {
            double sum = 0.0;
            for (Student s : students) {
                sum += s.percent();
            }
            cachedAverage = students.isEmpty() ? 0.0 : sum / students.size();
        }
        return cachedAverage;
    }

    public String getCourseCode() { return courseCode; }
    public List<Student> getStudents() { return List.copyOf(students); }

    // For the demo only: shows whether the cache survived.
    public boolean isAverageCached() { return cachedAverage != null; }
}
