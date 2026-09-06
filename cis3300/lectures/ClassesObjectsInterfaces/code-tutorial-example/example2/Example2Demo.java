/**
 * Example2Demo
 *
 * Builds a small multi-class program: a Garage that holds several Car
 * objects, and a Mechanic that borrows one of them to inspect it.
 *
 * @author  CIS 3300
 * @version 1.0
 */
public class Example2Demo {
    public static void main(String[] args) {
        Garage shop = new Garage("Main Street");
        shop.addCar(new Car("Honda", "Civic", 2022));
        shop.addCar(new Car("Ford", "Mustang", 2024));
        shop.addCar(new Car("Toyota", "Corolla", 2020));

        shop.printFleet();

        Car found = shop.findByModel("Mustang");
        Mechanic tom = new Mechanic();
        if (found != null) {
            tom.inspect(found);
        }
    }
}
