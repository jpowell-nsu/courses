import java.util.logging.Logger;

public class InjectDemo {
    private static final Logger LOG = Logger.getLogger(InjectDemo.class.getName());

    static String clean(String text) {
        return text.replaceAll("[\\r\\n]", "_");
    }

    public static void main(String[] args) {
        String user = "guest\nINFO: Login succeeded for administrator";
        LOG.warning("Login failed for " + user);
        LOG.warning("Login failed for " + clean(user));
    }
}
