/**
 * Example4Demo
 *
 * Shows what changes, and what does not, once equals, hashCode, and
 * toString are overridden on Car.
 *
 * @author  CIS 3300
 * @version 1.0
 */
public class Example4Demo {
    public static void main(String[] args) {
        Car civic1 = new Car("Honda", "Civic", 2022);
        Car civic2 = new Car("Honda", "Civic", 2022);

        System.out.println(civic1 == civic2);        // false -- still two different objects
        System.out.println(civic1.equals(civic2));   // true -- now that equals compares fields
        System.out.println(civic1);                  // uses toString automatically

        System.out.println(civic1.hashCode() == civic2.hashCode());   // true -- equal fields, equal hash codes
    }
}
