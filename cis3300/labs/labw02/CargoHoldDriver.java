package cargohold;

/**
 * CargoHoldDriver
 *
 * This class is already complete. Do not change it.
 *
 * It creates a CargoHold object and calls the methods you will write
 * in CargoHold.java. The line below that reads
 * "CargoHold hold = new CargoHold();" creates, or "instantiates," a
 * CargoHold object named hold. We will explain what this means in more
 * detail soon. For now, just know that hold is how this class refers
 * to the CargoHold you are building, and that hold.getTotalUnits()
 * calls the getTotalUnits method that belongs to that object.
 */
public class CargoHoldDriver {
    public static void main(String[] args) {
        CargoHold hold = new CargoHold();

        System.out.println("Total units aboard: " + hold.getTotalUnits());
        System.out.println("Most stocked amount: " + hold.getMostStockedAmount());
        System.out.println("Least stocked amount: " + hold.getLeastStockedAmount());
        System.out.println("Average units per supply type: " + hold.getAverageUnits());

        System.out.println();
        System.out.println("Formatted report:");
        hold.printCargoReport();
    }
}
