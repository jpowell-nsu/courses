package cargobay;

/**
 * CargoBayDriver
 *
 * This class is already complete. Do not change it.
 *
 * It creates a CargoBay object and calls the methods you will write in
 * CargoBay.java.
 */
public class CargoBayDriver {
    public static void main(String[] args) {
        CargoBay bay = new CargoBay();

        System.out.println("Empty slots: " + bay.countEmptySlots());
        System.out.println("Has an overloaded slot: " + bay.hasOverloadedSlot());

        System.out.println();
        System.out.println("Grid report:");
        bay.printGridReport();
    }
}
