/**
 * Example5Demo
 *
 * Exercises the Serviceable interface through Car: the abstract method
 * Car had to write, the default method it got for free, and the shared
 * constant declared on the interface itself.
 *
 * @author  CIS 3300
 * @version 1.0
 */
public class Example5Demo {
    public static void main(String[] args) {
        Car civic = new Car("Honda", "Civic", 2022);
        civic.drive(6000);

        civic.service();     // abstract method Car had to implement
        civic.honk();         // default method Car gets for free

        System.out.println("Service interval: "
            + Serviceable.MAX_MILES_BETWEEN_SERVICES + " miles");
    }
}
