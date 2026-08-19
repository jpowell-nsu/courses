package fleettracker;

/**
 * FleetTrackerDriver
 *
 * This class is already complete. Do not change it.
 *
 * It creates a FleetTracker object and calls the methods you will
 * write in FleetTracker.java.
 */
public class FleetTrackerDriver {
    public static void main(String[] args) {
        FleetTracker fleet = new FleetTracker();

        int index = fleet.findShipIndex("Sentinel");
        System.out.println("Index of Sentinel: " + index);
        System.out.println("Is Sentinel below threshold: " + fleet.isBelowThreshold(index));

        System.out.println();
        System.out.println("Fleet resupply report:");
        fleet.printResupplyReport();
    }
}
