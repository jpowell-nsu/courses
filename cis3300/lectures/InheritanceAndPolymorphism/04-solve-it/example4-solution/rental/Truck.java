package rental;

/**
 * A truck is priced by the day plus a charge for every mile.
 *
 * @version 1.0
 */
public class Truck extends Vehicle {
    /** The charge for each mile driven. */
    public static final double PER_MILE = 0.30;

    private final int payloadPounds;

    /**
     * Creates a truck.
     *
     * @param id the agency's ID
     * @param make the manufacturer
     * @param model the model name
     * @param year the model year
     * @param dailyRate the price for one day
     * @param payloadPounds the most weight the truck can carry
     */
    public Truck(String id, String make, String model, int year, double dailyRate,
                 int payloadPounds) {
        super(id, make, model, year, dailyRate);
        this.payloadPounds = payloadPounds;
    }

    /** @return the most weight the truck can carry, in pounds */
    public int getPayloadPounds() {
        return payloadPounds;
    }

    /**
     * @param pounds the weight of the load
     * @return true if the truck can carry that load
     */
    public boolean canHaul(int pounds) {
        return pounds <= payloadPounds;
    }

    /** @return the daily charge plus the mileage charge */
    @Override
    protected double baseCost(int days, int miles) {
        return getDailyRate() * days + PER_MILE * miles;
    }

    @Override
    public String toString() {
        return super.toString() + ", payload " + payloadPounds + " lb";
    }
}
