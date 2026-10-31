public class SysLogDemo {
    private static final System.Logger LOG = System.getLogger(SysLogDemo.class.getName());

    public static void main(String[] args) {
        LOG.log(System.Logger.Level.INFO, "Loading {0}", "files/staff.csv");
        LOG.log(System.Logger.Level.WARNING, "Skipped line {0}", 2);
    }
}
