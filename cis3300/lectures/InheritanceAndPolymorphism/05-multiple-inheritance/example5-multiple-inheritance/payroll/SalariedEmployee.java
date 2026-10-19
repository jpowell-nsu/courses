package payroll;

/**
 * An employee paid a fixed annual salary, in twelve equal monthly payments.
 *
 * @version 1.0
 */
public class SalariedEmployee extends Employee {
    private double annualSalary;

    /**
     * Creates a salaried employee.
     *
     * @param name the employee's name
     * @param annualSalary the yearly salary, which cannot be negative
     * @throws IllegalArgumentException if annualSalary is negative
     */
    public SalariedEmployee(String name, double annualSalary) {
        super(name);
        if (annualSalary < 0) {
            throw new IllegalArgumentException("negative salary: " + annualSalary);
        }
        this.annualSalary = annualSalary;
    }

    /** @return the yearly salary */
    public double getAnnualSalary() {
        return annualSalary;
    }

    /**
     * Raises the salary by a percentage.
     *
     * @param percent the raise, such as 5 for five percent
     */
    public void raiseSalary(double percent) {
        annualSalary = annualSalary + annualSalary * percent / 100;
    }

    /** @return one twelfth of the annual salary */
    @Override
    public double getMonthlyPay() {
        return annualSalary / 12;
    }
}
