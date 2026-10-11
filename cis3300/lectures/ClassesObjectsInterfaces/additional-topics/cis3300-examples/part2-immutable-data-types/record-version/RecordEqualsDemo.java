import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

// Part 2, "Records are only as equal as their components."
// A record's equals calls each object component's own equals method, and
// compares primitive components such as double by value.
public class RecordEqualsDemo {

    // A plain class that does NOT override equals or hashCode.
    static class Advisor {
        private final String name;

        Advisor(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    record Scores(String name, int[] values) { }
    record Mentoring(String student, Advisor advisor) { }
    record Group(String name, List<String> members) { }   // no List.copyOf on purpose
    record Reading(double value) { }

    // The fix for arrays: write equals, hashCode, and toString yourself.
    record FixedScores(String name, int[] values) {
        @Override
        public boolean equals(Object other) {
            return other instanceof FixedScores f
                    && name.equals(f.name)
                    && Arrays.equals(values, f.values);
        }

        @Override
        public int hashCode() {
            return 31 * name.hashCode() + Arrays.hashCode(values);
        }

        @Override
        public String toString() {
            return "FixedScores[name=" + name + ", values=" + Arrays.toString(values) + "]";
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Array components ---");
        Scores a = new Scores("Ada", new int[] {90, 85});
        Scores b = new Scores("Ada", new int[] {90, 85});
        System.out.println(a.equals(b));   // false: two different array objects
        System.out.println(a);             // something like Scores[name=Ada, values=[I@65ab7765]

        FixedScores fa = new FixedScores("Ada", new int[] {90, 85});
        FixedScores fb = new FixedScores("Ada", new int[] {90, 85});
        System.out.println(fa.equals(fb)); // true: Arrays.equals compares contents
        System.out.println(fa);            // FixedScores[name=Ada, values=[90, 85]]

        System.out.println("--- A component class without equals ---");
        Mentoring m1 = new Mentoring("Ada", new Advisor("Dr. Hopper"));
        Mentoring m2 = new Mentoring("Ada", new Advisor("Dr. Hopper"));
        System.out.println(m1.equals(m2)); // false: two different Advisor objects
        Advisor shared = new Advisor("Dr. Hopper");
        System.out.println(new Mentoring("Ada", shared)
                .equals(new Mentoring("Ada", shared)));   // true: the same Advisor object

        System.out.println("--- Components that compare by content ---");
        Group g1 = new Group("Team", new ArrayList<>(List.of("Ada", "Alan")));
        Group g2 = new Group("Team", new ArrayList<>(List.of("Ada", "Alan")));
        System.out.println(g1.equals(g2)); // true: List compares its contents

        System.out.println("--- A component that can still change ---");
        HashSet<Group> set = new HashSet<>();
        set.add(g1);
        g1.members().add("Grace");         // changes g1's hash code
        System.out.println(set.contains(g1));   // false: the set can no longer find it

        System.out.println("--- Components of type double ---");
        double nan = Double.NaN;
        System.out.println(nan == nan);                                    // false
        System.out.println(new Reading(nan).equals(new Reading(nan)));    // true
        System.out.println(0.0 == -0.0);                                   // true
        System.out.println(new Reading(0.0).equals(new Reading(-0.0)));   // false
        System.out.println(new Reading(0.1 + 0.2).equals(new Reading(0.3)));   // false: rounding
        System.out.println(0.1 + 0.2);     // 0.30000000000000004
    }
}
