public class Student {
    // Shared by every Student: one copy for the whole class.
    private static int nextId = 1;
    public static final String ID_PREFIX = "S";

    // One copy per object.
    private final String id;
    private final String name;
    private double percent;

    public Student(String name, double percent) {
        this.id = ID_PREFIX + nextId;
        nextId++;
        this.name = name;
        this.percent = percent;
    }

    public static int getStudentsCreated() {
        return nextId - 1;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPercent() { return percent; }
    public void setPercent(double percent) { this.percent = percent; }

    @Override
    public String toString() {
        return id + " " + name + " (" + percent + ")";
    }
}
