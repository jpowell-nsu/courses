// ===========================================================================
//  HashDoS.java   --   an algorithmic-complexity attack on a HashMap.
//
//  A HashMap is supposed to give near-constant-time insert and lookup. That
//  promise depends on keys spreading across buckets. If an attacker can supply
//  keys that all hash to the SAME bucket, the structure degrades and the program
//  does far more work, which is a denial-of-service.
//
//  Three kinds of key, same number of entries each:
//    good        : distinct hash codes, spread normally          (the baseline)
//    colliding   : all hash to 0, and NOT Comparable             (the attack)
//    comparable  : all hash to 0, but implement Comparable       (the mitigation)
//
//  In Java 8 and later, an overloaded bucket whose keys are Comparable is
//  rebuilt as a balanced (red-black) tree, which is why the third column stays
//  fast. That tree is the data-structures fix to this security problem.
//
//  Compile and run:
//      javac HashDoS.java
//      java HashDoS
// ===========================================================================

import java.util.HashMap;

public class HashDoS {

    // Distinct, well-spread hash codes. The normal, healthy case.
    static final class GoodKey {
        final int id;
        GoodKey(int id) { this.id = id; }
        public int hashCode() { return id; }
        public boolean equals(Object o) {
            return (o instanceof GoodKey) && ((GoodKey) o).id == id;
        }
    }

    // Every instance collides into one bucket, and the class is NOT Comparable,
    // so the bucket cannot be turned into a balanced tree. It stays a long chain.
    static final class CollidingKey {
        final int id;
        CollidingKey(int id) { this.id = id; }
        public int hashCode() { return 0; }
        public boolean equals(Object o) {
            return (o instanceof CollidingKey) && ((CollidingKey) o).id == id;
        }
    }

    // Same collision, but Comparable, so Java rebuilds the overloaded bucket as
    // a balanced tree and the cost per operation drops back to logarithmic.
    static final class ComparableKey implements Comparable<ComparableKey> {
        final int id;
        ComparableKey(int id) { this.id = id; }
        public int hashCode() { return 0; }
        public boolean equals(Object o) {
            return (o instanceof ComparableKey) && ((ComparableKey) o).id == id;
        }
        public int compareTo(ComparableKey o) { return Integer.compare(id, o.id); }
    }

    static long timeGood(int n) {
        long t = System.nanoTime();
        HashMap<GoodKey, Integer> m = new HashMap<>();
        for (int i = 0; i < n; i++) m.put(new GoodKey(i), i);
        return (System.nanoTime() - t) / 1_000_000;
    }
    static long timeColliding(int n) {
        long t = System.nanoTime();
        HashMap<CollidingKey, Integer> m = new HashMap<>();
        for (int i = 0; i < n; i++) m.put(new CollidingKey(i), i);
        return (System.nanoTime() - t) / 1_000_000;
    }
    static long timeComparable(int n) {
        long t = System.nanoTime();
        HashMap<ComparableKey, Integer> m = new HashMap<>();
        for (int i = 0; i < n; i++) m.put(new ComparableKey(i), i);
        return (System.nanoTime() - t) / 1_000_000;
    }

    public static void main(String[] args) {
        int[] sizes = { 5000, 10000, 20000, 40000 };
        System.out.printf("%-8s %-16s %-26s %-24s%n",
                "N", "good (ms)", "colliding, NOT Comparable", "colliding, Comparable");
        System.out.println("-".repeat(78));
        for (int n : sizes) {
            long g  = timeGood(n);
            long c  = timeColliding(n);
            long cc = timeComparable(n);
            System.out.printf("%-8d %-16d %-26d %-24d%n", n, g, c, cc);
        }
        System.out.println();
        System.out.println("Watch the 'NOT Comparable' column: each time N doubles it roughly");
        System.out.println("quadruples (quadratic work). The other two columns roughly double.");
    }
}
