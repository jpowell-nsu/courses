import java.util.ArrayList;
import java.util.List;

public class NotSubtype {
    public static void main(String[] args) {
        List<Integer> lengths = new ArrayList<>();
        List<Number> numbers = lengths;
    }
}
