package payroll;

/**
 * A manager is an employee who also earns a yearly bonus.
 * Manager inherits the ID, name, and salary from Employee and adds a bonus.
 *
 * @version 1.0
 */
public class Manager extends Employee {
    private double bonus;

    /**
     * Creates a manager. The Employee part of the object is built first, by super(...).
     *
     * @param name the manager's name
     * @param annualSalary the yearly salary
     * @param bonus the yearly bonus
     */
    public Manager(String name, double annualSalary, double bonus) {
        super(name, annualSalary);   // must be the first statement
        this.bonus = bonus;
    }

    /** @return the yearly bonus */
    public double getBonus() {
        return bonus;
    }

    /** @param bonus the new yearly bonus */
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    /**
     * A manager's monthly pay is the regular monthly pay plus one twelfth of the bonus.
     *
     * @return one month of pay, including the bonus
     */
    @Override
    public double getMonthlyPay() {
        // annualSalary is private in Employee, so ask Employee for its part of the answer.
        return super.getMonthlyPay() + bonus / 12;
    }

    /** @return the Employee description plus the bonus */
    @Override
    public String toString() {
        return super.toString() + ", bonus " + String.format("%.2f", bonus);
    }
}
