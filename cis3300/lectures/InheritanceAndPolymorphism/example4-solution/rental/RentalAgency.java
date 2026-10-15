package rental;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Example 4 solution: a vehicle rental agency. One list holds every vehicle, each vehicle
 * prices itself, and subclass-only features are reached with instanceof.
 *
 * @version 1.0
 */
public class RentalAgency {

    /**
     * Reads the fleet file and builds one object per row. This is the only method that
     * needs to know every subclass by name.
     *
     * @param file the CSV file, with a header row
     * @return every vehicle in the file
     * @throws IOException if the file cannot be read
     */
    public static ArrayList<Vehicle> loadFleet(Path file) throws IOException {
        ArrayList<Vehicle> fleet = new ArrayList<>();
        List<String> lines = Files.readAllLines(file);
        for (String line : lines.subList(1, lines.size())) {
            String[] p = line.split(",");
            String type = p[0];
            String id = p[1];
            String make = p[2];
            String model = p[3];
            int year = Integer.parseInt(p[4]);
            double rate = Double.parseDouble(p[5]);
            int extra = Integer.parseInt(p[6]);

            Vehicle v = switch (type) {
                case "CAR" -> new Car(id, make, model, year, rate, extra);
                case "TRUCK" -> new Truck(id, make, model, year, rate, extra);
                case "MOTORCYCLE" -> new Motorcycle(id, make, model, year, rate, extra);
                case "ELECTRIC" -> new ElectricCar(id, make, model, year, rate, extra);   // extension
                default -> throw new IllegalArgumentException("unknown vehicle type: " + type);
            };
            fleet.add(v);   // upcast: a Car, Truck, or Motorcycle stored as a Vehicle
        }
        return fleet;
    }

    /**
     * Prints a quote for every vehicle and returns the cheapest one. No instanceof is
     * needed: each object runs its own baseCost.
     *
     * @param fleet the vehicles to quote, which must not be empty
     * @param days the number of rental days
     * @param miles the expected miles
     * @return the vehicle with the lowest quote
     */
    public static Vehicle quoteAll(ArrayList<Vehicle> fleet, int days, int miles) {
        System.out.printf("Quotes for %d days and %d miles:%n", days, miles);
        Vehicle cheapest = fleet.get(0);
        for (Vehicle v : fleet) {
            double price = v.quote(days, miles);
            System.out.printf("  %-38s $%8.2f%n", v.getId() + " " + v.getMake() + " "
                    + v.getModel(), price);
            if (price < cheapest.quote(days, miles)) {
                cheapest = v;
            }
        }
        System.out.println("  Cheapest: " + cheapest.getId() + " " + cheapest.getModel());
        return cheapest;
    }

    /**
     * Finds the trucks that can carry a load. Payload is a Truck-only feature, so this
     * method must test each vehicle's type and downcast.
     *
     * @param fleet the vehicles to search
     * @param pounds the weight of the load
     * @return the trucks that can carry it
     */
    public static ArrayList<Truck> trucksThatCanHaul(ArrayList<Vehicle> fleet, int pounds) {
        ArrayList<Truck> result = new ArrayList<>();
        for (Vehicle v : fleet) {
            if (v instanceof Truck t && t.canHaul(pounds)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Finds the cars with at least a given number of seats.
     *
     * @param fleet the vehicles to search
     * @param seats the smallest acceptable number of seats
     * @return the matching cars
     */
    public static ArrayList<Car> carsSeatingAtLeast(ArrayList<Vehicle> fleet, int seats) {
        ArrayList<Car> result = new ArrayList<>();
        for (Vehicle v : fleet) {
            if (v instanceof Car c && c.getSeats() >= seats) {
                result.add(c);
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Vehicle> fleet = loadFleet(Path.of("fleet.csv"));

        System.out.println("Fleet:");
        for (Vehicle v : fleet) {
            System.out.println("  " + v);
        }

        quoteAll(fleet, 3, 240);
        quoteAll(fleet, 7, 900);

        System.out.println("Trucks for a 3000 lb load:");
        for (Truck t : trucksThatCanHaul(fleet, 3000)) {
            System.out.println("  " + t);
        }

        System.out.println("Cars seating at least 7:");
        for (Car c : carsSeatingAtLeast(fleet, 7)) {
            System.out.println("  " + c);
        }
    }
}
