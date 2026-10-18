package payroll;

/**
 * Example 1: a first subclass. Shows what Manager inherits from Employee,
 * what it adds, and what it overrides.
 *
 * @version 1.0
 */
public class Example1Demo {
    public static void main(String[] args) {
        Employee ada = new Employee("Ada", 60000);
        Manager grace = new Manager("Grace", 90000, 12000);

        // toString is called automatically by println.
        System.out.println(ada);
        System.out.println(grace);

        // Inherited methods: Manager never declared getId, getName, or raiseSalary.
        System.out.println(grace.getId() + " is " + grace.getName());
        grace.raiseSalary(10);
        System.out.println("After a 10 percent raise: " + grace);

        // A method Manager added. An Employee does not have it.
        grace.setBonus(15000);
        System.out.println("After a new bonus: " + grace);
        // ada.setBonus(500);   // does not compile: cannot find symbol

        // Every class extends Object, so every object has getClass, equals, and hashCode.
        System.out.println(grace.getClass().getName());
        System.out.println(grace.getClass().getSuperclass().getName());
        System.out.println(grace.getClass().getSuperclass().getSuperclass().getName());
    }
}
