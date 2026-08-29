package racer;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class RacerDemoPart3 {
    public static void main(String[] args) throws Exception {
        ArrayList<Racer> racers = new ArrayList<Racer>();
        Scanner fileScanner = new Scanner(new File("files/racers.csv"));

        fileScanner.nextLine(); // skip the header row

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(","); // splits at every comma

            String name = parts[0];
            String vehicle = parts[1];
            double topSpeed = Double.parseDouble(parts[2]);

            racers.add(new Racer(name, vehicle, topSpeed));
        }
        fileScanner.close();

        for (int i = 0; i < racers.size(); i++) {
            System.out.println(racers.get(i).getName());
        }
    }
}
