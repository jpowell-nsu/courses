package rental;

/**
 * A motorcycle is priced by the day plus a one-time safety gear fee per rental.
 *
 * @version 1.0
 */
public class Motorcycle extends Vehicle {
    /** The helmet and gear fee, charged once per rental. */
    public static final double GEAR_FEE = 12.00;

    private final int engineCc;

    /**
     * Creates a motorcycle.
     *
     * @param id the agency's ID
     * @param make the manufacturer
     * @param model the model name
     * @param year the model year
     * @param dailyRate the price for one day
     * @param engineCc the engine size in cubic centimeters
     */
    public Motorcycle(String id, String make, String model, int year, double dailyRate,
                      int engineCc) {
        super(id, make, model, year, dailyRate);
        this.engineCc = engineCc;
    }

    /** @return the engine size in cubic centimeters */
    public int getEngineCc() {
        return engineCc;
    }

    /** @return the daily charge plus the gear fee */
    @Override
    protected double baseCost(int days, int miles) {
        return getDailyRate() * days + GEAR_FEE;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + engineCc + " cc";
    }
}
