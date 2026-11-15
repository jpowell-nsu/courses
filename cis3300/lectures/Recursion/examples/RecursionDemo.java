/**
 * Examples for the CIS 3300 recursion notes: factorial, Fibonacci, a traced factorial,
 * a helper method with an extra parameter, and the loop versions.
 */
public class RecursionDemo {

    // ----- Factorial -----
    public static long factorial(int n) {
        if (n <= 1) {                    // base case
            return 1;
        }
        return n * factorial(n - 1);     // recursive step: a smaller n
    }

    // ----- Factorial with a trace, to show the calls and the returns -----
    public static long tracedFactorial(int n, String indent) {
        System.out.println(indent + "call factorial(" + n + ")");
        long result;
        if (n <= 1) {
            result = 1;
        } else {
            result = n * tracedFactorial(n - 1, indent + "   ");
        }
        System.out.println(indent + "return " + result + " from factorial(" + n + ")");
        return result;
    }

    // ----- Fibonacci -----
    public static long fib(int n) {
        if (n <= 1) {                    // base cases: fib(0) = 0, fib(1) = 1
            return n;
        }
        return fib(n - 1) + fib(n - 2);  // two recursive calls
    }

    // ----- Fibonacci that counts its calls -----
    private static long calls = 0;

    public static long countedFib(int n) {
        calls++;
        if (n <= 1) {
            return n;
        }
        return countedFib(n - 1) + countedFib(n - 2);
    }

    // ----- A helper method with an extra parameter -----
    public static int sum(int[] values) {
        return sum(values, 0);           // start at index 0
    }

    private static int sum(int[] values, int index) {
        if (index == values.length) {    // base case: no elements left
            return 0;
        }
        return values[index] + sum(values, index + 1);
    }

    // ----- Loop versions -----
    public static long factorialLoop(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    public static long fibLoop(int n) {
        long previous = 0;
        long current = 1;
        if (n == 0) {
            return 0;
        }
        for (int i = 2; i <= n; i++) {
            long next = previous + current;
            previous = current;
            current = next;
        }
        return current;
    }

    public static void main(String[] args) {
        System.out.println("factorial(5) = " + factorial(5));
        System.out.println("factorial(0) = " + factorial(0));
        System.out.println();

        tracedFactorial(3, "");
        System.out.println();

        for (int n = 0; n <= 10; n++) {
            System.out.print(fib(n) + " ");
        }
        System.out.println();

        for (int n : new int[] {5, 10, 20, 30}) {
            calls = 0;
            long value = countedFib(n);
            System.out.println("fib(" + n + ") = " + value + " took " + calls + " calls");
        }
        System.out.println();

        int[] scores = {90, 85, 72, 100};
        System.out.println("sum = " + sum(scores));
        System.out.println("sum of empty array = " + sum(new int[0]));
        System.out.println();

        System.out.println("factorialLoop(5) = " + factorialLoop(5));
        System.out.println("fibLoop(30) = " + fibLoop(30));
        System.out.println();

        System.out.println("factorial(20) = " + factorial(20));
        System.out.println("factorial(21) = " + factorial(21));
    }
}
