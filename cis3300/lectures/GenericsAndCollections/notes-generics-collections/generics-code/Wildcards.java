import java.util.ArrayList;
import java.util.List;

public class Wildcards {
    /** Reads numbers of any Number type and adds them up. */
    static double total(List<? extends Number> numbers) {
        double sum = 0;
        for (Number n : numbers) {
            sum += n.doubleValue();
        }
        return sum;
    }

    /** Puts count zeros into any list that can hold an Integer. */
    static void addZeros(List<? super Integer> list, int count) {
        for (int i = 0; i < count; i++) {
            list.add(0);
        }
    }

    public static void main(String[] args) {
        List<Integer> lengths = List.of(254, 198, 312);
        List<Double> ratings = List.of(4.5, 3.0, 5.0);
        System.out.println(total(lengths));
        System.out.println(total(ratings));

        List<Integer> ints = new ArrayList<>();
        List<Number> nums = new ArrayList<>();
        List<Object> objs = new ArrayList<>();
        addZeros(ints, 2);
        addZeros(nums, 2);
        addZeros(objs, 2);
        System.out.println(ints + " " + nums + " " + objs);
    }
}
