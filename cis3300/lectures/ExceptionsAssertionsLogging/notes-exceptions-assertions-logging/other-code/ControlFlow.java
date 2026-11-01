public class ControlFlow {
    public static void main(String[] args) {
        double[] salaries = {60000, 90000, 72000};

        // Do not do this: an exception is used to end the loop
        double total = 0;
        try {
            int i = 0;
            while (true) {
                total += salaries[i];
                i++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // the loop "ends" here
        }
        System.out.println(total);

        // Do this: the loop condition ends the loop
        double total2 = 0;
        for (int i = 0; i < salaries.length; i++) {
            total2 += salaries[i];
        }
        System.out.println(total2);
    }
}
