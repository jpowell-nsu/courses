public class Suppressed {
    static class Ledger implements AutoCloseable {
        Ledger() { System.out.println("ledger opened"); }

        void post(double amount) {
            if (amount < 0) {
                throw new IllegalArgumentException("negative amount: " + amount);
            }
            System.out.println("posted " + amount);
        }

        @Override
        public void close() {
            System.out.println("ledger closing");
            throw new IllegalStateException("ledger could not be saved");
        }
    }

    public static void main(String[] args) {
        try (Ledger ledger = new Ledger()) {
            ledger.post(100);
            ledger.post(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("caught: " + e.getMessage());
            for (Throwable s : e.getSuppressed()) {
                System.out.println("suppressed: " + s.getMessage());
            }
        }
    }
}
