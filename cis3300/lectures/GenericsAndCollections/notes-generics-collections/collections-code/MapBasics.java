import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapBasics {
    public static void main(String[] args) {
        Map<String, Integer> plays = new TreeMap<>();
        String[] history = {"Static", "Echo Park", "Static", "Slow Burn", "Glass Garden",
                "Static", "Paper Moons", "Echo Park", "Northern Lights"};
        for (String title : history) {
            plays.put(title, plays.getOrDefault(title, 0) + 1);
        }
        System.out.println(plays);
        System.out.println("Static: " + plays.get("Static") + ", Midnight Drive: "
                + plays.get("Midnight Drive") + ", contains Echo Park: "
                + plays.containsKey("Echo Park"));

        for (Map.Entry<String, Integer> entry : plays.entrySet()) {
            System.out.println(entry.getKey() + " played " + entry.getValue() + " times");
        }

        Map<String, Integer> hash = new HashMap<>();
        Map<String, Integer> linked = new LinkedHashMap<>();
        for (String title : history) {
            hash.put(title, 0);
            linked.put(title, 0);
        }
        System.out.println("HashMap keys:       " + hash.keySet());
        System.out.println("LinkedHashMap keys: " + linked.keySet());
    }
}
