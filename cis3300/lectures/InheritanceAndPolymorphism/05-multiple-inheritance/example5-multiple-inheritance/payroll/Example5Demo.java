package payroll;

import java.util.ArrayList;

/**
 * Example 5: multiple inheritance of type. One class, many types; one list, many classes;
 * and what happens when two interfaces supply the same default method.
 *
 * @version 1.0
 */
public class Example5Demo {

    /**
     * Prints a payment report. The method accepts anything Payable, so it works for
     * employees and contractors alike.
     *
     * @param payees everyone to pay this month
     */
    public static void printPayments(ArrayList<Payable> payees) {
        double total = 0;
        for (Payable p : payees) {
            System.out.println("  " + p.paymentLine());
            total += p.getMonthlyPay();
        }
        System.out.printf("  %-10s %10.2f%n", "TOTAL", total);
    }

    public static void main(String[] args) {
        SalariedEmployee ada = new SalariedEmployee("Ada", 60000);
        HourlyEmployee linus = new HourlyEmployee("Linus", 30);
        linus.setHoursThisMonth(170);
        Manager grace = new Manager("Grace", 90000, 12000);
        Executive alan = new Executive("Alan", 150000, 30000, 24000);
        Contractor margaret = new Contractor("Margaret", "Hamilton Labs", 7200);

        // ----- One list, many classes, one shared type -----
        ArrayList<Payable> payees = new ArrayList<>();
        payees.add(ada);
        payees.add(linus);
        payees.add(grace);
        payees.add(alan);
        payees.add(margaret);   // not an Employee, but still Payable
        System.out.println("Payments:");
        printPayments(payees);

        // ----- One object, many types -----
        Object thing = alan;
        System.out.println("alan is an Executive?        " + (thing instanceof Executive));
        System.out.println("alan is a Manager?           " + (thing instanceof Manager));
        System.out.println("alan is a SalariedEmployee?  " + (thing instanceof SalariedEmployee));
        System.out.println("alan is an Employee?         " + (thing instanceof Employee));
        System.out.println("alan is Payable?             " + (thing instanceof Payable));
        System.out.println("alan is a Mentor?            " + (thing instanceof Mentor));
        System.out.println("alan is a Trainer?           " + (thing instanceof Trainer));
        Payable who = margaret;
        System.out.println("margaret is an Employee?     " + (who instanceof Employee));
        // margaret instanceof Employee   does not compile: Contractor and Employee are
        // unrelated classes, so the compiler knows the answer could never be true.

        // ----- Interface types as variables -----
        Mentor mentor = grace;
        Trainer trainer = alan;   // Executive inherits Manager's override
        System.out.println("Grace " + mentor.supportDescription());
        System.out.println("Alan " + trainer.supportDescription());

        // ----- Casting from an interface type back to a class -----
        for (Payable p : payees) {
            if (p instanceof Contractor c) {
                System.out.println(c.getName() + " works for " + c.getCompany());
            }
        }
    }
}
