package rental;

/**
 * A car is priced by the day only, with unlimited miles.
 *
 * @version 1.0
 */
public class Car extends Vehicle {
    private final int seats;

    /**
     * Creates a car.
     *
     * @param id the agency's ID
     * @param make the manufacturer
     * @param model the model name
     * @param year the model year
     * @param dailyRate the price for one day
     * @param seats the number of seats
     */
    public Car(String id, String make, String model, int year, double dailyRate, int seats) {
        super(id, make, model, year, dailyRate);
        this.seats = seats;
    }

    /** @return the number of seats */
    public int getSeats() {
        return seats;
    }

    /** @return the daily rate times the number of days; miles are free */
    @Override
    protected double baseCost(int days, int miles) {
        return getDailyRate() * days;
    }

    @Override
    public String toString() {
        return super.toString() + ", seats " + seats;
    }
}
