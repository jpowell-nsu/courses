package payroll;

/**
 * An employee with an automatically assigned ID, a name, and an annual salary.
 * In Example 1, Employee is the superclass that Manager extends.
 *
 * @version 1.0
 */
public class Employee {
    /** The number used to build the next employee ID. One copy, shared by every Employee. */
    private static int nextId = 1;

    private final String id;
    private String name;
    private double annualSalary;

    /**
     * Creates an employee and assigns the next available ID.
     *
     * @param name the employee's name
     * @param annualSalary the yearly salary, which cannot be negative
     * @throws IllegalArgumentException if annualSalary is negative
     */
    public Employee(String name, double annualSalary) {
        if (annualSalary < 0) {
            throw new IllegalArgumentException("negative salary: " + annualSalary);
        }
        this.id = "E" + nextId;
        nextId++;
        this.name = name;
        this.annualSalary = annualSalary;
    }

    /** @return the employee ID, such as E1 */
    public String getId() {
        return id;
    }

    /** @return the employee's name */
    public String getName() {
        return name;
    }

    /** @param name the new name */
    public void setName(String name) {
        this.name = name;
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

    /** @return one month of pay */
    public double getMonthlyPay() {
        return annualSalary / 12;
    }

    /** @return the ID, the name, and one month of pay */
    @Override
    public String toString() {
        return id + " " + name + ", monthly pay " + String.format("%.2f", getMonthlyPay());
    }

    /**
     * Two employees are equal when they have the same ID. The rule is the same for every
     * kind of employee, so the method is final and uses instanceof.
     *
     * @param other the object to compare with
     * @return true if other is an Employee with the same ID
     */
    @Override
    public final boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Employee)) {   // also false when other is null
            return false;
        }
        Employee that = (Employee) other;     // safe: the instanceof check passed
        return id.equals(that.id);
    }

    /** @return a hash code based on the ID, consistent with equals */
    @Override
    public final int hashCode() {
        return id.hashCode();
    }
}
