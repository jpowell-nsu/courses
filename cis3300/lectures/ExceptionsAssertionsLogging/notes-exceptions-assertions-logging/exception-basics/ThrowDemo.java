public class ThrowDemo {
    static double monthlyPay(double annualSalary) {
        if (annualSalary < 0) {
            throw new IllegalArgumentException("negative salary: " + annualSalary);
        }
        return annualSalary / 12;
    }

    public static void main(String[] args) {
        System.out.println(monthlyPay(60000));
        System.out.println(monthlyPay(-5000));
        System.out.println("done");
    }
}
