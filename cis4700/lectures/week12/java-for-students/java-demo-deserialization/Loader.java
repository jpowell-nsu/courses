// The victim. It loads a previously "saved" session and greets the user. It
// looks completely ordinary: read the object, use it. The flaw is that it calls
// readObject on data it does not control, and trusts whatever comes back.
import java.io.*;

public class Loader {
    public static void main(String[] args) throws Exception {
        String path = (args.length > 0) ? args[0] : "session.ser";
        System.out.println("Loading saved session from " + path + " ...");

        try (ObjectInputStream in =
                 new ObjectInputStream(new FileInputStream(path))) {

            Object obj = in.readObject();   // <-- attacker code can run HERE
            Session s = (Session) obj;      // the cast happens only AFTER that

            System.out.printf("  Welcome back, %s. access level: %s%n",
                              s.name, s.isAdmin ? "STAFF" : "resident");
            if (s.isAdmin)
                System.out.println("  >>> SERVER ROOM DOOR UNLOCKED <<<");

        } catch (ClassCastException e) {
            System.out.println("  (the object was not a Session: " + e.getMessage() + ")");
            System.out.println("  ... but notice the attacker's code already ran above.");
        }
    }
}
