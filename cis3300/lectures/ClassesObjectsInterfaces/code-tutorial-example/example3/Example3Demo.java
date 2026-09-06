import java.util.ArrayList;

/**
 * Example3Demo
 *
 * Two things: (1) what actually gets passed when an object is an
 * argument, and (2) storing and working with objects in an ArrayList,
 * shown once with Cars and once with Pokemon.
 *
 * @author  CIS 3300
 * @version 1.0
 */
public class Example3Demo {

    public static void serviceCar(Car car) {
        car.setMileage(car.getMileage() + 5000);   // changes the object itself
    }

    public static void replaceCar(Car car) {
        car = new Car("Toyota", "Corolla", 2020);  // only reassigns the LOCAL copy
    }

    public static void main(String[] args) {
        // --- passing objects to methods ---
        Car civic = new Car("Honda", "Civic", 2022);

        serviceCar(civic);
        System.out.println(civic.getMileage());     // 5000 -- the change is visible

        replaceCar(civic);
        System.out.println(civic.getModel());        // "Civic" -- unchanged

        // --- ArrayList of objects: a typical example ---
        Garage shop = new Garage("Main Street");
        shop.addCar(new Car("Honda", "Civic", 2022));
        shop.addCar(new Car("Ford", "Mustang", 2024));
        shop.addCar(new Car("Toyota", "Corolla", 2020));
        shop.printFleet();

        // --- ArrayList of objects: a pop culture example ---
        ArrayList<Pokemon> party = new ArrayList<>();
        party.add(new Pokemon("Pikachu", "Electric", 35));
        party.add(new Pokemon("Charizard", "Fire", 78));
        party.add(new Pokemon("Snorlax", "Normal", 160));

        for (Pokemon p : party) {
            System.out.println(p.getName() + " (" + p.getType()
                + "), HP: " + p.getHp());
        }

        int totalHp = 0;
        for (Pokemon p : party) {
            totalHp += p.getHp();
        }
        System.out.println("Total party HP: " + totalHp);
    }
}
