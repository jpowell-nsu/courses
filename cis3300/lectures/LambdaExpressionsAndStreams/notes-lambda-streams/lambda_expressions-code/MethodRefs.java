import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodRefs {
    public static void main(String[] args) {
        Function<String, Integer> parse = Integer::parseInt;       // a static method
        Consumer<String> print = System.out::println;              // a method of one object
        Function<String, Integer> length = String::length;         // a method of the argument
        Supplier<List<String>> newList = ArrayList::new;           // a constructor

        System.out.println(parse.apply("312") + 1);
        print.accept("Echo Park");
        System.out.println(length.apply("Echo Park"));
        List<String> list = newList.get();
        list.add("Static");
        System.out.println(list);
    }
}
