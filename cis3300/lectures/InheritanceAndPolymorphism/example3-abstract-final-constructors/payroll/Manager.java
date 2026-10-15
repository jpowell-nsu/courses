package payroll;

/**
 * A manager is a salaried employee who also earns a yearly bonus.
 * Example 3 version: Manager now extends SalariedEmployee instead of Employee.
 *
 * @version 1.0
 */
public class Manager extends SalariedEmployee {
    private double bonus;

    /**
     * Creates a manager.
     *
     * @param name the manager's name
     * @param annualSalary the yearly salary
     * @param bonus the yearly bonus
     */
    public Manager(String name, double annualSalary, double bonus) {
        super(name, annualSalary);
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

    /** @return the salaried monthly pay plus one twelfth of the bonus */
    @Override
    public double getMonthlyPay() {
        return super.getMonthlyPay() + bonus / 12;
    }

    /** @return the Employee description plus the bonus */
    @Override
    public String toString() {
        return super.toString() + ", bonus " + String.format("%.2f", bonus);
    }
}
