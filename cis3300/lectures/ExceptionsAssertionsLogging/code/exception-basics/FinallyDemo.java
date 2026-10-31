public class FinallyDemo {
    static void process(String salary) {
        System.out.println("open the payroll file");
        try {
            System.out.println("monthly pay: " + Double.parseDouble(salary) / 12);
        } catch (NumberFormatException e) {
            System.out.println("not a number: " + salary);
        } finally {
            System.out.println("close the payroll file");
        }
    }

    public static void main(String[] args) {
        process("60000");
        System.out.println("---");
        process("abc");
    }
}
