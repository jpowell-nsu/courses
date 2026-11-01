public class PairDemo {
    public static void main(String[] args) {
        Pair<String, Integer> song = new Pair<>("Slow Burn", 312);
        Pair<String, Boolean> setting = new Pair<>("shuffle", true);

        String title = song.first();          // no cast needed
        int seconds = song.second();
        System.out.println(title + " lasts " + seconds + " seconds");
        System.out.println(setting);
    }
}
