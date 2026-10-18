package payroll;

/**
 * An employee paid by the hour. Hours above the regular monthly hours are paid at
 * one and a half times the hourly rate.
 *
 * @version 1.0
 */
public class HourlyEmployee extends Employee {
    /** Hours in a month that are paid at the regular rate. */
    public static final double REGULAR_HOURS = 160;

    /** The multiplier for hours beyond REGULAR_HOURS. */
    public static final double OVERTIME_RATE = 1.5;

    private double hourlyRate;
    private double hoursThisMonth;

    /**
     * Creates an hourly employee with no hours recorded yet.
     *
     * @param name the employee's name
     * @param hourlyRate the pay for one regular hour
     * @throws IllegalArgumentException if hourlyRate is negative
     */
    public HourlyEmployee(String name, double hourlyRate) {
        super(name);
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("rate cannot be negative: " + hourlyRate);
        }
        this.hourlyRate = hourlyRate;
    }

    /** @return the pay for one regular hour */
    public double getHourlyRate() {
        return hourlyRate;
    }

    /** @return the hours recorded this month */
    public double getHoursThisMonth() {
        return hoursThisMonth;
    }

    /**
     * Records the hours worked this month.
     *
     * @param hours the hours worked, which cannot be negative
     * @throws IllegalArgumentException if hours is negative
     */
    public void setHoursThisMonth(double hours) {
        if (hours < 0) {
            throw new IllegalArgumentException("hours cannot be negative: " + hours);
        }
        this.hoursThisMonth = hours;
    }

    /** @return regular pay plus any overtime pay for this month */
    @Override
    public double getMonthlyPay() {
        double regular = Math.min(hoursThisMonth, REGULAR_HOURS);
        double overtime = Math.max(hoursThisMonth - REGULAR_HOURS, 0);
        return regular * hourlyRate + overtime * hourlyRate * OVERTIME_RATE;
    }
}
