package cargobay;

/**
 * CargoBay
 *
 * This class represents the cargo bay of a single ship, laid out as a
 * grid. Each row is a bay level, and each column is a storage slot
 * within that level. A value of 0 means the slot is empty.
 *
 * We have not covered classes and objects, or two-dimensional arrays,
 * in lecture yet, so do not worry about how this class itself works.
 * For this lab, focus only on writing the body of each method marked
 * TODO below. The fields and the CargoBayDriver class that uses this
 * class are already written for you.
 */
public class CargoBay {

    // TODO: Declare a two-dimensional array named bayGrid where
    // bayGrid[row][column] holds the amount stored in that slot. A
    // value of 0 means the slot is empty. There are three rows (bay
    // levels) and four columns (storage slots per level). Store
    // these values:
    //   Row 0: 12, 0, 8, 0
    //   Row 1: 0, 0, 15, 3
    //   Row 2: 7, 22, 0, 0

    // A slot is considered overloaded if its value is greater than
    // this capacity.
    int slotCapacity = 20;

    /**
     * Returns the number of empty slots (value 0) in bayGrid.
     *
     * TODO: Use a nested loop, one loop for rows and one loop for
     * columns, to visit every slot in bayGrid and count how many are
     * equal to 0.
     */
    public int countEmptySlots() {
        // TODO: write this method
        return 0;
    }

    /**
     * Returns true if any slot in bayGrid holds more than
     * slotCapacity, and false otherwise.
     *
     * TODO: Use a nested loop to check every slot. You may return
     * true as soon as you find one slot over capacity.
     */
    public boolean hasOverloadedSlot() {
        // TODO: write this method
        return false;
    }

    /**
     * Prints the grid, row by row, followed by a short summary using
     * the methods above.
     *
     * TODO: Use a nested loop to print each row of bayGrid on its own
     * line, then print the results of countEmptySlots() and
     * hasOverloadedSlot() with clear labels.
     */
    public void printGridReport() {
        // TODO: write this method
    }
}
