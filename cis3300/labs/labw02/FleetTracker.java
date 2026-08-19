package fleettracker;

/**
 * FleetTracker
 *
 * This class represents a small fleet of ships. Each ship has a name
 * and a fuel supply, and the two are linked by position. In other
 * words, shipNames[i] and fuelSupply[i] describe the same ship, for
 * every index i. This is called a pair of "parallel arrays."
 *
 * We have not covered classes and objects in lecture yet, so do not
 * worry about how this class itself works. For this lab, focus only on
 * writing the body of each method marked TODO below. The fields and
 * the FleetTrackerDriver class that uses this class are already
 * written for you.
 */
public class FleetTracker {

    // TODO: Declare an array named shipNames that holds these five
    // ship names, in this order:
    //   "Wayfinder", "Nomad", "Sentinel", "Ironclad", "Pathfinder"

    // TODO: Declare an array named fuelSupply that holds these five
    // fuel amounts, in the same order as the names above, so that
    // fuelSupply[i] is the fuel level for the ship at shipNames[i]:
    //   35, 12, 60, 8, 22

    // A ship needs resupply when its fuel is below this amount.
    int resupplyThreshold = 20;

    /**
     * Searches shipNames for the given name and returns its index.
     * Returns -1 if the name is not found anywhere in the array.
     *
     * TODO: Write a linear search over shipNames. Compare strings
     * with .equals(), not ==.
     */
    public int findShipIndex(String shipName) {
        // TODO: write this method
        return -1;
    }

    /**
     * Returns true if the ship at shipIndex has fuel below
     * resupplyThreshold, and false otherwise.
     *
     * TODO: Use shipIndex to look up the correct value in fuelSupply
     * and compare it to resupplyThreshold.
     */
    public boolean isBelowThreshold(int shipIndex) {
        // TODO: write this method
        return false;
    }

    /**
     * Prints one line per ship in the fleet, noting whether it needs
     * resupply.
     *
     * TODO: Loop over every index in shipNames. For each index, use
     * isBelowThreshold() to decide what to print, for example:
     *   Wayfinder: fuel OK
     *   Nomad: NEEDS RESUPPLY
     * You already have the index from your loop, so you do not need
     * to call findShipIndex() here.
     */
    public void printResupplyReport() {
        // TODO: write this method
    }
}
