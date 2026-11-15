/** Mistakes from the notes, run for real. */
public class Traps {

    // Unreachable base case for negative input.
    public static long factorialEq(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorialEq(n - 1);
    }

    public static void main(String[] args) {
        System.out.println("factorialEq(5) = " + factorialEq(5));
        System.out.println("factorialEq(-3) = " + factorialEq(-3));
    }
}
