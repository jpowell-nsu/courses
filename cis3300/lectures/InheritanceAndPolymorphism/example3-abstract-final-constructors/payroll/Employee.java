package payroll;

/**
 * The abstract superclass of every kind of employee. It holds what all employees share,
 * an ID and a name, and requires every concrete subclass to say how it is paid.
 * Example 3 version: Employee is now abstract, and salary has moved to SalariedEmployee.
 *
 * @version 1.0
 */
public abstract class Employee {
    /** The number used to build the next employee ID. One copy, shared by every Employee. */
    private static int nextId = 1;

    private final String id;
    private String name;

    /**
     * Abstract classes still have constructors. This one runs every time any kind of
     * employee is created, because every subclass constructor chains up to it.
     *
     * @param name the employee's name
     * @throws IllegalArgumentException if name is null or blank
     */
    protected Employee(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
        this.id = "E" + nextId;
        nextId++;
        this.name = name.strip();
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

    /**
     * Every kind of employee is paid, but each kind computes pay differently,
     * so there is no sensible body to write here.
     *
     * @return one month of pay
     */
    public abstract double getMonthlyPay();

    /** @return the ID, the name, and one month of pay */
    @Override
    public String toString() {
        return id + " " + name + ", monthly pay " + String.format("%.2f", getMonthlyPay());
    }

    /**
     * Two employees are equal when they have the same ID.
     *
     * @param other the object to compare with
     * @return true if other is an Employee with the same ID
     */
    @Override
    public final boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Employee)) {
            return false;
        }
        Employee that = (Employee) other;
        return id.equals(that.id);
    }

    /** @return a hash code based on the ID, consistent with equals */
    @Override
    public final int hashCode() {
        return id.hashCode();
    }
}
