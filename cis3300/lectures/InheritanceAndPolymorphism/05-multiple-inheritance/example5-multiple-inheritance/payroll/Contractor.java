package payroll;

/**
 * An outside contractor who sends the company a monthly invoice. A contractor is
 * Payable, but is not an Employee, so it shares a type with Employee without sharing
 * any of Employee's fields or constructors.
 *
 * @version 1.0
 */
public class Contractor implements Payable {
    private final String name;
    private final String company;
    private double monthlyInvoice;

    /**
     * Creates a contractor.
     *
     * @param name the contractor's name
     * @param company the contractor's company
     * @param monthlyInvoice the amount billed each month
     */
    public Contractor(String name, String company, double monthlyInvoice) {
        this.name = name;
        this.company = company;
        this.monthlyInvoice = monthlyInvoice;
    }

    @Override
    public String getName() {
        return name;
    }

    /** @return the contractor's company */
    public String getCompany() {
        return company;
    }

    @Override
    public double getMonthlyPay() {
        return monthlyInvoice;
    }

    @Override
    public String toString() {
        return name + " (" + company + "), invoice " + String.format("%.2f", monthlyInvoice);
    }
}
