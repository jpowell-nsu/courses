package payroll;

import java.util.ArrayList;

/**
 * Example 3: an abstract superclass. Employee can no longer be created directly, but
 * it is still the type that arrays, lists, and parameters use.
 *
 * @version 1.0
 */
public class Example3Demo {

    /**
     * Finds the employee with the highest monthly pay.
     *
     * @param staff the employees to search, which must not be empty
     * @return the highest paid employee
     */
    public static Employee highestPaid(ArrayList<Employee> staff) {
        Employee best = staff.get(0);
        for (Employee e : staff) {
            if (e.getMonthlyPay() > best.getMonthlyPay()) {
                best = e;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        // Employee nobody = new Employee("Nobody");   // does not compile: Employee is abstract

        ArrayList<Employee> staff = new ArrayList<>();
        staff.add(new SalariedEmployee("Ada", 60000));

        HourlyEmployee linus = new HourlyEmployee("Linus", 30);
        linus.setHoursThisMonth(170);   // 10 hours of overtime
        staff.add(linus);
        staff.add(new Manager("Grace", 90000, 12000));
        staff.add(new Executive("Alan", 150000, 30000, 24000));

        System.out.println("Staff:");
        for (Employee e : staff) {
            System.out.println("  " + e.getClass().getSimpleName() + ": " + e);
        }
        System.out.println("Highest paid: " + highestPaid(staff).getName());

        // Give every salaried employee a raise. Managers and executives are salaried too.
        for (Employee e : staff) {
            if (e instanceof SalariedEmployee s) {
                s.raiseSalary(5);
            }
        }
        System.out.println("After a 5 percent raise for salaried staff:");
        for (Employee e : staff) {
            System.out.println("  " + e);
        }
    }
}
