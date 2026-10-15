package payroll;

/**
 * Anything the company pays every month. Employees are Payable, and so are contractors,
 * who are not employees at all.
 *
 * @version 1.0
 */
public interface Payable {
    /** @return the name to print on the payment */
    String getName();

    /** @return the amount owed for one month */
    double getMonthlyPay();

    /**
     * A ready-made method that every Payable class inherits.
     *
     * @return one formatted line for a payment report
     */
    default String paymentLine() {
        return String.format("%-10s %10.2f", getName(), getMonthlyPay());
    }
}
