import java.util.List;

public class InstanceOfGeneric {
    static boolean check(Object o) {
        return o instanceof List<String>;
    }
}
