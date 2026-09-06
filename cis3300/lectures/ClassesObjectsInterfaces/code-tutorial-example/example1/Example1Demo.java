/**
 * Example1Demo
 *
 * Creates a couple of Car objects, uses them, and pokes at the identity
 * question: two cars with identical state are still two different objects.
 *
 * @author  CIS 3300
 * @version 1.0
 */
public class Example1Demo {
    public static void main(String[] args) {
        Car civic = new Car("Honda", "Civic", 2022);
        Car mustang = new Car("Ford", "Mustang", 2024);

        civic.drive(120);
        mustang.setMileage(15);

        System.out.println(civic.getMake() + " " + civic.getModel()
            + ": " + civic.getMileage() + " miles");
        System.out.println(mustang.getMake() + " " + mustang.getModel()
            + ": " + mustang.getMileage() + " miles");

        // a rejected write: mileage should never go backward
        civic.setMileage(10);

        // identity: two Car objects with identical state are still different objects
        Car civic2 = new Car("Honda", "Civic", 2022);
        System.out.println(civic == civic2);   // false -- two different objects
        System.out.println(civic == civic);    // true  -- the same object, compared to itself
    }
}
