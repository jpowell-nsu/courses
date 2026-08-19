package cargohold;

/**
 * CargoHold
 *
 * This class represents the cargo hold of a single ship. It stores how
 * many units of each supply type are aboard the ship, and it will
 * provide a few methods that summarize that cargo.
 *
 * We have not covered classes and objects in lecture yet, so do not
 * worry about how this class itself works. For this lab, focus only on
 * writing the body of each method marked TODO below. The field
 * (cargoUnits) and the CargoHoldDriver class that uses this class are
 * already written for you. We will cover what a class and an object
 * are, and how they work together, in an upcoming lecture.
 */
public class CargoHold {

    // TODO: Declare an array named cargoUnits that holds the
    // quantity of each supply type aboard the ship. Each index
    // represents a different supply type (for example, index 0
    // might be food rations, index 1 might be spare parts, and so
    // on). Store these eight values, in this order:
    //   42, 17, 88, 5, 63, 24, 71, 9
    // Think about what type of value each of these is, and what an
    // array declaration with an initial set of values looks like.

    /**
     * Returns the total number of units across every supply type.
     *
     * TODO: Add up every value in cargoUnits and return the sum.
     */
    public int getTotalUnits() {
        // TODO: write this method
        return 0;
    }

    /**
     * Returns the amount of the most heavily stocked supply type.
     *
     * TODO: Find and return the largest value in cargoUnits.
     */
    public int getMostStockedAmount() {
        // TODO: write this method
        return 0;
    }

    /**
     * Returns the amount of the least stocked supply type.
     *
     * TODO: Find and return the smallest value in cargoUnits.
     */
    public int getLeastStockedAmount() {
        // TODO: write this method
        return 0;
    }

    /**
     * Returns the average number of units per supply type, as a
     * double so the result can include a decimal portion.
     *
     * TODO: Divide the total units by the number of supply types.
     * Consider calling getTotalUnits() instead of adding the array up
     * a second time, and watch out for integer division.
     */
    public double getAverageUnits() {
        // TODO: write this method
        return 0.0;
    }

    /**
     * Prints a short, readable report of this cargo hold using the
     * methods above.
     *
     * TODO: Call the four methods above and print their results with
     * a clear label for each line, for example:
     *   Total units aboard: 319
     *   Most stocked amount: 88
     *   Least stocked amount: 5
     *   Average units per supply type: 39.9
     */
    public void printCargoReport() {
        // TODO: write this method
    }
}
