package payroll;

/**
 * An executive is a manager who also receives yearly stock awards.
 * Example 3 version: the class is final, so no class can extend it.
 *
 * @version 1.0
 */
public final class Executive extends Manager {
    private double stockAwards;

    /**
     * Creates an executive.
     *
     * @param name the executive's name
     * @param annualSalary the yearly salary
     * @param bonus the yearly bonus
     * @param stockAwards the yearly value of stock awards
     */
    public Executive(String name, double annualSalary, double bonus, double stockAwards) {
        super(name, annualSalary, bonus);
        this.stockAwards = stockAwards;
    }

    /** @return the yearly value of stock awards */
    public double getStockAwards() {
        return stockAwards;
    }

    /** @return Manager's monthly pay plus one twelfth of the stock awards */
    @Override
    public double getMonthlyPay() {
        return super.getMonthlyPay() + stockAwards / 12;
    }

    /** @return the Manager description plus the stock awards */
    @Override
    public String toString() {
        return super.toString() + ", stock " + String.format("%.2f", stockAwards);
    }
}
