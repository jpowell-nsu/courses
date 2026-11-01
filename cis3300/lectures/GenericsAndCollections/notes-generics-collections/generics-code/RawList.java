import java.util.ArrayList;

public class RawList {
    public static void main(String[] args) {
        ArrayList titles = new ArrayList();
        titles.add("Paper Moons");
        titles.add(42);                           // nothing stops this
        for (Object o : titles) {
            String title = (String) o;            // every element needs a cast
            System.out.println(title.toUpperCase());
        }
    }
}
