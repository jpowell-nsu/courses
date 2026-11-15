import java.util.Arrays;

/** Solutions to the Try It problems, for checking the notes. */
public class TryItSolutions {

    public static int sumTo(int n) {
        if (n <= 0) {
            return 0;
        }
        return n + sumTo(n - 1);
    }

    public static double power(double x, int n) {
        if (n == 0) {
            return 1;
        }
        return x * power(x, n - 1);
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void countdown(int n) {
        if (n == 0) {
            System.out.println("Liftoff!");
            return;
        }
        System.out.println(n);
        countdown(n - 1);
    }

    public static void reverse(int[] a) {
        reverse(a, 0, a.length - 1);
    }

    private static void reverse(int[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int temp = a[low];
        a[low] = a[high];
        a[high] = temp;
        reverse(a, low + 1, high - 1);
    }

    public static void main(String[] args) {
        System.out.println(sumTo(100));
        System.out.println(power(2, 10));
        System.out.println(gcd(48, 36));
        countdown(3);
        int[] a = {1, 2, 3, 4, 5};
        reverse(a);
        System.out.println(Arrays.toString(a));
    }
}
