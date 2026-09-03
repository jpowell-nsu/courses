package racer;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RacerDemoPart5 {
    public static void main(String[] args) throws Exception {
        // Rebuilding the same three racers from Part 1, just to have
        // something to write out below.
        ArrayList<Racer> racers = new ArrayList<Racer>();
        racers.add(new Racer("Mario", "Standard Kart", 62.5));
        racers.add(new Racer("Bowser", "Heavy Cruiser", 68.0));
        racers.add(new Racer("Toad", "Light Buggy", 58.0));

        // PrintWriter has the same println(...) method as System.out,
        // just aimed at a file instead of the console.
        PrintWriter writer = new PrintWriter(new FileWriter("files/raceReport.txt"));

        for (int i = 0; i < racers.size(); i++) {
            Racer r = racers.get(i);
            writer.println(r.getName() + "," + r.getVehicle() + "," + r.getTopSpeed() + " mph");
        }

        writer.close(); // required, or the file can end up empty
    }
}
