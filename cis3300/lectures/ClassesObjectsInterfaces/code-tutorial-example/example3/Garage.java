import java.util.ArrayList;

/**
 * Garage
 *
 * Example 2 version: a Garage HAS-A list of Cars (aggregation). It manages
 * many Car objects; code that wants a specific car goes through the
 * Garage rather than holding on to Car objects itself.
 *
 * @author  CIS 3300
 * @version 1.0
 */
public class Garage {
    private String location;
    private ArrayList<Car> fleet;

    public Garage(String location) {
        this.location = location;
        this.fleet = new ArrayList<>();
    }

    public void addCar(Car car) {
        fleet.add(car);
    }

    public Car findByModel(String model) {
        for (Car car : fleet) {
            if (car.getModel().equalsIgnoreCase(model)) {
                return car;
            }
        }
        return null;   // not found
    }

    public void printFleet() {
        for (Car car : fleet) {
            System.out.println(car.getYear() + " " + car.getMake()
                + " " + car.getModel());
        }
    }
}
