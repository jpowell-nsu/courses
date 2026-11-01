import java.util.List;

public class ExtendsAdd {
    static void addOne(List<? extends Number> numbers) {
        numbers.add(1);
    }
}
