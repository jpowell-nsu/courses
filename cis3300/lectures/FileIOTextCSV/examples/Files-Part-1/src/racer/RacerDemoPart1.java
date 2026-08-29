package racer;

import java.util.ArrayList;

public class RacerDemoPart1 {
    public static void main(String[] args) {
        ArrayList<Racer> racers = new ArrayList<Racer>();

        // Build an object, then add it to the list, in one step.
        racers.add(new Racer("Mario", "Standard Kart", 62.5));
        racers.add(new Racer("Bowser", "Heavy Cruiser", 68.0));
        racers.add(new Racer("Toad", "Light Buggy", 58.0));

        // Loop through the list, calling a getter on each object.
        for (int i = 0; i < racers.size(); i++) {
            Racer r = racers.get(i);
            System.out.println(r.getName() + " drives the " + r.getVehicle()
                    + " at " + r.getTopSpeed() + " mph");
        }

        // Walk the list, keeping the best one seen so far.
        Racer fastest = racers.get(0);
        for (int i = 1; i < racers.size(); i++) {
            if (racers.get(i).getTopSpeed() > fastest.getTopSpeed()) {
                fastest = racers.get(i);
            }
        }
        System.out.println("Fastest: " + fastest.getName());
    }
}
