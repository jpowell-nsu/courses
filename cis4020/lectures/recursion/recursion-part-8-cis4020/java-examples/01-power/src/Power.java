public class Power {
    // Computes base raised to the given exponent using recursion.
    // Precondition: exponent is non-negative.
    public static int power(int base, int exponent) {
        if (exponent == 0) {                // base case
            return 1;
        }
        return base * power(base, exponent - 1);
    }
}
