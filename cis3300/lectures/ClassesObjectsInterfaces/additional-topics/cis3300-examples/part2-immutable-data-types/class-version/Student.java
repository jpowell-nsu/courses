public final class Student {
    private final String id;
    private final String name;
    private final double percent;

    public Student(String id, String name, double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("percent must be 0 to 100: " + percent);
        }
        this.id = id;
        this.name = name;
        this.percent = percent;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPercent() { return percent; }

    // No setter. A "change" produces a new object instead.
    public Student withPercent(double newPercent) {
        return new Student(id, name, newPercent);
    }
}
