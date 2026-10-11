// Part 2, Sections 1 and 3: String is immutable, and a hand-written
// immutable Student class returns a new object instead of changing itself.
public class ImmutableClassDemo {
    public static void main(String[] args) {
        String x = "ada";
        String y = x.toUpperCase();
        System.out.println(x + " " + y);   // ada ADA   (x did not change)

        Student s = new Student("S1", "Ada", 88.0);
        Student t = s.withPercent(91.5);
        System.out.println(s.getPercent() + " " + t.getPercent());   // 88.0 91.5
    }
}
