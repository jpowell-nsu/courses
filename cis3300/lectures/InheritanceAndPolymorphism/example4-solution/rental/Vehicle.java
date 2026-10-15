package rental;

/**
 * The abstract superclass of every vehicle the agency rents. It stores what every vehicle
 * has, and it owns the one pricing rule that applies to all of them: the weekly discount.
 *
 * @version 1.0
 */
public abstract class Vehicle {
    /** Rentals of at least this many days get the weekly discount. */
    public static final int WEEKLY_DAYS = 7;

    /** The fraction taken off a weekly rental. */
    public static final double WEEKLY_DISCOUNT = 0.10;

    private final String id;
    private final String make;
    private final String model;
    private final int year;
    private final double dailyRate;

    /**
     * Creates the Vehicle part of any vehicle.
     *
     * @param id the agency's ID, such as C100
     * @param make the manufacturer
     * @param model the model name
     * @param year the model year
     * @param dailyRate the price for one day
     * @throws IllegalArgumentException if dailyRate is negative
     */
    protected Vehicle(String id, String make, String model, int year, double dailyRate) {
        if (dailyRate < 0) {
            throw new IllegalArgumentException("daily rate cannot be negative: " + dailyRate);
        }
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.dailyRate = dailyRate;
    }

    public String getId() { return id; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public double getDailyRate() { return dailyRate; }

    /**
     * The cost before any discount. Each kind of vehicle prices a rental differently.
     *
     * @param days the number of rental days
     * @param miles the expected miles driven
     * @return the cost before the weekly discount
     */
    protected abstract double baseCost(int days, int miles);

    /**
     * The price a customer pays. The discount rule is the same for every vehicle, so it
     * lives here once, and the method is final so no subclass can skip it.
     *
     * @param days the number of rental days, at least 1
     * @param miles the expected miles driven, at least 0
     * @return the final price
     * @throws IllegalArgumentException if days or miles is out of range
     */
    public final double quote(int days, int miles) {
        if (days < 1 || miles < 0) {
            throw new IllegalArgumentException("days must be at least 1 and miles at least 0");
        }
        double cost = baseCost(days, miles);   // runs the subclass version
        if (days >= WEEKLY_DAYS) {
            cost = cost * (1 - WEEKLY_DISCOUNT);
        }
        return cost;
    }

    /** @return the ID, year, make, model, and daily rate */
    @Override
    public String toString() {
        return String.format("%s %d %s %s, $%.2f/day", id, year, make, model, dailyRate);
    }
}
