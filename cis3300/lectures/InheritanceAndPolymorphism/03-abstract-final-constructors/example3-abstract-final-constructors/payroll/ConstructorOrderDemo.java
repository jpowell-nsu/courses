package payroll;

/**
 * Example 3, part 2: the order in which static blocks, field initializers, and constructor
 * bodies run when a subclass object is created, and why a constructor should not call a
 * method that a subclass can override.
 *
 * @version 1.0
 */
public class ConstructorOrderDemo {

    static class Base {
        static {
            System.out.println("Base static block");
        }

        private String baseField = trace("Base field initializer");

        Base() {
            super();   // calls Object(); Java would insert this line for us
            System.out.println("Base constructor body");
            describe();   // risky: this calls an overridable method
        }

        void describe() {
            System.out.println("Base.describe()");
        }
    }

    static class Derived extends Base {
        static {
            System.out.println("Derived static block");
        }

        private String label = trace("Derived field initializer");

        Derived() {
            super();
            System.out.println("Derived constructor body, label = " + label);
        }

        @Override
        void describe() {
            System.out.println("Derived.describe(), label = " + label);
        }
    }

    /** Prints a message and returns it, so a field initializer can announce itself. */
    static String trace(String message) {
        System.out.println(message);
        return message;
    }

    public static void main(String[] args) {
        System.out.println("main starts");
        new Derived();
        System.out.println("(a second object)");
        new Derived();
    }
}
