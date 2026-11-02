// A saved login session. Serializable so the kiosk can write it to disk and
// read it back later. Nothing here looks dangerous.
import java.io.Serializable;

public class Session implements Serializable {
    private static final long serialVersionUID = 1L;
    String  name;
    boolean isAdmin;

    Session(String name, boolean isAdmin) {
        this.name = name;
        this.isAdmin = isAdmin;
    }
}
