import java.util.ArrayList;

public class Roster {
    private final String courseCode;
    private final ArrayList<Student> students = new ArrayList<>();

    public Roster(String courseCode) {
        this.courseCode = courseCode;
    }

    public void add(Student s) {
        if (students.size() >= Config.MAX_ROSTER_SIZE) {
            throw new IllegalStateException(courseCode + " is full");
        }
        students.add(s);
    }

    // ----- Static nested class: no Roster object required -----
    public static class Stats {
        private final int count;
        private final double low;
        private final double high;
        private final double average;

        public Stats(int count, double low, double high, double average) {
            this.count = count;
            this.low = low;
            this.high = high;
            this.average = average;
        }

        public int getCount() { return count; }
        public double getLow() { return low; }
        public double getHigh() { return high; }
        public double getAverage() { return average; }

        @Override
        public String toString() {
            return count + " students, low " + low + ", high " + high
                    + ", average " + average;
        }
    }

    public Stats computeStats() {
        if (students.isEmpty()) {
            return new Stats(0, 0.0, 0.0, 0.0);
        }
        double low = students.get(0).getPercent();
        double high = low;
        double sum = 0.0;
        for (Student s : students) {
            low = Math.min(low, s.getPercent());
            high = Math.max(high, s.getPercent());
            sum += s.getPercent();
        }
        return new Stats(students.size(), low, high, sum / students.size());
    }

    // ----- Private inner class: tied to one Roster object -----
    public void printReport() {
        Report report = new Report();   // linked to this Roster object
        report.print();
    }

    private class Report {
        private int lineNumber = 1;

        void print() {
            System.out.println("Report for " + courseCode);   // outer Roster's field
            for (Student s : students) {                     // outer Roster's list
                System.out.println(lineNumber + ". " + s);
                lineNumber++;
            }
        }
    }

    // ----- Used with the anonymous class example -----
    public void printIf(StudentFilter filter) {
        for (Student s : students) {
            if (filter.accept(s)) {
                System.out.println(s);
            }
        }
    }
}
