public class AssertDemo {
    private static double average(double[] values) {
        assert values.length > 0 : "average of an empty array";
        double total = 0;
        for (double v : values) {
            total += v;
        }
        return total / values.length;
    }

    public static void main(String[] args) {
        System.out.println(average(new double[] {5000, 7500}));
        System.out.println(average(new double[0]));
    }
}
