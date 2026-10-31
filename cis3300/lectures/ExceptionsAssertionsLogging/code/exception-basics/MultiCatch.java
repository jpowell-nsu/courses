public class MultiCatch {
    static double parseSalary(String line) {
        String[] parts = line.split(",");
        return Double.parseDouble(parts[1]);
    }

    public static void main(String[] args) {
        for (String line : new String[] {"Ada,60000", "Grace,abc", "Alan"}) {
            try {
                System.out.println(parseSalary(line));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Bad line \"" + line + "\": " + e.getClass().getSimpleName());
            }
        }
    }
}
