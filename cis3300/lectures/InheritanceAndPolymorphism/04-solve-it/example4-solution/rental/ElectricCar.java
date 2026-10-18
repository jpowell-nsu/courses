package rental;

/**
 * Extension: an electric car is a car with a daily charging fee. Adding it requires
 * this class and one new case in RentalAgency.loadFleet, and nothing else.
 *
 * @version 1.0
 */
public class ElectricCar extends Car {
    /** The charging fee for each rental day. */
    public static final double CHARGING_FEE = 6.00;

    /**
     * Creates an electric car.
     *
     * @param id the agency's ID
     * @param make the manufacturer
     * @param model the model name
     * @param year the model year
     * @param dailyRate the price for one day
     * @param seats the number of seats
     */
    public ElectricCar(String id, String make, String model, int year, double dailyRate,
                       int seats) {
        super(id, make, model, year, dailyRate, seats);
    }

    /** @return the car price plus the charging fee for each day */
    @Override
    protected double baseCost(int days, int miles) {
        return super.baseCost(days, miles) + CHARGING_FEE * days;
    }
}
