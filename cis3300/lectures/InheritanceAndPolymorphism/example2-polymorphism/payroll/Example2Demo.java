package payroll;

import java.util.ArrayList;

/**
 * Example 2: polymorphism. Employees of different classes share arrays, lists, and
 * method parameters declared as Employee, and each object still runs its own methods.
 *
 * @version 1.0
 */
public class Example2Demo {

    /**
     * Adds up one month of pay for everyone on the list. This method only knows about
     * Employee, yet it works for every subclass, including ones written later.
     *
     * @param staff the employees to pay
     * @return the total monthly payroll
     */
    public static double totalMonthlyPay(ArrayList<Employee> staff) {
        double total = 0;
        for (Employee e : staff) {
            total += e.getMonthlyPay();   // which getMonthlyPay? decided by each object at run time
        }
        return total;
    }

    // Two overloads. The compiler picks one using the DECLARED type of the argument.
    public static void printBadge(Employee e) {
        System.out.println("  Badge: STAFF   " + e.getName());
    }

    public static void printBadge(Manager m) {
        System.out.println("  Badge: MANAGER " + m.getName());
    }

    public static void main(String[] args) {
        Employee ada = new Employee("Ada", 60000);
        Manager grace = new Manager("Grace", 90000, 12000);
        Executive alan = new Executive("Alan", 150000, 30000, 24000);

        // ----- An array of the superclass type holds any kind of Employee -----
        System.out.println("Array:");
        Employee[] team = { ada, grace, alan };   // upcasting happens automatically
        for (Employee e : team) {
            System.out.println("  " + e);
        }

        // ----- The same idea with an ArrayList and a method parameter -----
        ArrayList<Employee> staff = new ArrayList<>();
        staff.add(ada);
        staff.add(grace);
        staff.add(alan);
        System.out.printf("Total monthly pay: %.2f%n", totalMonthlyPay(staff));

        // ----- Declared type versus actual type -----
        Employee someone = grace;               // declared type Employee, actual type Manager
        System.out.printf("someone.getMonthlyPay(): %.2f%n", someone.getMonthlyPay());
        // someone.getBonus();                  // does not compile: Employee has no getBonus

        // ----- Overloading is decided at compile time, overriding at run time -----
        System.out.println("Badges:");
        printBadge(grace);     // declared type Manager  -> printBadge(Manager)
        printBadge(someone);   // declared type Employee -> printBadge(Employee), even though it is a Manager

        // ----- Downcasting to reach a subclass-only method -----
        System.out.println("Bonuses:");
        for (Employee e : staff) {
            if (e instanceof Manager m) {       // test and cast in one step
                m.setBonus(m.getBonus() + 1000);
                System.out.printf("  %s now has a bonus of %.2f%n", m.getName(), m.getBonus());
            }
        }

        // ----- A bad downcast compiles, then fails at run time -----
        Employee notAManager = ada;
        try {
            Manager oops = (Manager) notAManager;
            System.out.println(oops.getBonus());
        } catch (ClassCastException ex) {
            System.out.println("ClassCastException: " + ex.getMessage());
        }

        // ----- instanceof versus getClass -----
        Employee boss = alan;                   // declared Employee, actual Executive
        System.out.println("boss instanceof Manager:          " + (boss instanceof Manager));
        System.out.println("boss.getClass() == Manager.class: " + (boss.getClass() == Manager.class));
        System.out.println("boss.getClass().getSimpleName():  " + boss.getClass().getSimpleName());

        // ----- equals in a hierarchy: same ID means same employee -----
        Employee sameAlan = staff.get(2);
        System.out.println("staff.get(2).equals(alan): " + sameAlan.equals(alan));
        System.out.println("staff.contains(grace):     " + staff.contains(grace));
    }
}
