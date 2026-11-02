// ===========================================================================
//  KioskContrast.java   --   the C door kiosk, rebuilt in Java.
//
//  This is the opener. Run the SAME sequence that broke the C version (a staff
//  member badges in and out, then a resident badges in) and watch it refuse to
//  break. Two things make the C bug impossible here:
//     (1) every 'new Session()' gives a fresh object whose fields the JVM sets
//         to defaults (isAdmin = false), so there is no leftover value to read,
//     (2) "logging out" just drops the reference; there is no free(), and the
//         garbage collector will never hand a future 'new' a stale old object.
//
//  Compile and run:
//      javac KioskContrast.java
//      java KioskContrast
// ===========================================================================

import java.util.Scanner;

public class KioskContrast {

    static class Session {
        String  name;
        boolean isAdmin;     // Java ALWAYS initializes this to false
    }

    static Session cur = null;   // whoever is currently badged in

    static void badgeIn(Scanner in) {
        System.out.print("  Username: ");
        String user = in.nextLine();
        System.out.print("  Password: ");
        String pass = in.nextLine();

        cur = new Session();     // a brand-new object; fields are zeroed by the JVM
        System.out.printf("  [trace] new Session() -> object #%d%n",
                          System.identityHashCode(cur));
        System.out.printf("  [trace] isAdmin before we set anything: %b"
                          + "   (Java guarantees false)%n", cur.isAdmin);

        cur.name = user;
        if (user.equals("ra") && pass.equals("letmein")) {
            cur.isAdmin = true;
            System.out.println("  [trace] staff password correct: isAdmin = true");
        } else {
            System.out.println("  [trace] resident login: we never set isAdmin,"
                             + " so it stays false");
        }
        System.out.printf("  Welcome, %s.%n", cur.name);
    }

    static void checkAccess() {
        if (cur == null) { System.out.println("  Nobody is badged in."); return; }
        System.out.printf("  User: %s | access level: %s%n",
                          cur.name, cur.isAdmin ? "STAFF" : "resident");
        if (cur.isAdmin)
            System.out.println("  >>> SERVER ROOM DOOR UNLOCKED <<<");
    }

    static void badgeOut() {
        if (cur == null) { System.out.println("  Nobody is badged in."); return; }
        System.out.printf("  Goodbye, %s.%n", cur.name);
        System.out.printf("  [trace] dropping the reference to object #%d"
                          + "  (no free(); the garbage collector owns it now)%n",
                          System.identityHashCode(cur));
        cur = null;              // the closest thing to 'free': we just forget it
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("=== Residence Hall Door Kiosk (Java) ===");
        while (true) {
            System.out.println("\n[1] Badge in   [2] Check access   [3] Badge out   [4] Quit");
            System.out.print("> ");
            if (!in.hasNextLine()) break;
            switch (in.nextLine().trim()) {
                case "1": badgeIn(in);     break;
                case "2": checkAccess();   break;
                case "3": badgeOut();      break;
                case "4": System.out.println("Bye."); return;
                default:  System.out.println("  (type 1, 2, 3, or 4)");
            }
        }
    }
}
