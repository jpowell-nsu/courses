/**
 * Mechanic
 *
 * Example 2 version: a Mechanic USES a Car (dependence). It borrows one for
 * the duration of a single method call and never stores one as a field.
 *
 * @author  CIS 3300
 * @version 1.0
 */
public class Mechanic {
    public void inspect(Car car) {
        System.out.println("Inspecting a " + car.getYear() + " "
            + car.getMake() + " " + car.getModel());
    }
}
